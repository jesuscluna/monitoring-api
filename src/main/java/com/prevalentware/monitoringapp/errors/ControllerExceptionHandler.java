package com.prevalentware.monitoringapp.errors;

import org.joda.time.DateTime;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDetail> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ExceptionDetail message = new ExceptionDetail(HttpStatus.NOT_FOUND.value(), new DateTime().toString(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NotAcceptableException.class)
    public ResponseEntity<ExceptionDetail> notAcceptableException(NotAcceptableException ex, WebRequest request) {
        ExceptionDetail message = new ExceptionDetail(HttpStatus.NOT_ACCEPTABLE.value(), new DateTime().toString(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionDetail> unauthorizedException(UnauthorizedException ex, WebRequest request) {
        ExceptionDetail message = new ExceptionDetail(HttpStatus.UNAUTHORIZED.value(), new DateTime().toString(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ExceptionDetail> forbiddenException(ForbiddenException ex, WebRequest request) {
        ExceptionDetail message = new ExceptionDetail(HttpStatus.FORBIDDEN.value(), new DateTime().toString(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException .class)
    public ResponseEntity<ExceptionDetail> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        ExceptionDetail message = new ExceptionDetail(HttpStatus.BAD_REQUEST.value(), new DateTime().toString(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDetail> internalServerException(InternalServerException ex, WebRequest request) {
        ExceptionDetail message = new ExceptionDetail(HttpStatus.INTERNAL_SERVER_ERROR.value(), new DateTime().toString(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ExceptionDetail>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionDetail message = new ExceptionDetail(status.value(), new DateTime().toString(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionDetail message = new ExceptionDetail(status.value(), new DateTime().toString(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorMessages = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .toList();
        ExceptionDetail message = new ExceptionDetail(status.value(), new DateTime().toString(), String.join(", ", errorMessages), request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionDetail message = new ExceptionDetail(status.value(), new DateTime().toString(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
