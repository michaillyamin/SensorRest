package com.example.sensorrest.util.mappers;

import com.example.sensorrest.dto.SensorDTO;
import com.example.sensorrest.models.Sensor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SensorMapper {

    private final ModelMapper modelMapper;

    public Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

}
