package com.monitorsensor.sensor.service.validator;

import com.monitorsensor.sensor.repository.model.Sensor;

public interface RangeValidator {

    void validate(Sensor sensor);
}
