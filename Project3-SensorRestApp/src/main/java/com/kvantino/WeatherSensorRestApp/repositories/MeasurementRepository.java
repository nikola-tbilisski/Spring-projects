package com.kvantino.WeatherSensorRestApp.repositories;

import com.kvantino.WeatherSensorRestApp.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    List<Measurement> findByRainingIsTrue();

    List<Measurement> findBySensorName(String name);
}
