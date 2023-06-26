package com.example.sensorrest.controllers;

import com.example.sensorrest.dto.SensorDTO;
import com.example.sensorrest.models.Sensor;
import com.example.sensorrest.services.SensorService;
import com.example.sensorrest.util.exceptions.MeasurementErrorResponse;
import com.example.sensorrest.util.exceptions.MeasurementException;
import com.example.sensorrest.util.mappers.SensorMapper;
import com.example.sensorrest.util.validators.SensorValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.example.sensorrest.util.exceptions.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/api/sensors")
@Tag(name = "Sensors Controller", description = "Sensors API")
@RequiredArgsConstructor
public class SensorsController {

    private final SensorService sensorService;
    private final SensorMapper sensorMapper;
    private final SensorValidator sensorValidator;

    @PostMapping()
    @Operation(summary = "Save the sensor")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO,
                                                   BindingResult bindingResult) {
        Sensor sensorToAdd = sensorMapper.convertToSensor(sensorDTO);

        sensorValidator.validate(sensorToAdd, bindingResult);

        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        sensorService.register(sensorToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
