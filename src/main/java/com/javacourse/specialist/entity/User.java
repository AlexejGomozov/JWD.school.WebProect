package com.javacourse.specialist.entity;

public class User {
    private int id;
    private String name;
    private String surname;
    private String phoneNumber;
    //private String login;
    private String password;
    private UserRoles role;
    private RegistrationStatus status;

    public enum UserRoles{
        ADMIN, CLIENT
    }
    public enum RegistrationStatus{
        REGISTERED,  NOT_REGISTERED   // IN_PROCESS,
    }

    public User(){}
    public User(UserRoles role){}

    public int getId(){return id;}
    public String getName(){return name;}
    public String getSurname(){return surname;}
    public String getPhoneNumber(){return phoneNumber;}
    //public String getLogin(){return login;}
    public String getPassword(){return password;}
    public UserRoles getUserRole(){return role;}
    public RegistrationStatus getRegistrationStatus(){return status;}

    public void setId(int id){this.id = id;}
    public void  setName(String name){this.name = this.name;}
    public void  setSurname(String surname){this.surname = surname;}
    public void   setPhoneNumber(String phoneNumber){this.phoneNumber = phoneNumber;}
   // public void  setLogin(String login){this.login = login;}
    public void  setPassword(String password){this.password = password;}
    public void setUserRole(UserRoles role){this.role = role;}
    public void setRegistrationStatus(RegistrationStatus status){this.status = status;}

    //todo equals toString hashCode
}
