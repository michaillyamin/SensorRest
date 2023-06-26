package com.example.sensorrest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Measurement DTO")
public class MeasurementDTO {

    @NotNull
    @Min(-100)
    @Max(100)
    @Schema(description = "Measurement value")
    private Double value;

    @NotNull
    @Schema(description = "Measurement raining")
    private Boolean raining;

    @NotNull
    @Schema(description = "Measurement sensor")
    private SensorDTO sensor;
}
