package com.monitorsensor.sensor.service.validator;

import com.monitorsensor.sensor.repository.model.Sensor;

public interface SensorValidator {

    void validate(Sensor sensor);
}
