package com.javacourse.specialist.entity;

import java.util.Date;

public class Datetime {

    private int datetimeId;
    private Date date; // int ?
    private String dayOfWeek;
    private int procedureStatusId;

    public Datetime(){}

//    public Datetime(int datetimeId, Date date, String dayOfWeek, int procedureStatusId){
//        this.datetimeId = datetimeId;
//        this.date = date;
//        this.dayOfWeek = dayOfWeek;
//        this.procedureStatusId = procedureStatusId;
//    }

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

    public int getProcedureStatusId() {
        return procedureStatusId;
    }

    public void setProcedureStatusId(int procedureStatusId) {
        this.procedureStatusId = procedureStatusId;
    }
    //todo equals hashCode toString
}
