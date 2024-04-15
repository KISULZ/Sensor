package com.monitorsensor.sensor.service.mapper;

import com.monitorsensor.sensor.repository.model.Sensor;
import com.monitorsensor.sensor.service.dto.SensorDto;
import com.monitorsensor.sensor.service.dto.UpdateSensorDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {
        RangeMapper.class,
        DictionaryCodeMapper.class
        },
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SensorMapper {

    SensorDto sensorToSensorDto(Sensor sensor);

    Sensor sensorDtoToSensor(SensorDto sensorDto);

    SensorDto updateSensorDtoToSensorDto(UpdateSensorDto sensorDto);
}
