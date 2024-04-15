package com.monitorsensor.sensor.repository.dao;

import com.monitorsensor.sensor.repository.model.DictionaryCode;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryCodeDao extends JpaRepository<DictionaryCode, Long> {

    Optional<DictionaryCode> findByValueSetIdAndElementId(UUID valueSetId, String elementId);
}
