package com.javacourse.specialist.service.impl;

import com.javacourse.specialist.dao.DaoProvider;
import com.javacourse.specialist.dao.OrderDao;
import com.javacourse.specialist.dao.ProcedureDao;
import com.javacourse.specialist.dao.UserDao;
import com.javacourse.specialist.entity.Order;
import com.javacourse.specialist.entity.Procedure;
import com.javacourse.specialist.exception.DaoException;
import com.javacourse.specialist.exception.ServiceException;
import com.javacourse.specialist.service.OrderService;
import com.javacourse.specialist.validator.ProcedureValidator;
import com.javacourse.specialist.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = LogManager.getLogger();
    private final OrderDao orderDao = DaoProvider.getInstance().getOrderDao();
    private final ProcedureDao procedureDao = DaoProvider.getInstance().getProcedureDao();
    private static final int FIVE_PROCEDURES = 5;
    private static final double DISCOUNT_AFTER_FIVE_PROCEDURES = 0.5;
    private static final double NO_DISCOUNT = 1
            ;
   private double findDiscount(int userId) throws ServiceException{
       double discount = NO_DISCOUNT;
       try {
            int procedureAmount = orderDao.findProcedureAmountByUserId(userId);
            if(procedureAmount % FIVE_PROCEDURES == 0) {discount = DISCOUNT_AFTER_FIVE_PROCEDURES;}
        } catch (DaoException e) {
            LOGGER.error("Exception while method 'findDiscount': " + e.getMessage());
            throw new ServiceException(e);
        }
        return discount;
    }

    private BigDecimal findExactPrice (int userId, BigDecimal price) throws ServiceException{
        double discount = findDiscount(userId);
        BigDecimal discountBDecimal = BigDecimal.valueOf(discount);
       return  discountBDecimal.multiply(price);
    }
 // */

    @Override
    public boolean createOrder(int userId, int specialistId, int procedureId, int datetimeId) throws ServiceException {
    //userId, specialistId, procedureId, datetimeId   - int или String ?????????
        Order order = new Order();
     boolean isCreateOrder = true;
        double discount = findDiscount(userId);
        BigDecimal price;
        BigDecimal exactPrice = null;
        int procedureAmount;
        final Validator validator = Validator.getInstance();

        try {
           Optional <Procedure> procedure = procedureDao.findProcedureById(procedureId);
           if(procedure.isPresent()){
            price = procedure.get().getPrice();
            exactPrice = findExactPrice(userId, price);
           }
            procedureAmount = orderDao.findProcedureAmountByUserId(userId) + 1; //"+1" mean с учетом этой процедуры
        }catch(DaoException e){
            throw new ServiceException(e);
        }
                   if(
                        validator.isValid(String.valueOf(userId))&&
                        validator.isValid(String.valueOf(specialistId))&&
                        validator.isValid(String.valueOf(procedureId))&&
                        validator.isValid(String.valueOf(datetimeId)))
                   {

           order.setProcedureAmount(procedureAmount);
           order.setDiscount(discount);
           order.setExectPrice(exactPrice);
           order.setUserId(userId);
           order.setSpecialistId(specialistId);
           order.setProcedureId(procedureId);
           order.setDatetimeId(datetimeId);
       }else {
           return !isCreateOrder;
       }
        return isCreateOrder;
    }

    @Override
    public List<Order> findOrderByProcedureType(String procedureType) throws ServiceException {
       final ProcedureValidator procedureValidator = ProcedureValidator.getInstance();
        if(procedureValidator.isValidName(procedureType)) {
           try {
              return orderDao.findOrderByProcedureType(procedureType);
           } catch (DaoException e) {
               throw new ServiceException(e);
           }
       }
        return Collections.emptyList();
    }

    @Override
    public Optional<Order> findOrderByUsersId(int userId) throws ServiceException {
        final Validator validator = Validator.getInstance();
        if(validator.isValidId(String.valueOf(userId))){
           try {
               return orderDao.findOrderByOrderId(userId);
           } catch (DaoException e) {
               throw new ServiceException(e);
           }
       }
        return Optional.empty();
    }

    @Override
    public List<Order> findOrderByDate(LocalDateTime date) throws ServiceException {
        final Validator validator = Validator.getInstance();
        if(validator.isValidId(String.valueOf(date))) {
            try {
                return orderDao.findOrderByDate(date);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Order> findOrderByOrderId(int orderId) throws ServiceException {
        final Validator validator = Validator.getInstance();
       if(validator.isValidId(String.valueOf(orderId))) {
           try {
               return orderDao.findOrderByOrderId(orderId);
           } catch (DaoException e) {
               throw new ServiceException(e);
           }
       }
        return Optional.empty();
    }

    @Override
    public List<Order> findOrderBySurname(String surname) throws ServiceException {
        final Validator validator = Validator.getInstance();
       if(validator.isValidSurname(surname)) {
           try {
               return orderDao.findOrderBySurname(surname);
           } catch (DaoException e) {
               throw new ServiceException(e);
           }
       }
        return Collections.emptyList();
    }

    @Override
    public boolean removeOrderById(int orderId) throws ServiceException {
        final Validator validator = Validator.getInstance();
        boolean isDeleted = true;
        if(validator.isValidId(String.valueOf(orderId))) {
            try {
                return orderDao.removeOrderById(orderId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
       return !isDeleted;
    }
}
