package com.monitorsensor.sensor.repository.dao;

import com.monitorsensor.sensor.repository.model.Range;
import lombok.NonNull;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RangeDao extends JpaRepository<Range, Long> {

    Optional<Range> findById(@NonNull Long id);
    void deleteById(Long id);
}
