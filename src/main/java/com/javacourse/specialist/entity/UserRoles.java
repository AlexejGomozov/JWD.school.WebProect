package com.javacourse.specialist.entity;

public class UserRoles {

    private int userRolesId;
    private String role;

    public UserRoles(){}

//    public UserRoles(int userRolesId, String role){
//        this.userRolesId = userRolesId;
//        this.role = role;
//    }

    public int getUserRolesId() {
        return userRolesId;
    }

    public void setUserRolesId(int userRolesId) {
        this.userRolesId = userRolesId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    // todo hashCode equals toString
}
