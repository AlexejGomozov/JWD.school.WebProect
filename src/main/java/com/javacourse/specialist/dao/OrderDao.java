package com.javacourse.specialist.dao;

import com.javacourse.specialist.entity.Order;
import com.javacourse.specialist.entity.Procedure;
import com.javacourse.specialist.entity.User;
import com.javacourse.specialist.exception.DaoException;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderDao {
        public int findProcedureAmountByUserId(int userId) throws DaoException;
        void createOrder(Order order) throws DaoException;
        List<Order> findOrderByProcedureType(String procedureType) throws DaoException;
        List<Order> findOrderByUserId(int userId) throws DaoException;
        List<Order> findOrderByDate(LocalDateTime date) throws DaoException;
        Optional<Order> findOrderByOrderId(int orderId) throws DaoException;
        List<Order> findOrderBySurname(String surname) throws DaoException;
        boolean removeOrderById(int orderId) throws DaoException;
    }
//findProcedureAmountByUserId
