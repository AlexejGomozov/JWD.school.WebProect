package com.javacourse.specialist.connection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.javacourse.specialist.exception.DaoException;
import com.javacourse.specialist.exception.DatabaseConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger();

    private static final Properties properties = new Properties();
    private static final String DATABASE_PROPERTIES = "db.properties";

    private static final String PROPERTY_URL = "db.url";
    private static final String PROPERTY_USERNAME = "db.user";
    private static final String  PROPERTY_PASSWORD = "db.password";
    private static final String PROPERTY_DRIVER = "db.driver";

    private static final String DATABASE_URL;
    private static final String DATABASE_USERNAME;
    private static final String  DATABASE_PASSWORD;
    private static final String DATABASE_DRIVER;
    private static final int DEFAULT_POOL_SIZE = 4;
    private int connNum;
   // private static String SQL_VERIFCONN = "select 1";

    private static final Lock lockerConnection = new ReentrantLock();
    private static final AtomicBoolean isCreated = new AtomicBoolean();
    BlockingQueue<ProxyConnection> freePool;
    BlockingQueue<ProxyConnection> occupiedPool;
    private static ConnectionPool instance = null;

    static{
        try(InputStream inputStream = ConnectionPool.class.getClassLoader().getResourceAsStream(DATABASE_PROPERTIES)) {
            properties.load(inputStream);
            DATABASE_USERNAME = properties.getProperty(PROPERTY_USERNAME);
            DATABASE_PASSWORD = properties.getProperty(PROPERTY_PASSWORD);
            DATABASE_URL = properties.getProperty(PROPERTY_URL);
            DATABASE_DRIVER = properties.getProperty(PROPERTY_DRIVER);
            Class.forName(DATABASE_DRIVER);
        }catch(ClassNotFoundException e){
            logger.fatal("Not register driver: " + properties.getProperty(PROPERTY_DRIVER), e);
            throw new RuntimeException(e);
        }catch(FileNotFoundException e){
            logger.fatal("Exception while properies load : " +  e.getMessage());
            throw new RuntimeException(e);
        }catch(IOException e){
            logger.fatal("Not load file: "+ e.getMessage());
            throw new RuntimeException(e);
        }
    }


    private ConnectionPool(){
        freePool = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        occupiedPool = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);

        for(int i = 0; i < DEFAULT_POOL_SIZE; i++){
            try {
                ProxyConnection proxyConnection = getConnection();
                //ProxyConnection proxyConnection = new ProxyConnection(connection);
                freePool.add(proxyConnection);  //put(proxyConnection)
            } catch (DaoException e) {
               logger.error("Error for create connection: " + e.getMessage());
            }
        }
    }

    public static ConnectionPool getInstance(){
        if(!isCreated.get()){
        lockerConnection.lock();
        try {
            if (instance == null) {
                instance = new ConnectionPool();
            }
        }finally{
        lockerConnection.unlock();}
        }
        return instance;
    }
    //__________________________________

   public ProxyConnection getConnection() throws DaoException{
       ProxyConnection conn = null;
       if(isFull()){
           throw new DaoException("The connection is full");
       }
       try {
           conn = getConnectionFromPool();
       if(conn == null){
           conn = createNewConnectionForPool();
       }
           conn = makeAvailable(conn);
       } catch (InterruptedException e) {
           logger.error("Exception into 'getConnection' method: " + e.getMessage());
           Thread.currentThread().interrupt();
       }
       return conn;
   }


    public boolean returnConnection(ProxyConnection conn) {   //throws SQLException{  //???? void (or) boolean
//       try {
//           if (conn == null) {
//               throw new DatabaseConnectionException();  //NullPointerException
//           }
//       }catch(DatabaseConnectionException e){
//           logger.error("Exception into 'returnConnection' method, it's not connection"+ e.getMessage());
//           return false; // здесь же можно вставить return если conn == null ???
//       }

       try {
           if (!occupiedPool.remove(conn)) {
               throw new SQLException(
                       "The connection is returned alredy or it's not for this pool");
           }
       }catch(SQLException e){
           logger.error( "The connection is returned alredy or it's not for this pool" + e.getMessage());
           return false;  //???
       }
       // if(conn instanceof  ProxyConnection) {   //if(conn instanceof  ProxyConnection conn) -подсвечивает
            try {
                freePool.put(conn);
                return true;   //?????
            } catch (InterruptedException e) {
                logger.error("Exception into 'returnConnection' method: " + e.getMessage());
                Thread.currentThread().interrupt();
                return false;// здесь ???
            }
        //return false;  //   или здесь???
        }
    //}


    private boolean isFull(){
        return ((freePool.size()==0) && (connNum>= DEFAULT_POOL_SIZE));
    }

    private ProxyConnection createNewConnectionForPool()throws DaoException{
        ProxyConnection conn = createNewConnection();
        connNum++;
        occupiedPool.add(conn);
        return conn;
    }

    private ProxyConnection createNewConnection() throws DaoException{

            ProxyConnection conn = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            conn = (ProxyConnection) DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        }catch(SQLException e){
            logger.error("Exception into 'createNewConnectionForPool' method: " + e.getMessage());
            throw new DaoException(e);
        }
        return conn;
    }


   private ProxyConnection getConnectionFromPool() throws InterruptedException {
        ProxyConnection conn = null;
        if(freePool.size() > 0){
                conn = freePool.take();
                occupiedPool.put(conn);
        }
       return conn;
   }


   private ProxyConnection makeAvailable(ProxyConnection conn) throws DaoException {
        if(isConnectionAvailable(conn)){
            return conn;}
       try {
           occupiedPool.remove(conn);
           connNum--;
           conn.close();
       }catch(SQLException e){
           throw new DaoException(e);
       }
                    try {
                        conn = createNewConnection();
                        occupiedPool.put(conn);
                        connNum++;
                    }catch(InterruptedException e){
                        throw new DaoException(e);
                    }
                         return conn;
                        }


   private boolean isConnectionAvailable(ProxyConnection conn){
        String SQL_VERIFCONN = "select 1";
        try(Statement st = conn.createStatement()){
            st.executeQuery(SQL_VERIFCONN);
            return true;
        }catch(SQLException e){
            logger.error("Exception into 'isConnectionAvailable' method, connection it's not available: " + e.getMessage());
            return false;
        }
   }
   public void destroyPool(){
        for(int i = 0; i<DEFAULT_POOL_SIZE; i++){
           try {
               freePool.take().reallyClose();
           } catch (InterruptedException e) {
              logger.error("Exception into 'destroyPool' method: " + e.getMessage());
           }
        }
   }
}
