package com.javacourse.specialist.dao.impl;
import com.javacourse.specialist.connection.ConnectionPool;
import com.javacourse.specialist.dao.UserDao;
import com.javacourse.specialist.dao.mapper.UserCreator;
import com.javacourse.specialist.entity.User;
import com.javacourse.specialist.exception.DaoException;
import com.javacourse.specialist.exception.DatabaseConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl<Connecton> implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger();
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String ADD_USER = "INSERT INTO users (name, surname, phone_number, login, password, role, status) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_ALL_USERS = "SELECT id, name, surname phone_number, login, password, role, status FROM users";
    private static final String FIND_USER_BY_ID = "SELECT id, name, surname phone_number, login, password, role, status FROM users WHERE id=?";
    private static final String REMOVE_USER_BY_ID = "DELETE FROM users WHERE id=?";

    @Override
    public void addUser(User user) throws DaoException {
        try(
        Connection  dbConnection = connectionPool.getConnection();
        PreparedStatement  preparedStatement = dbConnection.prepareStatement(ADD_USER))
            {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2,user.getSurname());
                preparedStatement.setInt(3, user.getPhoneNumber());
                preparedStatement.setString(4,user.getLogin());
                preparedStatement.setString(5,user.getPassword());
                preparedStatement.setString(6,user.getUserRole().toString());
                preparedStatement.setString(7,user.getRegistrationStatus().toString());

            preparedStatement.executeUpdate();
        } catch (SQLException | DatabaseConnectionException e) {
            LOGGER.error("Exception while method addUser: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public List<User> findAllUsers() throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection dbConnection = connectionPool.getConnection();
          Statement  statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS))
            {
            while(resultSet.next()){
                User user = UserCreator.create(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException | DatabaseConnectionException e) {
            LOGGER.error("Exception while method getAllUser: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<User> findUserById(int id) throws DaoException {
        Optional<User> userOptional = Optional.empty();
        try (Connection dbConnection = connectionPool.getConnection();
          PreparedStatement preparedStatement = dbConnection.prepareStatement(FIND_USER_BY_ID))
            {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                User user = UserCreator.create(resultSet);
                userOptional = Optional.of(user);
            }
         return userOptional;
        } catch (SQLException  | DatabaseConnectionException e) {
            LOGGER.error("Exception while method getUserById: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public void removeUserById(int id) throws DaoException {
        try (Connection dbConnection = connectionPool.getConnection();
              PreparedStatement  preparedStatement = dbConnection.prepareStatement(REMOVE_USER_BY_ID))
             {
              preparedStatement.setInt( 1, id);
              preparedStatement.executeUpdate();
          }catch(SQLException | DatabaseConnectionException e){
            LOGGER.error("Exception while method removeUserById: " + e.getMessage());
            throw new DaoException(e);
        }
    }
}
