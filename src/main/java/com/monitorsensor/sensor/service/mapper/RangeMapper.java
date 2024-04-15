package com.monitorsensor.sensor.service.mapper;
import com.monitorsensor.sensor.repository.model.Range;
import com.monitorsensor.sensor.service.dto.RangeDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RangeMapper {

    RangeDto rangeToRangeDto(Range range);

    Range rangeDtoToRange(RangeDto rangeDto);
}
