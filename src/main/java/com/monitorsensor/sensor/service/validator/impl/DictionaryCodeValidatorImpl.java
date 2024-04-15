package com.monitorsensor.sensor.service.validator.impl;

import com.monitorsensor.sensor.repository.model.Sensor;
import com.monitorsensor.sensor.service.validator.DictionaryCodeValidator;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DictionaryCodeValidatorImpl implements DictionaryCodeValidator {

    @Override
    public void validate(Sensor sensor) {
        UUID typeUUID = UUID.fromString("b1553b2e-f193-4020-86e0-5e561d409863");
        UUID unitUUID = UUID.fromString("e82b4419-c789-435e-b7ab-300513ad12db");
        if (!sensor.getType().getValueSetId().equals(typeUUID)) {
            throw new RuntimeException("Error validate sensor.type");
        }
        if(sensor.getUnit() != null){
            if (!sensor.getUnit().getValueSetId().equals(unitUUID)) {
                throw new RuntimeException("Error validate sensor.unit");
            }
        }
    }
}
