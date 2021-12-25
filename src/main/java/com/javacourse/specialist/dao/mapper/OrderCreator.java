package com.javacourse.specialist.dao.mapper;

import com.javacourse.specialist.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.javacourse.specialist.dao.ColumnName.*;

public class OrderCreator {

    private OrderCreator(){}

    public static Order create(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setProcedureAmount(resultSet.getInt(ORDER_PROCEDURE_AMOUNT));
        order.setDiscount(resultSet.getInt(ORDER_DISCOUNT));
        order.setUserId(resultSet.getInt(USER_ID));
        order.setSpecialistId(resultSet.getInt(SPECIALIST_ID));
        order.setProcedureId(resultSet.getInt(PROCEDURE_ID));
        order.setDatetimeId(resultSet.getInt(DATETIME_ID));

        return order;
    }
}
