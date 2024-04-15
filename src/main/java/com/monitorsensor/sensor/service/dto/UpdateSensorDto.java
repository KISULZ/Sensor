package com.monitorsensor.sensor.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.monitorsensor.sensor.repository.model.Range;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UpdateSensorDto extends GenericPropertyDto {

    private long id;

    @Valid
    @NotNull(message = "Required field sensor.name cannot be null!")
    @Size(max = 30, min = 3, message = "Length of field name cannot be more than 30 and less than 3 character")
    private String name;

    @Valid
    @NotNull(message = "Required field sensor.model cannot be null!")
    @Size(max = 15, message = "Length of field model cannot be more than 15 character")
    private String model;

    @Valid
    @NotNull(message = "Required field sensor.range cannot be null!")
    private Range range;

    @Valid
    @NotNull(message = "Required field sensor.type cannot be null!")
    private DictionaryCodeDto type;

    @Valid
    private DictionaryCodeDto unit;

    @Valid
    @Size(max = 40, message = "Length of field location cannot be more than 40 character")
    private String location;

    @Valid
    @Size(max = 200, message = "Length of field description cannot be more than 200 character")
    private String description;
}
