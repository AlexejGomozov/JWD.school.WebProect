package com.javacourse.specialist.dao.mapper;

import com.javacourse.specialist.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.javacourse.specialist.dao.ColumnName.*;

public class OrderCreator {
    /*
    * private ProcedureCreator(){}

    public static Procedure create(ResultSet resultSet) throws SQLException {
        Procedure procedure = new Procedure();

        //procedureType.setProcedureTypeId(resultSet.getInt(PROCEDURE_TYPE_ID));
        procedure.setDuration(resultSet.getInt(PROCEDURE_DURATION));
        procedure.setPrice(resultSet.getDouble(PROCEDURE_PRICE));
        procedure.setProcedureType(resultSet.getString(PROCEDURE_TYPE));

        return procedure;
    } */


    private OrderCreator(){}

    public static Order create(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setOrderId(resultSet.getInt(ORDER_ID));
        order.setProcedureAmount(resultSet.getInt(ORDER_PROCEDURE_AMOUNT));
        order.setDiscount(resultSet.getInt(ORDER_DISCOUNT));
        order.setUserId(resultSet.getInt(USER_ID));
        order.setSpecialistId(resultSet.getInt(SPECIALIST_ID));
        order.setProcedureId(resultSet.getInt(PROCEDURE_ID));
        order.setDatetimeId(resultSet.getInt(DATETIME_ID));

        return order;
    }
}
