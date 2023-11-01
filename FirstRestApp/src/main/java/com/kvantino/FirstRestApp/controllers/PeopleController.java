package com.kvantino.FirstRestApp.controllers;

import com.kvantino.FirstRestApp.models.Person;
import com.kvantino.FirstRestApp.services.PeopleService;
import com.kvantino.FirstRestApp.util.PersonErrorResponse;
import com.kvantino.FirstRestApp.util.PersonNotCreatedException;
import com.kvantino.FirstRestApp.util.PersonNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //@RestController annotation is: @Controller + @ResponseBody for all methods in a Controller class
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping()
    public List<Person> getPeople() {
        return peopleService.findAll(); //Here this method with Jackson parses this list of Person
        // objects in to JSON format and returns it.

    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") int id) {
        return peopleService.findOne(id); //Here Jackson parses founded Person object in to JSON format
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder erMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError er : errors) {
                erMsg.append(er.getField())
                        .append(" - ")
                        .append(er.getDefaultMessage())
                        .append("; ");
            }

            throw new PersonNotCreatedException(erMsg.toString());
        }

        peopleService.save(person);

        //returns Http response with empty body and status 200(ok)
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e) {
        PersonErrorResponse response = new PersonErrorResponse(
                "Person with this id wasn't found",
                System.currentTimeMillis()
        );

        //In HTTP response will be the body of the "response" & Http status of 404 (NOT_FOUND)
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException e) {
        PersonErrorResponse response = new PersonErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
