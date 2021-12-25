package com.javacourse.specialist.entity;

import java.util.List;

public class Specialist {

    private int specialistId;
    private String image;
    private String qualification;
    private String name;
    private List<String> listProcedures;
    private double workExperience;

    public Specialist(){}

    public int getSpecialistsId() {
        return specialistId;
    }

    public void setSpecialistsId(int specialistsId) {
        this.specialistId = specialistId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getListProcedures() {
        return listProcedures;
    }

    public void setListProcedures(List<String> listProcedures) {
        this.listProcedures = listProcedures;
    }

    public double getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(double workExperience) {
        this.workExperience = workExperience;
    }

    //todo equals hashCode toString
}
