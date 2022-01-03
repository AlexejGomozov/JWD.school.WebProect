package com.javacourse.specialist.entity;

import java.math.BigDecimal;

public class Order {
    private int orderId;
    private int procedureAmount;
    private double discount;
    BigDecimal exectPrice;
    private int userId;
    private int specialistId;
    private int procedureId;
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

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public BigDecimal getExectPrice() {
        return exectPrice;
    }

    public void setExectPrice(BigDecimal exectPrice) {
        this.exectPrice = exectPrice;
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

    public int getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(int procedureId) {
        this.procedureId = procedureId;
    }

    public int getDatetimeId() {
        return datetimeId;
    }

    public void setDatetimeId(int datetimeId) {
        this.datetimeId = datetimeId;
    }

    //todo equals hashcode toString
}
