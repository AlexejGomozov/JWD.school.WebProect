package com.javacourse.specialist.entity;

import java.math.BigDecimal;

public class Procedure {
    private int procedureId;
    private String pocedureType;
    private int duration;
    private BigDecimal price;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    //todo equals hashCode toString
}
