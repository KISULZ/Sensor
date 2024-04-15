package com.monitorsensor.sensor.service.validator.impl;

import com.monitorsensor.sensor.repository.model.Sensor;
import com.monitorsensor.sensor.service.dto.SensorDto;
import com.monitorsensor.sensor.service.validator.RangeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RangeValidatorImpl implements RangeValidator {

    @Override
    public void validate(Sensor sensor) {
        if (sensor.getRange().getRangeTo() < 0 ||
                sensor.getRange().getRangeFrom() < 0 ||
                sensor.getRange().getRangeFrom() >= sensor.getRange().getRangeTo()
        ) {
            throw new RuntimeException("Error validate sensor.range");
        }
    }
}
