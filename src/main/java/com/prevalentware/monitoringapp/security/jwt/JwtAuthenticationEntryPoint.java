package com.prevalentware.monitoringapp.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prevalentware.monitoringapp.constants.Constants;
import com.prevalentware.monitoringapp.errors.ExceptionDetail;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    public JwtAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setContentType("application/json");
        ExceptionDetail exceptionDetail = new ExceptionDetail(HttpStatus.UNAUTHORIZED.value(), new DateTime().toString(), Constants.ERROR_UNAUTHORIZED, e.getMessage());
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(objectMapper.writeValueAsString(exceptionDetail));
        writer.flush();
    }
}