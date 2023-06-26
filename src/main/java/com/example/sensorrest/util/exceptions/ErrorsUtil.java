package com.example.sensorrest.util.exceptions;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorsUtil {

    public static void returnErrorsToClient(BindingResult bindingResult) {
        StringBuilder errorMessage = new StringBuilder();

        List<FieldError> errors = bindingResult.getFieldErrors();

        errors.forEach(error -> errorMessage
                .append(error.getField())
                .append(" - ").append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
                .append(";"));

        throw new MeasurementException(errorMessage.toString());
    }
}
