package com.kvantino.WeatherSensorRestApp.util;

public class EntityNotCreatedException extends RuntimeException {
    public EntityNotCreatedException(String msg) {
        super(msg);
    }
}
