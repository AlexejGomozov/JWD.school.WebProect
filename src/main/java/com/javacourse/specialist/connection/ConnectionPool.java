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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final Properties PROPERTIES = new Properties();
    private static final String DATABASE_PROPERTIES = "db.properties";

    private static final String PROPERTY_URL = "db.url";
    private static final String PROPERTY_USERNAME = "db.user";
    private static final String  PROPERTY_PASSWORD = "db.password";
    private static final String PROPERTY_DRIVER = "db.driver";

    private static final String DATABASE_URL;
    private static final String DATABASE_USERNAME;
    private static final String  DATABASE_PASSWORD;
    private static final String DATABASE_DRIVER;
    private static final int POOL_SIZE = 4;
    private int connCount;

    private static final Lock lockerConnection = new ReentrantLock();
    private static final AtomicBoolean isCreated = new AtomicBoolean();
    private BlockingQueue<ProxyConnection> freePool;
    private BlockingQueue<ProxyConnection> occupiedPool;
    private static ConnectionPool instance;

    static{
        try(InputStream inputStream = ConnectionPool.class.getClassLoader().getResourceAsStream(DATABASE_PROPERTIES)) {
            PROPERTIES.load(inputStream);
            DATABASE_USERNAME = PROPERTIES.getProperty(PROPERTY_USERNAME);
            DATABASE_PASSWORD = PROPERTIES.getProperty(PROPERTY_PASSWORD);
            DATABASE_URL = PROPERTIES.getProperty(PROPERTY_URL);
            DATABASE_DRIVER = PROPERTIES.getProperty(PROPERTY_DRIVER);
            Class.forName(DATABASE_DRIVER);
        }catch(ClassNotFoundException e){
            LOGGER.fatal("Not register driver: " + PROPERTIES.getProperty(PROPERTY_DRIVER), e);
            throw new RuntimeException(e);
        }catch(FileNotFoundException e){
            LOGGER.fatal("Exception while properies load : " +  e.getMessage());
            throw new RuntimeException(e);
        }catch(IOException e){
            LOGGER.fatal("Not load file: "+ e.getMessage());
            throw new RuntimeException(e);
        }
    }


    private ConnectionPool(){
        freePool = new LinkedBlockingDeque<>(POOL_SIZE);
        occupiedPool = new LinkedBlockingDeque<>(POOL_SIZE);

        for(int i = 0; i < POOL_SIZE; i++){
            try {
                Connection connection = getConnection();
                freePool.add((ProxyConnection) connection);
            } catch (DaoException e) {
                LOGGER.error("Error for create connection: " + e.getMessage());
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

    public Connection getConnection() throws DaoException{
        ProxyConnection conn = null;
        try {
            conn = freePool.take();
            occupiedPool.put(conn);
            conn = makeAvailable(conn);

        } catch (InterruptedException e) {
            LOGGER.error("Exception into 'getConnection' method: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        return conn;
    }



    public boolean releaseConnection(Connection conn) {
       boolean isRelease = true;
        if (!occupiedPool.remove(conn)) {
            LOGGER.error("The connection is returned alredy or it's not for this pool");
            return !isRelease;
        }
            try {
                freePool.put((ProxyConnection) conn);
                return isRelease;
            } catch(InterruptedException e){
                LOGGER.error("Exception into 'returnConnection' method: " + e.getMessage());
                Thread.currentThread().interrupt();
                return !isRelease;
            }
        }


    private ProxyConnection createNewConnection() throws DaoException{

            ProxyConnection conn = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            conn = (ProxyConnection) DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        }catch(SQLException e){
            LOGGER.error("Exception into 'createNewConnectionForPool' method: " + e.getMessage());
            throw new DaoException(e);
        }
        return conn;
    }


   private ProxyConnection makeAvailable(ProxyConnection conn) throws DaoException {
        if(isConnectionAvailable(conn)){
            return conn;}
        lockerConnection.lock();
       try {
           occupiedPool.remove(conn);
           connCount--;
           conn.close();
       }catch(SQLException e){
           throw new DaoException(e);
       }
                    try {
                        conn = createNewConnection();
                        occupiedPool.put(conn);
                        connCount++;
                    }catch(InterruptedException e){
                        throw new DaoException(e);
                    }
                    finally{lockerConnection.unlock();
                    }
                    return conn;
    }


   private boolean isConnectionAvailable(ProxyConnection conn){
        String SQL_VERIFCONN = "select 1";
        try(Statement st = conn.createStatement()){
            st.executeQuery(SQL_VERIFCONN);
            return true;
        }catch(SQLException e){
            LOGGER.error("Exception into 'isConnectionAvailable' method, connection it's not available: " + e.getMessage());
            return false;
        }
   }
   public void destroyPool(){
        for(int i = 0; i<POOL_SIZE; i++){
           try {
               freePool.take().reallyClose();
           } catch (InterruptedException e) {
               LOGGER.error("Exception into 'destroyPool' method: " + e.getMessage());
           }
        }
   }
}
