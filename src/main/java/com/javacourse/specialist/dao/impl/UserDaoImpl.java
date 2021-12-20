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
import java.util.Optional;

public class UserDaoImpl<Connecton> implements UserDAO {
    private static final Logger LOGGER = LogManager.getLogger();
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void addUser(Users user) throws DaoException {
        try(
        Connection  dbConnection = connectionPool.getConnection();
        PreparedStatement  preparedStatement = dbConnection.prepareStatement(
                "INSERT INTO users (name, surname, phone_number, login, password, role, status) VALUES(?, ?, ?, ?, ?, ?, ?)"))
            {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2,user.getSurname());
                preparedStatement.setInt(3, user.getPhoneNumber());
                preparedStatement.setString(4,user.getLogin());
                preparedStatement.setString(5,user.getPassword());
                preparedStatement.setString(6,user.getUserRole().toString());
                preparedStatement.setString(7,user.getRegistrationStatus().toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Exception while method addUser: " + e.getMessage());
        }
    }


    @Override
    public List<Users> findAllUser() throws DaoException {
        List<Users> users = new ArrayList<>();
        try (Connection dbConnection = connectionPool.getConnection();
          Statement  statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT id, name, surname phone_number, login, password, role, status FROM users"))
            {
            while(resultSet.next()){
                Users user = UserCreator.create(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            LOGGER.error("Exception while method getAllUser: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Users> findUserById(int id) throws DaoException {
        Optional<Users> userOptional = Optional.empty();
        try (Connection dbConnection = connectionPool.getConnection();
          PreparedStatement preparedStatement = dbConnection.prepareStatement(
                  "SELECT id, name, surname phone_number, login, password, role, status FROM users WHERE id=?"))
            {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Users user = UserCreator.create(resultSet);
                userOptional = Optional.of(user);
            }
         return userOptional;
        } catch (SQLException e) {
            LOGGER.error("Exception while method getUserById: " + e.getMessage());
            throw new DaoException(e);
        }
    }

    @Override
    public void removeUserById(int id) throws DaoException {
        try (Connection dbConnection = connectionPool.getConnection();
              PreparedStatement  preparedStatement = dbConnection.prepareStatement(
                      "DELETE FROM users WHERE id=?"))
             {
              preparedStatement.setInt( 1, id);
              preparedStatement.executeUpdate();
          }catch(SQLException e){
            LOGGER.error("Exception while method removeUserById: " + e.getMessage());
        }
    }
}
