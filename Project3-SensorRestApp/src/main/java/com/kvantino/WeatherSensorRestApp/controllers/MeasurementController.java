package com.kvantino.WeatherSensorRestApp.controllers;

import com.kvantino.WeatherSensorRestApp.dto.MeasurementDTO;
import com.kvantino.WeatherSensorRestApp.dto.MeasurementToSendDTO;
import com.kvantino.WeatherSensorRestApp.models.Measurement;
import com.kvantino.WeatherSensorRestApp.services.MeasurementService;
import com.kvantino.WeatherSensorRestApp.util.EntityNotCreatedException;
import com.kvantino.WeatherSensorRestApp.util.ErrorResponse;
import com.kvantino.WeatherSensorRestApp.util.MeasurementValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementController(MeasurementService measurementService, MeasurementValidator measurementValidator, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<MeasurementToSendDTO> getAllMeasurements() {
        return measurementService.findAll().stream()
                .map(this::convertModelToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public Integer getRainyDaysCount() {
        return measurementService.findRainyDays();
    }

    //Get method with direct uri of Sensor name
//    @GetMapping("/{name}")
//    public List<MeasurementToSendDTO> showByName(@PathVariable("name") String name) {
//        return measurementService.findByName(name).stream()
//                .map(this::convertModelToDto)
//                .collect(Collectors.toList());
//    }

    //Get method with sensor parameter: ?name=Sensor name
    @GetMapping("/sensor")
    public List<MeasurementToSendDTO> showByName(@RequestParam("name") String name) {
        return measurementService.findByName(name).stream()
                .map(this::convertModelToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {
        measurementValidator.validate(measurementDTO, bindingResult);

        ErrorResponse.reportErrors(bindingResult);

        measurementService.save(convertDtoToModel(measurementDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Measurement convertDtoToModel(MeasurementDTO measurementDTO) {
        //Convert DTO to Measurement class with setters & getters (exclude WeatherSensor)
//        Measurement measurement = new Measurement();
//        measurement.setSensorName(measurementDTO.getWeatherSensor().getName());
//        measurement.setValue(measurementDTO.getValue());
//        measurement.setRaining(measurementDTO.getRaining());
//
//        return measurement;

        //Convert via ModelMapper & exclude WeatherSensor & map sensorName from WeatherSensorDTO
        // Create a new instance of ModelMapper to ensure a clean configuration
        ModelMapper InnerModelMapper = new ModelMapper();

        // Create a PropertyMap to customize the mapping
        InnerModelMapper.addMappings(new PropertyMap<MeasurementDTO, Measurement>() {
            @Override
            protected void configure() {
                // Map sensorName from measurementDTO weatherSensor manually & skip weatherSensor from mapping
                map().setSensorName(source.getWeatherSensor().getName());
                skip().setWeatherSensor(null);
            }
        });

        return InnerModelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementToSendDTO convertModelToDto(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementToSendDTO.class);
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
