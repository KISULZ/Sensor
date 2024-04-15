package com.monitorsensor.sensor.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "dictionary_code", schema = "public")
public class DictionaryCode implements Serializable {

    @Serial
    private static final long serialVersionUID = -7957841060715470601L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "value_set_id")
    private UUID valueSetId;

    @Column(name = "value_set_name")
    private String valueSetName;

    @Column(name = "value_set_title")
    private String valueSetTitle;

    @Column(name = "element_id")
    private String elementId;

    @Column(name = "display")
    private String display;
}