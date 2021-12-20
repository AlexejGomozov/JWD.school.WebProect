package com.javacourse.specialist.entity;

public class Orders {
    private int ordersId;
    private int procedureAmount;
    private int discount;
    private int usersId;
    private int specialistId;
    private int procedureTypeId;
    private int datetimeId;

    public Orders(){}

    public int getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(int ordersId) {
        this.ordersId = ordersId;
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

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
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
