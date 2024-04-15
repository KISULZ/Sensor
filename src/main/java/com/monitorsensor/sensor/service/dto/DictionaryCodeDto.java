package com.monitorsensor.sensor.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DictionaryCodeDto extends GenericPropertyDto {

    @JsonIgnore
    private Long id;

    @NotNull(message = "Required field valueSetId not found!")
    private UUID valueSetId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String valueSetName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String valueSetTitle;

    @NotBlank(message = "Required field elementId cannot be empty!")
    @Size(max = 255, message = "Length of field elementId cannot be more than 255 character")
    private String elementId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String display;
}
