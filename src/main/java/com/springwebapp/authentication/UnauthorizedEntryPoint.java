package com.springwebapp.authentication;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class is invoked whenever the {@link TokenAuthenticationFilter} deems
 * an incoming request as UN_AUTHORIZED.
 */
@Component
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        System.err.println(" UNAUTHORIZED ACCESS!!! ");
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
