package com.kvantino.WeatherSensorRestApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Sensors")
@Getter
@Setter
@NoArgsConstructor
public class WeatherSensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Sensor name shouldn't be empty")
    @Pattern(regexp = "\\bSensor-[0-9]{3}\\b", message = "Sensor name pattern is: Sensor- + 3 digits. for example: Sensor-001")
    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_who")
    @NotEmpty(message = "Admin name who implemented this sensor shouldn't be empty")
    private String createdWho;

    @OneToMany(mappedBy = "weatherSensor")
    private List<Measurement> measurements;

    public WeatherSensor(String name) {
        this.name = name;
    }
}
