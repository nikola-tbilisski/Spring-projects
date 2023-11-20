package com.kvantino.WeatherSensorRestApp.services;

import com.kvantino.WeatherSensorRestApp.models.WeatherSensor;
import com.kvantino.WeatherSensorRestApp.repositories.WeatherSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class WeatherSensorValidatorService {

    private final WeatherSensorRepository weatherSensorRepository;

    @Autowired
    public WeatherSensorValidatorService(WeatherSensorRepository weatherSensorRepository) {
        this.weatherSensorRepository = weatherSensorRepository;
    }

    public Optional<WeatherSensor> loadWeatherSensorByName(String name) {
        return weatherSensorRepository.findByName(name);
    }
}
