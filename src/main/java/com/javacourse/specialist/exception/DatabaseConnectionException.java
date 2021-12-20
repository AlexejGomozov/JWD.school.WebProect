package com.javacourse.specialist.exception;

public class DatabaseConnectionException extends Exception{
    public DatabaseConnectionException(){}
    public DatabaseConnectionException(String message){
    }
    public DatabaseConnectionException(String message, Throwable cause){
        super(message, cause);
    }
    public DatabaseConnectionException(Throwable cause){
        super(cause);
    }
}
