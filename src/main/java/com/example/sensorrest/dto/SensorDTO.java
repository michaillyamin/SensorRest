package com.example.sensorrest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Sensor DTO")
public class SensorDTO {

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 3, max = 30, message = "Sensor name must be from 3 to 30 characters")
    @Schema(description = "Sensor name")
    private String name;
}
