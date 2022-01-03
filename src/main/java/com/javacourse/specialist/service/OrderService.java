package com.javacourse.specialist.service;

import com.javacourse.specialist.entity.Order;
import com.javacourse.specialist.exception.DaoException;
import com.javacourse.specialist.exception.ServiceException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    boolean createOrder(int userId, int specialistId, int procedureId, int datetimeId) throws ServiceException;
    List<Order> findOrderByProcedureType(String procedureType) throws ServiceException;
    Optional<Order> findOrderByUsersId(int userId) throws ServiceException;
    List<Order> findOrderByDate(LocalDateTime date) throws ServiceException;
    Optional<Order> findOrderByOrderId(int orderId) throws ServiceException;
    List<Order> findOrderBySurname(String surname) throws ServiceException;
    boolean removeOrderById(int orderId) throws ServiceException;

}




