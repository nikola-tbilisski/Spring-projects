package com.kvantino.WeatherSensorRestApp.controllers;

import com.kvantino.WeatherSensorRestApp.dto.WeatherSensorDTO;
import com.kvantino.WeatherSensorRestApp.models.WeatherSensor;
import com.kvantino.WeatherSensorRestApp.services.WeatherSensorService;
import com.kvantino.WeatherSensorRestApp.util.EntityNotCreatedException;
import com.kvantino.WeatherSensorRestApp.util.ErrorResponse;
import com.kvantino.WeatherSensorRestApp.util.WeatherSensorValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensors")
public class WeatherSensorController {
    private final WeatherSensorService weatherSensorService;
    private final WeatherSensorValidator weatherSensorValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public WeatherSensorController(WeatherSensorService weatherSensorService, WeatherSensorValidator weatherSensorValidator, ModelMapper modelMapper) {
        this.weatherSensorService = weatherSensorService;
        this.weatherSensorValidator = weatherSensorValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid WeatherSensorDTO weatherSensorDTO, BindingResult bindingResult) {

        weatherSensorValidator.validate(weatherSensorDTO, bindingResult);

        ErrorResponse.reportErrors(bindingResult);

        weatherSensorService.save(convertDtoToModel(weatherSensorDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private WeatherSensor convertDtoToModel(WeatherSensorDTO weatherSensorDTO) {
        return modelMapper.map(weatherSensorDTO, WeatherSensor.class);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(EntityNotCreatedException e) {
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
