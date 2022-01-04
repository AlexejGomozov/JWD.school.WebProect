package com.javacourse.specialist.controller;

import com.javacourse.specialist.manager.MessageManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.rowset.serial.SerialException;
import java.util.Optional;

@WebServlet(urlPatterns = {"controller", "*.do"})
public class Controller extends HttpServlet {

    public static final String COMMAND = "command";

    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws SecurityException{
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws SecurityException{
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws SecurityException{
        String commandStr = request.getParameter(COMMAND);
        Optional<Command> commandOptional = CommandProvider.defineCommand(commandStr);
        Command command = commandOptional.orElseThrow(IllegalArgumentException::new);
        String page = command.execute(request);

        if (page !=null){
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }else{
            request.getSession().setAttribute("nullPage", MessageManager.EN.getMessage("message.nullpage")); //???
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}
