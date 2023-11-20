package com.kvantino.WeatherSensorRestApp.services;

import com.kvantino.WeatherSensorRestApp.models.WeatherSensor;
import com.kvantino.WeatherSensorRestApp.repositories.WeatherSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
public class WeatherSensorService {
    private final WeatherSensorRepository weatherSensorRepository;

    @Autowired
    public WeatherSensorService(WeatherSensorRepository weatherSensorRepository) {
        this.weatherSensorRepository = weatherSensorRepository;
    }

    @Transactional
    public void save(WeatherSensor weatherSensor) {
        enrichWeatherSensor(weatherSensor);
        weatherSensorRepository.save(weatherSensor);
    }

    private void enrichWeatherSensor(WeatherSensor weatherSensor) {
        weatherSensor.setCreatedAt(LocalDateTime.now());
        weatherSensor.setCreatedWho("Admin: Zhora :)");
    }
}
