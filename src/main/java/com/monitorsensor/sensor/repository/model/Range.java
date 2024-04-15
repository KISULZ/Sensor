package com.monitorsensor.sensor.repository.model;


import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "range")
public class Range implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "rangeFrom")
    private int rangeFrom;

    @Column(name = "rangeTo")
    private int rangeTo;
    
}
