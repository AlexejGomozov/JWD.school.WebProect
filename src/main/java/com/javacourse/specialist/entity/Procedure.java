package com.javacourse.specialist.entity;

public class Procedure {
    private int procedureId;
    private String pocedureType;
    private int duration;
    private double price;

    public Procedure(){}

    public int getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(int procedureId) {
        this.procedureId = procedureId;
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
