package com.javacourse.specialist.dao;

public final class ColumnName {
    /* users table */
    public static final String USER_ID = "id";
    public static final String NAME = "name";
    public static final String SURMAME = "surname";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String USER_ROLE = "role";
    public static final String REGISTRATION_STATUS = "status";
    /* roles table */
   // public static final String USER_ROLE_ID = "id";
   // public static final String USER_ROLE = "role";
    /* roles status */
   // public static final String REGISTRATION_STATUS_ID = "id";
   // public static final String REGISTRATION_STATUS = "status";
    /* specialist table*/
    public static final String SPECIALIST_ID = "id";
    public static final String SPECIALIST_IMAGE = "image";
    public static final String SPECEALIST_QUALIFICATION = "qualification";
    /* procedure type table */
    public static final String PROCEDURE_ID = "id";
    public static final String PROCEDURE_DURATION = "duration";
    public static final String PROCEDURE_PRICE = "price";
    public static final String PROCEDURE_TYPE = "procedure_type";
    /* procedure_status table */
   // public static final String PROCEDURE_STATUS_ID = "id";
    //public static final String PROCEDURE_STATUS = "status";
    /* orders table */
    public static final String ORDER_ID = "id";
    public static final String ORDER_PROCEDURE_AMOUNT = "amount";
    public static final String ORDER_DISCOUNT = "discount";
    /* datetime table */
    public static final String DATETIME_ID = "id";
    public static final String DATETIME_DATE = "date";
    public static final String DATETIME_DAY_OF_WEEK = "day";
    public static final String PROCEDURE_STATUS = "status";

    private ColumnName(){}
}

