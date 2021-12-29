package com.javacourse.specialist.dao;

import com.javacourse.specialist.dao.impl.OrderDaoImpl;
import com.javacourse.specialist.dao.impl.ProcedureDaoImpl;
import com.javacourse.specialist.dao.impl.UserDaoImpl;
import com.javacourse.specialist.entity.Procedure;

public class DaoProvider {
    private UserDao userDao = new UserDaoImpl();
    private OrderDao orderDao = new OrderDaoImpl();
    private ProcedureDao procedureDao = new ProcedureDaoImpl();

    private DaoProvider(){}

    private static class DaoProviderHolder{
        private static final DaoProvider instance = new DaoProvider();
    }
    public static DaoProvider getInstance(){
        return DaoProviderHolder.instance;
    }
    public UserDao getUserDao(){
        return userDao;
    }
    public OrderDao getOrderDao(){
        return orderDao;
    }
    public ProcedureDao getProcedureDao(){
        return procedureDao;
    }
}
