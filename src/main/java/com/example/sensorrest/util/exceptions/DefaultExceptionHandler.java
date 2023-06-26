package com.example.sensorrest.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(MeasurementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseEntity<MeasurementErrorResponse> handleMeasurementException(MeasurementException e) {

        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
