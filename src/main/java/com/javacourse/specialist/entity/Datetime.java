package com.javacourse.specialist.entity;

import java.util.Date;

public class Datetime {

    private int datetimeId;
    private Date date;
    private String dayOfWeek;
    private int procedureStatusId;

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

    public int getProcedureStatusId() {
        return procedureStatusId;
    }

    public void setProcedureStatusId(int procedureStatusId) {
        this.procedureStatusId = procedureStatusId;
    }

    //todo equals hashCode toString
}
