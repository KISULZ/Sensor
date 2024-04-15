package com.monitorsensor.sensor.repository.dao;

import com.monitorsensor.sensor.repository.model.Sensor;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SensorDao extends JpaRepository<Sensor, Long> {

    Optional<Sensor> findById(@NonNull Long id);
    void deleteById(Long id);

    @Query(nativeQuery = true, value = """
             select *
             from sensor
             where name Like CONCAT('%', :name, '%')
             and model Like CONCAT('%', :model, '%')
            """
    )
    List<Sensor> findByParam(
            @Param("model") String model,
            @Param("name") String name
    );
}
