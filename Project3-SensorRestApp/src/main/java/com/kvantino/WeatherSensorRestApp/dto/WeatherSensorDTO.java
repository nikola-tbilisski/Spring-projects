package com.kvantino.WeatherSensorRestApp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherSensorDTO {
    @NotEmpty(message = "Sensor name shouldn't be empty")
    @Pattern(regexp = "\\bSensor-[0-9]{3}\\b", message = "Sensor name should be: Sensor- + 3 digits. for example: Sensor-001")
    private String name;
}
