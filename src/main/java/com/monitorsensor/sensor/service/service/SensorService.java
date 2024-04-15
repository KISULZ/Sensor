package com.monitorsensor.sensor.service.service;

import com.monitorsensor.sensor.service.dto.SensorDto;
import com.monitorsensor.sensor.service.dto.UpdateSensorDto;
import java.util.List;
import java.util.Optional;

public interface SensorService {

    SensorDto add(final SensorDto sensorDto);

    void delete(Long id);

    List<SensorDto> getAll();

    SensorDto getById(Long id);

    List<SensorDto> getByParam(String model, String name);

    Optional<SensorDto> update(Long id, UpdateSensorDto sensorDto);
}
