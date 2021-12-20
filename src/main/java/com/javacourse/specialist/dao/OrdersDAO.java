package com.javacourse.specialist.dao;

import com.javacourse.specialist.entity.Orders;
import com.javacourse.specialist.entity.ProcedureType;
import com.javacourse.specialist.exception.DaoException;

import java.util.Date;
import java.util.List;

public interface OrdersDAO {

        List<Orders> findOrdersByProcedureType(ProcedureType procedureType) throws DaoException;
        List<Orders> findOrdersByUsersId(int usersId) throws DaoException;
        List<Orders> findOrdersByDate(Date date) throws DaoException;
        //List<Orders> findOrdersByDayOfWeek
        Orders findOrderByOrdersId(int ordersId) throws DaoException;
        String findOrderBySurname(String surname) throws DaoException;
        Orders createOrderByProcedureType(ProcedureType procedureType) throws DaoException;
        Orders createOrderByDate(Date date) throws DaoException;
    }

