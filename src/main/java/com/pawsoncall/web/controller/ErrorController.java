package com.pawsoncall.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;

@Controller
public class ErrorController {
    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @GetMapping("/error")
    public String error(HttpServletRequest request) {
        Object status = request.getAttribute("jakarta.servlet.error.status_code");
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error_404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error_500";
            }
        }
        String message = (String) request.getSession().getAttribute("error.message");
        if (message != null) {
            logger.warn(message);
        }
        return "error";
    }
}
