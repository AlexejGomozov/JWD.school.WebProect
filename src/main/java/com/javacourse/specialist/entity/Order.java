package com.javacourse.specialist.entity;

public class Order {
    private int orderId;
    private int procedureAmount;
    private int discount;
    private int userId;
    private int specialistId;
    private int procedureTypeId;
    private int datetimeId;

    public Order(){}

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProcedureAmount() {
        return procedureAmount;
    }

    public void setProcedureAmount(int procedureAmount) {
        this.procedureAmount = procedureAmount;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(int specialistId) {
        this.specialistId = specialistId;
    }

    public int getProcedureTypeId() {
        return procedureTypeId;
    }

    public void setProcedureTypeId(int procedureTypeId) {
        this.procedureTypeId = procedureTypeId;
    }

    public int getDatetimeId() {
        return datetimeId;
    }

    public void setDatetimeId(int datetimeId) {
        this.datetimeId = datetimeId;
    }

    //todo equals hashcode toString
}
