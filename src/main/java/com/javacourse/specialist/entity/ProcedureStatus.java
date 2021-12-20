package com.javacourse.specialist.entity;

public class ProcedureStatus {

    private int procedureStatusId;
    private String status;

    public static final String STATUS_ACTIVE = "active";
    public static final String STATUS_INACTIVE = "inactive";

    public ProcedureStatus(){}

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
