package com.javacourse.specialist.entity;

public class UserRoles {

    private int userRolesId;
    private String role;

    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_CLIENT = "client";

    public UserRoles(){}

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
