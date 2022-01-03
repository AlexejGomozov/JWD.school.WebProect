package com.javacourse.specialist.dao;

public final class ColumnName {
    /* users table */
    public static final String USER_ID = "id";
    public static final String NAME = "name";
    public static final String SURMAME = "surname";
    public static final String PHONE_NUMBER = "phone_number";
    //public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String USER_ROLE = "role";
    public static final String REGISTRATION_STATUS = "status";
    /* specialist table */
    public static final String SPECIALIST_ID = "id";
    public static final String SPECIALIST_IMAGE = "image";
    public static final String SPECIALIST_QUALIFICATION = "qualification";
    public static final String SPECIALIST_NAME = "name";
    public static final String SPECIALIST_LIST_PROCEDURES = "list_procedures";
    public static final String SPECIALIST_WORK_EXPERIENCE = "work_experience";
    /* procedure type table */
    public static final String PROCEDURE_ID = "id";
    public static final String PROCEDURE_DURATION = "duration";
    public static final String PROCEDURE_PRICE = "price";
    public static final String PROCEDURE_TYPE = "procedure_type";
    /* orders table */
    public static final String ORDER_ID = "id";
    public static final String ORDER_PROCEDURE_AMOUNT = "amount";
    public static final String ORDER_DISCOUNT = "discount";
    public static final String ORDER_EXECT_PRICE = "exect_price";
    /* datetime table */
    public static final String DATETIME_ID = "id";
    public static final String DATETIME_DATE = "date";
    public static final String DATETIME_DAY_OF_WEEK = "day";
    public static final String PROCEDURE_STATUS = "status";

    private ColumnName(){}
}

