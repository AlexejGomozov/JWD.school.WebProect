package com.javacourse.specialist.entity;

public class ProcedureStatus {

    private int procedureStatusId;
    private String status;

    public ProcedureStatus(){}

//    public ProcedureStatus(int procedureStatusId, String status){
//        this.procedureStatusId = procedureStatusId;
//        this.status = status;
//    }

    public int getProcedureStatusId() {
        return procedureStatusId;
    }

    public void setProcedureStatusId(int procedureStatusId) {
        this.procedureStatusId = procedureStatusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    //todo equals hashCode toString
}
