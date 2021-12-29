package com.javacourse.specialist.service;

import com.javacourse.specialist.service.impl.OrderServiceImpl;
import com.javacourse.specialist.service.impl.ProcedureServiceImpl;
import com.javacourse.specialist.service.impl.UserServiceImpl;

public class ServiceProvider {
    private UserService userService = new UserServiceImpl();
    private ProcedureService procedureService = new ProcedureServiceImpl();
    private OrderService orderService = new OrderServiceImpl();

    private ServiceProvider(){}

    private static class ServiceProviderHolder{
        private static final ServiceProvider instance = new ServiceProvider();
    }
    public static ServiceProvider getInstance(){
        return ServiceProviderHolder.instance;
    }
    public UserService getUserService(){
        return userService;
    }
    public ProcedureService getProcedureService(){
        return procedureService;
    }
    public OrderService getOrderService(){
        return orderService;
    }
}
