package com.javacourse.specialist.command;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = {"controller", "*.do"})
public class Controller extends HttpServlet {

    public static final String COMMAND = "command";

    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String commandStr = request.getParameter(COMMAND);
        Optional<Command> commandOptional = CommandProvider.defineCommand(commandStr);
        Command command = commandOptional.orElseThrow(IllegalArgumentException::new);
        String page = command.execute(request, response);

        if (page !=null){
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
