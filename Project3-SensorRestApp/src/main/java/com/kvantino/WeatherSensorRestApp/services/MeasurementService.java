package com.kvantino.WeatherSensorRestApp.services;

import com.kvantino.WeatherSensorRestApp.models.Measurement;
import com.kvantino.WeatherSensorRestApp.repositories.MeasurementRepository;
import com.kvantino.WeatherSensorRestApp.repositories.WeatherSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;

    private final WeatherSensorRepository weatherSensorRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, WeatherSensorRepository weatherSensorRepository) {
        this.measurementRepository = measurementRepository;
        this.weatherSensorRepository = weatherSensorRepository;
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    public Integer findRainyDays() {
        return measurementRepository.findByRainingIsTrue().size();
    }

    public List<Measurement> findByName(String name) {
        return measurementRepository.findBySensorName(name);
    }

    @Transactional
    public void save(Measurement measurement) {
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    private void enrichMeasurement(Measurement measurement) {
        measurement.setMeasurementDate(LocalDateTime.now());
        measurement.setWeatherSensor(weatherSensorRepository.findByName(measurement.getSensorName()).orElse(null));
        //System.out.println(measurement.getSensorName());
    }
}
