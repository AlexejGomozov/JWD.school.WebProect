package com.javacourse.specialist.entity;

public class RegistrationStatus {

    private int registrationStatusId;
    private String status;

    public static final String STATUS_IN_PROCESS = "in_process";
    public static final String STATUS_REGISTERED = "registered";
    public static final String STATUS_NOT_REGISTERED = "not_registered";

    public RegistrationStatus(){}

    public int getRegistrationStatusId() {
        return registrationStatusId;
    }

    public void setRegistrationStatusId(int registrationStatusId) {
        this.registrationStatusId = registrationStatusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //todo equals hashCode toString
}
