package com.javacourse.specialist.dao.mapper;

import com.javacourse.specialist.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.javacourse.specialist.dao.ColumnName.*;

public class OrderCreator {
     private static OrderCreator instance;
    private OrderCreator(){}
      public static OrderCreator getInstance(){
        if(instance == null){
            instance = new OrderCreator();
        }
        return instance;
      }
    public Order create(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setOrderId(resultSet.getInt(ORDER_ID));
        order.setProcedureAmount(resultSet.getInt(ORDER_PROCEDURE_AMOUNT));
        order.setDiscount(resultSet.getInt(ORDER_DISCOUNT));
        order.setExectPrice(resultSet.getBigDecimal(ORDER_EXECT_PRICE));
        order.setUserId(resultSet.getInt(USER_ID));
        order.setSpecialistId(resultSet.getInt(SPECIALIST_ID));
        order.setProcedureId(resultSet.getInt(PROCEDURE_ID));
        order.setDatetimeId(resultSet.getInt(DATETIME_ID));

        return order;
    }
}
