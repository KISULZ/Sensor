package com.monitorsensor.sensor.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RangeDto extends GenericPropertyDto {

    private long id;

    @Valid
    @NotNull(message = "Required field range.rangeFrom cannot be null!")
    private int rangeFrom;

    @Valid
    @NotNull(message = "Required field range.rangeTo cannot be null!")
    private int rangeTo;
}
