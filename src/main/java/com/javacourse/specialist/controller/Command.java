package com.javacourse.specialist.controller;

import jakarta.servlet.http.HttpServletRequest;

public interface Command {
    String execute( HttpServletRequest request);
}
