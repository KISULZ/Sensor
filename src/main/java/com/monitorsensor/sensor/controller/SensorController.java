package com.monitorsensor.sensor.controller;

import com.monitorsensor.sensor.service.dto.SensorDto;
import com.monitorsensor.sensor.service.dto.UpdateSensorDto;
import com.monitorsensor.sensor.service.service.SensorService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(
        value = "/sensor",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
@Slf4j
public class SensorController {

    private final SensorService sensorService;

    @Operation(summary= "add Sensor")
    @PreAuthorize("hasAnyRole('dev','user')")
    @PostMapping("/addSensor")
    public ResponseEntity<Object> add(
            @RequestBody @Validated SensorDto sensorDto,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return ResponseEntity.status(500).body(result.getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toSet()).toString());
        } else {
            try {
                final SensorDto process = sensorService.add(sensorDto);
                return ResponseEntity.status(201).body(process);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return ResponseEntity.status(500).body("Server error! Contact to administrator.");
            }
        }
    }

    @Operation(summary= "remove Sensor")
    @PreAuthorize("hasAnyRole('dev','user')")
    @PatchMapping("/delete")
    public ResponseEntity<Object> patchStatus(@RequestParam("id") Long id) {
        try {
            sensorService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(500).body("Server error! Contact to administrator.");
        }
    }

    @Operation(summary= "Update Sensor")
    @PreAuthorize("hasAnyRole('dev','user')")
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(
            @Param("SensorDto") @RequestBody @Validated UpdateSensorDto SensorDto,
            @Param("id") @RequestParam("id") Long id,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return ResponseEntity.status(500).body(result.getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toSet()).toString());
        } else {
            try {
                final Optional<SensorDto> optionalSensorDto = sensorService.update(id, SensorDto);
                return ResponseEntity.status(201).body(optionalSensorDto);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return ResponseEntity.status(500).body("Server error! Contact to administrator.");
            }
        }
    }

    @Operation(summary= "Get all Sensor")
    @PreAuthorize("hasAnyRole('dev','user')")
    @GetMapping(value = "/all")
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.status(200).body(sensorService.getAll());
    }

    @Operation(summary= "Get by id Sensor")
    @PreAuthorize("hasAnyRole('dev','user')")
    @GetMapping(value = "/byId")
    public ResponseEntity<Object> getById(
            @RequestParam("id") Long id
    ) {
        try {
            return ResponseEntity.status(200).body(sensorService.getById(id));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(500).body("Server error! Contact to administrator.");
        }
    }

    @Operation(summary= "Get by param Sensor")
    @PreAuthorize("hasAnyRole('dev','user')")
    @GetMapping(value = "/param")
    public ResponseEntity<Object> getParam(
            @RequestParam("model") String model,
            @RequestParam("name") String name
    ) {
        try {
            return ResponseEntity.status(200).body(sensorService.getByParam(model, name));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(500).body("Server error! Contact to administrator.");
        }
    }
}
