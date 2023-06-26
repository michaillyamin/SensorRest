package com.example.sensorrest.util.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MeasurementErrorResponse {
    private String message;
    private long timestamp;
}
