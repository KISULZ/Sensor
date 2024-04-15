package com.monitorsensor.sensor.service.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@JsonDeserialize
public abstract class GenericPropertyDto implements Serializable {

    @JsonIgnore
    private final Map<String, Object> properties = new HashMap<>();

    @JsonAnySetter
    public void setProperties(String key, Object value) {
        properties.put(key, value);
    }

    @JsonIgnore
    public boolean isHasWrongFields() {
        if (!properties.isEmpty()) {
            properties.put("message", "Found wrong field(s) - " + properties);
            return true;
        } else {
            return false;
        }
    }
}