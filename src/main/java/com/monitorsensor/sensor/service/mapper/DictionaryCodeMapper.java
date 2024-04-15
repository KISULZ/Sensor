package com.monitorsensor.sensor.service.mapper;

import com.monitorsensor.sensor.repository.model.DictionaryCode;
import com.monitorsensor.sensor.service.dto.DictionaryCodeDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface DictionaryCodeMapper {

    DictionaryCodeDto dictionaryCodeToDictionaryCodeDto(DictionaryCode dictionaryCode);

    DictionaryCode dictionaryCodeDtoToDictionaryCode(DictionaryCodeDto dictionaryCodeDto);
}
