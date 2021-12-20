package com.javacourse.specialist.entity;

public class Specialists {

    private int specialistsId;
    private String image;
    private String qualification;

    public Specialists(){}

//    public Specialists(int specialistsId, String image, String qualification){
//        this.specialistsId = specialistsId;
//        this.image = image;
//        this.qualification = qualification;
//    }

    public int getSpecialistsId() {
        return specialistsId;
    }

    public void setSpecialistsId(int specialistsId) {
        this.specialistsId = specialistsId;
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
