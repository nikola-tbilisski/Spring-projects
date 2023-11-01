package com.kvantino.springcourse.FirstSpringBootSecurityApp.util;

import com.kvantino.springcourse.FirstSpringBootSecurityApp.models.Person;
import com.kvantino.springcourse.FirstSpringBootSecurityApp.services.PersonValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PersonValidatorService personValidatorService;

    @Autowired
    public PersonValidator(PersonValidatorService personValidatorService) {
        this.personValidatorService = personValidatorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personValidatorService.loadUserByUsername(person.getUsername()).isPresent())
            errors.rejectValue("username", "", "Username already in use");
    }
}
