package com.javacourse.specialist.command;

import com.javacourse.specialist.command.impl.*;

public enum CommandType {


    SIGN_IN(new SignIn()),
    ADD_USER(new AddUser()),
    REGISTRATION(new Registration()),
    ADD_ORDER(new AddOrder()),
    WRONG_REQUEST(new WrongRequest());

    Command command;

    CommandType(Command command){
        this.command = command;
    }
    public Command getCommand(){
     return command;
    }
}
