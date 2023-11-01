package com.kvantino.FirstRestApp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //@RestController annotation is: @Controller + @ResponseBody for all methods in a Controller class
@RequestMapping("/api")
public class FirstRESTController {

    //@ResponseBody // comment @ResponseBody if you use @RestController
    @GetMapping("/sayHello")
    public String sayHello() {
        return "Hello from First REST app.";
    }
}
