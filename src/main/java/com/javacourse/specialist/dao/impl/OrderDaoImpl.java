package com.javacourse.specialist.dao.impl;

import com.javacourse.specialist.connection.ConnectionPool;
import com.javacourse.specialist.dao.OrderDao;
import com.javacourse.specialist.dao.mapper.OrderCreator;
import com.javacourse.specialist.entity.Order;
import com.javacourse.specialist.entity.Procedure;
import com.javacourse.specialist.entity.User;
import com.javacourse.specialist.exception.DaoException;
import com.javacourse.specialist.exception.DatabaseConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.javacourse.specialist.dao.ColumnName.*;

public class OrderDaoImpl implements OrderDao {
    private static final Logger LOGGER = LogManager.getLogger();
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String CREATE_ORDER =
            "INSERT into order (procedure_amount, discount, exect_price, user_id, specialist_id, procedure_id, datetime_id) VALUES(?, ?, ?, ?, ?, ?)";

    private static final String FIND_ORDER_BY_PROCEDURE_TYPE =   // will check
            "SELECT o.order_id, o.procedure_amount, o.discount, o.exect_price, o.user_id, o.specialist_id, o.procedure_id, o.datetime_id" +
                    " FROM orders o JOIN procedure p ON o.procedure_id = p.procedure_id WHERE p.pocedure_type = ?";

    private static final String FIND_ORDER_BY_USER_ID =
            "SELECT order_id, procedure_amount, discount, exect_price, user_id, specialist_id, procedure_id, datetime_id FROM order WHERE user_id=?";

    private static final String FIND_ORDER_BY_DATE =
            "SELECT o.order_id, o.procedure_amount, o.discount, o.exect_price, o.user_id, o.specialist_id, o.procedure_id, o.datetime_id" +
                    " FROM orders o JOIN datetime d ON o.datetime_id = d.datetime_id WHERE d.date = ?";

    private static final String FIND_ORDER_BY_ORDER_ID =
            "SELECT order_id, procedure_amount, discount, exect_price,  user_id, specialist_id, procedure_id, datetime_id FROM orders WHERE order_id=?";

    private static final String FIND_ORDER_BY_SURNAME =  // will check
            "SELECT o.order_id, o.procedure_amount, o.discount, o.exect_price, o.user_id, o.specialist_id, o.procedure_id, o.datetime_id" +
                    " FROM orders o JOIN users u ON o.user_id = u.user_id WHERE u.surname = ?";

    private static final String REMOVE_ORDER_BY_ID = "DELETE FROM orders WHERE  id=?";

    private static final String FIND_PROCEDURE_AMOUNT = "SELECT COUNT(*) as count FROM orders WHERE user_id=?";

    @Override
    public int findProcedureAmountByUserId(int userId) throws DaoException {
        int result = 0;
        try(
                Connection dbConnection = connectionPool.getConnection();
                PreparedStatement preparedStatement = dbConnection.prepareStatement(FIND_PROCEDURE_AMOUNT))
        {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
              result = resultSet.getInt(1);
            }
        }catch(SQLException | DatabaseConnectionException e){
            LOGGER.error("Exception thrown 'findProcedureAmountByUserId' method: " + e);
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public void createOrder(Order order) throws DaoException {
      try (
              Connection dbConnection = connectionPool.getConnection();
              PreparedStatement preparedStatement = dbConnection.prepareStatement(CREATE_ORDER))
      {
          preparedStatement.setInt(1, order.getProcedureAmount());
          preparedStatement.setDouble(2, order.getDiscount());
          preparedStatement.setBigDecimal(3,order.getExectPrice());
          preparedStatement.setInt(4, order.getUserId());
          preparedStatement.setInt(5, order.getSpecialistId());
          preparedStatement.setInt(6, order.getProcedureId());
          preparedStatement.setInt(7, order.getDatetimeId());

          preparedStatement.executeUpdate();
      }catch(SQLException | DatabaseConnectionException e){
          LOGGER.error("Exception thrown 'createOrder' method: " + e);
          throw new DaoException(e);
      }
    }

    @Override
    public List<Order> findOrderByProcedureType(String procedureType) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try(
                Connection dbConnection = connectionPool.getConnection();
                PreparedStatement preparedStatement = dbConnection.prepareStatement(FIND_ORDER_BY_PROCEDURE_TYPE))
        {
          preparedStatement.setString(1, procedureType);
         try(ResultSet resultSet = preparedStatement.executeQuery()) {
             while (resultSet.next()) {
                 Order order = OrderCreator.create(resultSet);
                 orders.add(order);
             }
         }
            }catch(SQLException | DatabaseConnectionException e){
                LOGGER.error("Exception thrown 'findOrderByProcedureType' method: " + e);
                throw new DaoException(e);
        }
        return orders;
        }


    @Override
    public List<Order> findOrderByUserId(int userId) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try(
                Connection dbConnection = connectionPool.getConnection();
                PreparedStatement preparedStatement = dbConnection.prepareStatement(FIND_ORDER_BY_USER_ID))
        {
            preparedStatement.setInt(1, userId);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Order order = OrderCreator.create(resultSet);
                    orders.add(order);
                }
            }
        }catch(SQLException | DatabaseConnectionException e){
            LOGGER.error("Exception thrown 'findOrderByUsersId' method: " +e);
            throw new DaoException(e);
        }
        return orders;
    }

    @Override
    public List<Order> findOrderByDate(LocalDateTime date) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (
                Connection dbConnection = connectionPool.getConnection();
                PreparedStatement preparedStatement = dbConnection.prepareStatement(FIND_ORDER_BY_DATE)) {
            preparedStatement.setString(1, String.valueOf(date));
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Order order = OrderCreator.create(resultSet);
                    orders.add(order);
                }
            }
        } catch (SQLException | DatabaseConnectionException e) {
            LOGGER.error("Exception thrown 'findOrderByDate' method: " + e);
            throw new DaoException(e);
        }
        return orders;
    }

    @Override
    public Optional<Order> findOrderByOrderId(int orderId) throws DaoException {
        Optional<Order> order = Optional.empty();
        try(
                Connection dbConnection = connectionPool.getConnection();
                PreparedStatement preparedStatement = dbConnection.prepareStatement(FIND_ORDER_BY_ORDER_ID))
        {
            preparedStatement.setInt(1, orderId);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Order orderOptional = OrderCreator.create(resultSet);
                    order = Optional.of(orderOptional);
                }
                return order;
            }
        }catch(SQLException | DatabaseConnectionException e){
            LOGGER.error("Exception thrown 'findOrderByOrderId' method: " +e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<Order> findOrderBySurname(String surname) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try(
                Connection dbConnection = connectionPool.getConnection();
                PreparedStatement preparedStatement = dbConnection.prepareStatement(FIND_ORDER_BY_SURNAME))
        {
            preparedStatement.setString(1, surname);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Order order = OrderCreator.create(resultSet);
                    orders.add(order);
                }
            }
        }catch(SQLException | DatabaseConnectionException e){
            LOGGER.error("Exception thrown 'findOrderBySurname' method: " + e);
            throw new DaoException(e);
        }
        return orders;
    }

    @Override
    public boolean removeOrderById(int orderId) throws DaoException {
        boolean isDeleted;
       try(Connection dbConnection = connectionPool.getConnection();
           PreparedStatement preparedStatement = dbConnection.prepareStatement(REMOVE_ORDER_BY_ID))
       {
           preparedStatement.setInt(1, orderId);
           isDeleted = preparedStatement.executeUpdate() == 1;
       }catch(SQLException | DatabaseConnectionException e){
           LOGGER.error("Exception thrown 'removeOrderById' method: " + e);
           throw new DaoException(e);
       }
       return isDeleted;
    }
}
