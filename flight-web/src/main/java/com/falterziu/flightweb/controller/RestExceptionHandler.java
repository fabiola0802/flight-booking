package com.falterziu.flightweb.controller;

import com.falterziu.flightdata.exception.FlightAppBadRequestException;
import com.falterziu.flightdata.exception.FlightAppNotFoundException;
import com.falterziu.flightdata.util.CustomErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.PersistenceException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {FlightAppBadRequestException.class })
    protected ResponseEntity<CustomErrorResponse> handleBadRequestException(RuntimeException ex) {
        return generateResponse(ex, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = { PersistenceException.class })
    protected ResponseEntity<CustomErrorResponse> handlePersistenceException(PersistenceException ex) {
        return generateResponse("There was a problem saving data", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {FlightAppNotFoundException.class })
    protected ResponseEntity<CustomErrorResponse> handleNotFoundException(RuntimeException ex) {

        return generateResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { RuntimeException.class })
    protected ResponseEntity<CustomErrorResponse> handleGenericException(RuntimeException ex) {
        return generateResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<CustomErrorResponse> generateResponse(RuntimeException ex, HttpStatus status) {
        return generateResponse(ex.getMessage(), status);
    }

    private ResponseEntity<CustomErrorResponse> generateResponse(String errorMessage, HttpStatus status) {
        CustomErrorResponse response = new CustomErrorResponse();
        response.setStatus(status.value());
        response.setMessage(Collections.singletonList(errorMessage));
        response.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(response, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorResponse response = new CustomErrorResponse();
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        response.setStatus(status.value());
        response.setTimestamp(LocalDateTime.now());
        response.setMessage(errors);
        return handleExceptionInternal(ex, response, headers, status, request);
    }
}
