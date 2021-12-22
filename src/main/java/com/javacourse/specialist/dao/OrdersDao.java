package com.javacourse.specialist.dao;

import com.javacourse.specialist.entity.Order;
import com.javacourse.specialist.entity.ProcedureType;
import com.javacourse.specialist.exception.DaoException;

import java.util.Date;
import java.util.List;

public interface OrdersDao {
        void addOrder(Order order) throws DaoException;
        List<Order> findOrdersByProcedureType(ProcedureType procedureType) throws DaoException;
        List<Order> findOrdersByUsersId(int usersId) throws DaoException;
        List<Order> findOrdersByDate(Date date) throws DaoException;
        //List<Orders> findOrdersByDayOfWeek
        Order findOrderByOrdersId(int ordersId) throws DaoException;
        String findOrderBySurname(String surname) throws DaoException;
        Order createOrderByProcedureType(ProcedureType procedureType) throws DaoException;
        Order createOrderByDate(Date date) throws DaoException;
        void removeOrderById(int orderId) throws DaoException;
    }

