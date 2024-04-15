package com.monitorsensor.sensor.service.validator.impl;

import com.monitorsensor.sensor.repository.model.Sensor;
import com.monitorsensor.sensor.service.validator.SensorValidator;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SensorValidatorImpl implements SensorValidator {

    @Override
    public void validate(Sensor sensor) {
        if (sensor.getName().length() > 30 || sensor.getName().length() < 3) {
            throw new RuntimeException("Error validate sensor.name");
        }

        if (sensor.getModel().length() > 15) {
            throw new RuntimeException("Error validate sensor.model");
        }

        if(StringUtils.isNotEmpty(sensor.getLocation())) {
            if (sensor.getLocation().length() > 40) {
                throw new RuntimeException("Error validate sensor.location");
            }
        }

        if(StringUtils.isNotEmpty(sensor.getDescription())) {
            if (sensor.getDescription().length() > 200) {
                throw new RuntimeException("Error validate sensor.description");
            }
        }
    }
}
