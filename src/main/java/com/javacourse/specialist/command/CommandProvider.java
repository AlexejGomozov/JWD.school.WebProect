package com.javacourse.specialist.command;

import com.javacourse.specialist.command.impl.*;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import static com.javacourse.specialist.command.CommandType.*;



public class CommandProvider {

     private static CommandProvider instance;
     private static EnumMap<CommandType, Command> commands = new EnumMap(CommandType.class);

     public static CommandProvider getInstance(){
     if(instance == null){
         instance = new CommandProvider();
       }
     return instance;
     }

     public CommandProvider(){
         commands.put(SIGN_IN, new SignIn());
         commands.put(ADD_USER, new AddUser());
         commands.put(REGISTRATION, new Registration());
         commands.put(ADD_ORDER, new AddOrder());
         commands.put(WRONG_REQUEST, new WrongRequest());
     }


     public static Optional<Command> defineCommand(String commandStr){

         CommandType commandType =null;
         Optional <Command> command;
         commandType = CommandType.valueOf(commandStr.toUpperCase());

         command = Optional.ofNullable(commands.get(commandType));

         return command;
     }
}
