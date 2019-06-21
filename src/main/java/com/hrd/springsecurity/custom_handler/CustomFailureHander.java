package com.hrd.springsecurity.custom_handler;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomFailureHander implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        String message = "";
        if (e.getClass().isAssignableFrom(BadCredentialsException.class)) {
            message = "Incorrect username or password";
        } else {
            message = "Error Login";
        }
        System.out.println(message);
        httpServletRequest.getSession().setAttribute("message", message);

        httpServletResponse.sendRedirect("/login");

    }
}
