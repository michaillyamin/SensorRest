package com.example.sensorrest.controllers;

import com.example.sensorrest.dto.MeasurementDTO;
import com.example.sensorrest.dto.MeasurementsResponse;
import com.example.sensorrest.models.Measurement;
import com.example.sensorrest.services.MeasurementService;
import com.example.sensorrest.util.mappers.MeasurementMapper;
import com.example.sensorrest.util.validators.MeasurementValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static com.example.sensorrest.util.exceptions.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/api/measurements")
@Tag(name = "Measurements Controller", description = "Measurements API")
@RequiredArgsConstructor
public class MeasurementsController {

    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;
    private final MeasurementMapper measurementMapper;

    @PostMapping()
    @Operation(summary = "Save incoming measurement")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO,
                                          BindingResult bindingResult) {

        Measurement measurementToAdd = measurementMapper.convertToMeasurement(measurementDTO);

        measurementValidator.validate(measurementToAdd, bindingResult);
        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        measurementService.addMeasurement(measurementToAdd);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    @Operation(summary = "Get all measurements")
    public MeasurementsResponse getMeasurements() {
        return new MeasurementsResponse(measurementService.findAll().stream().map(measurementMapper::convertToMeasurementDTO)
                .collect(Collectors.toList()));
    }


    @GetMapping("/rainyDaysCount")
    @Operation(summary = "Get the number of rainy days")
    public Long getRainyDaysCount() {
        return measurementService.findAll().stream().filter(Measurement::getRaining).count();
    }

}
