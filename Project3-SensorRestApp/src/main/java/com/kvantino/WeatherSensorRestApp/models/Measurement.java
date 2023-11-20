package com.kvantino.WeatherSensorRestApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Measurements")
@Getter
@Setter
@NoArgsConstructor
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @NotEmpty
    @Column(name = "sensor_name")
    private String sensorName;

    @NotNull
    @Min(value = -100, message = "Degree value should be greater then -100")
    @Max(value = 100, message = "Degree value should be less then 100")
    @Column(name = "value")
    private Double value;

    @NotNull
    @Column(name = "raining")
    private Boolean raining;

    @NotNull
    @Column(name = "measurement_date")
    private LocalDateTime measurementDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private WeatherSensor weatherSensor;

    public Measurement(String sensorName, Double value, Boolean raining, LocalDateTime measurementDate, WeatherSensor weatherSensor) {
        this.sensorName = sensorName;
        this.value = value;
        this.raining = raining;
        this.measurementDate = measurementDate;
        this.weatherSensor = weatherSensor;
    }
}
