package com.javacourse.specialist.dao.impl;
import com.javacourse.specialist.connection.ConnectionPool;
import com.javacourse.specialist.dao.UserDAO;
import com.javacourse.specialist.entity.RegistrationStatus;
import com.javacourse.specialist.entity.UserRoles;
import com.javacourse.specialist.entity.Users;
import com.javacourse.specialist.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl<Connecton> implements UserDAO {
    private static final Logger logger = LogManager.getLogger();
    ConnectionPool connectionPool = ConnectionPool.getInstance();
//    Connection conn ; //= null;
//
//    {
//        try {
//            conn = connectionPool.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

//    private Connection getConnection() {
//
//        String connectionString = "jdbc:mysql://localhost:3306/specialist";
//        String USERNAME = "root";
//        String PASSWORD = "grt7832_2";
//        Connection dbConnection = null;
//        try {
//            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//            dbConnection = DriverManager.getConnection(connectionString, USERNAME, PASSWORD);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return dbConnection;
//    }

    @Override
    public void addUser(Users user) throws DaoException {
        Connection  dbConnection = connectionPool.getConnection();
           // Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        try {
        String sqlQuery =
                "INSERT INTO users (name, surname, phoneNumber, login, password, userRole, registrationStatus) VALUES(?, ?, ?, ?, ?, ?, ?)";
            //Connection  dbConnection = connectionPool.getConnection();;//getConnection();
                preparedStatement = dbConnection.prepareStatement(sqlQuery);

                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2,user.getSurname());
                preparedStatement.setInt(3, user.getPhoneNumber());
                preparedStatement.setString(4,user.getLogin());
                preparedStatement.setString(5,user.getPassword());
                preparedStatement.setString(6,user.getUserRole().getRole());
                preparedStatement.setString(7,user.getRegistrationStatus().getStatus());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error("Exception while method addUser: " + e.getMessage());
        } finally{
            try{
                if(preparedStatement != null){ preparedStatement.close();}
                if(dbConnection != null){ dbConnection.close();}
            }catch(SQLException e){
                logger.error("Exception while method addUser: " + e.getMessage());
            }
        }
        }


    @Override
    public List<Users> findAllUser() throws DaoException {
        Connection dbConnection = connectionPool.getConnection();
       // Connection dbConnection = null;
       Statement statement = null;
        List<Users> users = new ArrayList<>();
        try {
            String sqlQuery =
                    "SELECT id, name, surname phoneNumber, login, password, userRoles, registrationStatus FROM users";
           // Connection dbConnection = connectionPool.getConnection();//getConnection();
            statement = dbConnection.createStatement();

            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while(resultSet.next()){
                Users user = new Users();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setPhoneNumber(resultSet.getInt("phonenumber"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                UserRoles userRoles = new UserRoles();
                userRoles.setRole(resultSet.getString("user_roles"));
                RegistrationStatus registrationStatus = new RegistrationStatus();
                registrationStatus.setStatus(resultSet.getString("registration_status"));

                users.add(user);  //add or other type of method?
            }
        } catch (SQLException e) {
            logger.error("Exception while method getAllUser: " + e.getMessage());
        } finally{
            try{
                if(statement != null){ statement.close();}
                if(dbConnection != null){ dbConnection.close();}
            }catch(SQLException e){
                logger.error("Exception while method getAllUser: " + e.getMessage());
            }
        }
        return users;
    }


    @Override
    public Users findUserById(int id) throws DaoException {

        Connection dbConnection = connectionPool.getConnection();
        //Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        Users user = new Users();
        try {
            String sqlQuery =                       //писать через нижнее подчеркив-е?
                    "SELECT id, name, surname phone_number, login, password, role, status FROM users WHERE id=?";
//          Connection dbConnection = connectionPool.getConnection();//getConnection();
            preparedStatement = dbConnection.prepareStatement(sqlQuery);

            preparedStatement.setInt(1, user.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setPhoneNumber(resultSet.getInt("phone_number"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                UserRoles userRole = new UserRoles();
                userRole.setRole(resultSet.getString("role"));
                RegistrationStatus registrationStatus = new RegistrationStatus();
                registrationStatus.setStatus(resultSet.getString("status"));

            }
           user.setId(id);
        } catch (SQLException e) {
            logger.error("Exception while method getUserById: " + e.getMessage());
        } finally{
            try{
                if(preparedStatement != null){ preparedStatement.close();}
                if(dbConnection != null){ dbConnection.close();}
            }catch(SQLException e){
                logger.error("Exception while method getUserById: " + e.getMessage());
            }
        }
        return user;
    }

    @Override
    public void removeUserById(int id) throws DaoException {
        Connection dbConnection = connectionPool.getConnection();
        String sqlQuery = "DELETE FROM users WHERE id=?";
        PreparedStatement  preparedStatement = null;
        //Connection dbConnection = null;

          try{
              //dbConnection = connectionPool.getConnection();//getConnection();
              preparedStatement = dbConnection.prepareStatement(sqlQuery);
             preparedStatement.setInt( 1, id);
             preparedStatement.executeUpdate();
          }catch(SQLException e){
              logger.error("Exception while method removeUserById: " + e.getMessage());
        }finally{
              try{
                  if(preparedStatement != null){preparedStatement.close();}
                  if(dbConnection != null){ dbConnection.close();}
              }catch(SQLException e){
                  logger.error("Exception while method 'removeUserById': " + e.getMessage());
              }
          }
    }
}
