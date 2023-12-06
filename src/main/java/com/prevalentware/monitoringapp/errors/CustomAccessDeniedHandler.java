package com.prevalentware.monitoringapp.errors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prevalentware.monitoringapp.constants.Constants;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper;


    public CustomAccessDeniedHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");
        ExceptionDetail exceptionDetail = new ExceptionDetail(HttpStatus.FORBIDDEN.value(), new DateTime().toString(), Constants.ERROR_FORBIDDEN, request.getRequestURI());
        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(exceptionDetail));
        writer.flush();
    }
}
