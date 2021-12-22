package com.javacourse.specialist.entity;

public class UserRole {

    private int userRoleId;
    private String role;

    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_CLIENT = "client";

    public UserRole(){}

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRolesId) {
        this.userRoleId = userRoleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // todo hashCode equals toString
}
