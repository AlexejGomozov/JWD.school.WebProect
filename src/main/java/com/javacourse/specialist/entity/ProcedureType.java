package com.javacourse.specialist.entity;

public class ProcedureType {
    private int procedureTypeId;
    private String pocedureType;
    private int duration;
    private double price;

    public ProcedureType(){}

//    public ProcedureType(int procedureTypeId, int duration, double price){
//        this.procedureTypeId = procedureTypeId;
//        this.duration = duration;
//        this.price = price;
//    }

    public int getProcedureTypeId() {
        return procedureTypeId;
    }

    public void setProcedureTypeId(int procedureTypeId) {
        this.procedureTypeId = procedureTypeId;
    }

    public String getProcedureType(){
        return pocedureType;
    }

    public void setProcedureType(String procedureType){
        this.pocedureType = procedureType;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    //todo equals hashCode toString
}
