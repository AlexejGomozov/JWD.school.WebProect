package com.javacourse.specialist.entity;

public class RegistrationStatus {

    private int registrationStatusId;
    private String status;

    public RegistrationStatus(){}

//    public RegistrationStatus(int registrationStatusId, String status){
//        this.registrationStatusId = registrationStatusId;
//        this.status = status;
//    }

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
