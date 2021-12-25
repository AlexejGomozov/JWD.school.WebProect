package com.javacourse.specialist.entity;

import java.util.Date;

public class Datetime {

    private int datetimeId;
    private Date date;
    private String dayOfWeek;
    private ProcedureStatus status;


    public enum ProcedureStatus{
        OPEN, CLOSED
    }

    public Datetime(){}

    public int getDatetimeId() {
        return datetimeId;
    }

    public void setDatetimeId(int datetimeId) {
        this.datetimeId = datetimeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public ProcedureStatus getProcedureStatus() {
        return status;
    }

    public void setProcedureStatuS(ProcedureStatus status) {
        this.status = status;
    }

    //todo equals hashCode toString
}
