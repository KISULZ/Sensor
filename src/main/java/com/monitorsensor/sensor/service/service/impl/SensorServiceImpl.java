package com.monitorsensor.sensor.service.service.impl;

import com.monitorsensor.sensor.repository.dao.DictionaryCodeDao;
import com.monitorsensor.sensor.repository.dao.RangeDao;
import com.monitorsensor.sensor.repository.dao.SensorDao;
import com.monitorsensor.sensor.repository.model.DictionaryCode;
import com.monitorsensor.sensor.repository.model.Range;
import com.monitorsensor.sensor.repository.model.Sensor;
import com.monitorsensor.sensor.service.dto.SensorDto;
import com.monitorsensor.sensor.service.dto.UpdateSensorDto;
import com.monitorsensor.sensor.service.mapper.SensorMapper;
import com.monitorsensor.sensor.service.service.SensorService;
import com.monitorsensor.sensor.service.validator.DictionaryCodeValidator;
import com.monitorsensor.sensor.service.validator.RangeValidator;
import com.monitorsensor.sensor.service.dto.DictionaryCodeDto;
import com.monitorsensor.sensor.service.validator.SensorValidator;
import io.micrometer.common.util.StringUtils;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {

    private final SensorMapper sensorMapper;
    private final SensorDao sensorDao;
    private final RangeDao rangeDao;
    private final RangeValidator rangeValidator;
    private final SensorValidator sensorValidator;
    private final DictionaryCodeValidator dictionaryCodeValidator;
    private final DictionaryCodeDao dictionaryCodeDao;

    @Override
    @Transactional
    public SensorDto add(final SensorDto sensorDto) {
        final Sensor sensor = sensorMapper.sensorDtoToSensor(sensorDto);

        rangeValidator.validate(sensor);
        sensorValidator.validate(sensor);
        dictionaryCodeValidator.validate(sensor);

        final DictionaryCodeDto typeDto = sensorDto.getType();
        final DictionaryCode type = pull(typeDto);
        sensor.setType(type);

        if(sensor.getUnit() != null) {
            final DictionaryCodeDto unitDto = sensorDto.getUnit();
            final DictionaryCode unit = pull(unitDto);
            sensor.setUnit(unit);
        }

        final Sensor savedSensor = sensorDao.save(sensor);
        return sensorMapper.sensorToSensorDto(savedSensor);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Optional<Sensor> optionalSensor = sensorDao.findById(id);
        if (optionalSensor.isEmpty()) {
            throw new RuntimeException("Error sensor");
        }
        final Sensor sensor = optionalSensor.get();
        Optional<Range> optionalRange = rangeDao.findById(sensor.getRange().getId());
        sensorDao.deleteById(id);
        if (optionalRange.isEmpty()) {
            throw new RuntimeException("Error sensor");
        }
        rangeDao.deleteById(sensor.getRange().getId());
    }

    @Override
    @Transactional
    public Optional<SensorDto> update(Long id, UpdateSensorDto updateSensorDto) {
        final Optional<Sensor> optionalSensor = sensorDao.findById(id);
        if (optionalSensor.isEmpty()) {
            throw new RuntimeException("Error sensor");
        }
        Sensor databaseProcess = optionalSensor.get();

        rangeValidator.validate(databaseProcess);
        sensorValidator.validate(databaseProcess);
        dictionaryCodeValidator.validate(databaseProcess);

        SensorDto sensorDto = sensorMapper.updateSensorDtoToSensorDto(updateSensorDto);
        final DictionaryCodeDto typeDto = sensorDto.getType();
        final DictionaryCode type = pull(typeDto);
        databaseProcess.setType(type);

        if(updateSensorDto.getUnit() != null) {
            final DictionaryCodeDto unitDto = sensorDto.getUnit();
            final DictionaryCode unit = pull(unitDto);
            databaseProcess.setUnit(unit);
        }
        databaseProcess.setName(sensorDto.getName());
        databaseProcess.setModel(sensorDto.getModel());
        databaseProcess.setRange(sensorDto.getRange());
        if(StringUtils.isNotEmpty(sensorDto.getLocation())) {
            databaseProcess.setLocation(sensorDto.getLocation());
        }
        if(StringUtils.isNotEmpty(sensorDto.getDescription())) {
            databaseProcess.setDescription(sensorDto.getDescription());
        }

        Sensor savedProcess = sensorDao.save(databaseProcess);
        return Optional.of(sensorMapper.sensorToSensorDto(savedProcess));
    }

    @Override
    public List<SensorDto> getAll() {
        List<SensorDto> sensorDtoList = sensorDao.findAll().stream()
                .map(sensorMapper::sensorToSensorDto)
                .collect(Collectors.toList());

        return sensorDtoList;
    }

    @Override
    public SensorDto getById(Long id) {
        Optional<Sensor> sensorDto = sensorDao.findById(id);

        return sensorMapper.sensorToSensorDto(sensorDto.get());
    }

    @Override
    public List<SensorDto> getByParam(String model, String name) {
        List<SensorDto> sensorDto = sensorDao.findByParam(model, name).stream()
                .map(sensorMapper::sensorToSensorDto)
                .collect(Collectors.toList());

        return sensorDto;
    }

    private DictionaryCode pull(final DictionaryCodeDto dictionaryCodeDto) {
        Optional<DictionaryCode> optionalDictionaryCode = dictionaryCodeDao.findByValueSetIdAndElementId(
                dictionaryCodeDto.getValueSetId(),
                dictionaryCodeDto.getElementId()
        );
        if (optionalDictionaryCode.isPresent()) {
            return optionalDictionaryCode.get();
        } else {
            return null;
        }
    }
}
