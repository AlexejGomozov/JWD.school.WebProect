package com.javacourse.specialist.entity;

public class Specialist {

    private int specialistId;
    private String image;
    private String qualification;

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

    //todo equals hashCode toString
}
