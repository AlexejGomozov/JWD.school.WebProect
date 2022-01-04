package com.javacourse.specialist.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandProvider {

     private static Map<String, Command> commands = new HashMap<>();

     public CommandProvider(){
         commands.put("", );
         commands.put("", );
         commands.put("", );
         commands.put("", );
     }
     public static Optional<Command> defineCommand(String commandStr){
         Optional <Command> command;
         command = Optional.ofNullable(commands.get(commandStr));

         return command;
     }
}
