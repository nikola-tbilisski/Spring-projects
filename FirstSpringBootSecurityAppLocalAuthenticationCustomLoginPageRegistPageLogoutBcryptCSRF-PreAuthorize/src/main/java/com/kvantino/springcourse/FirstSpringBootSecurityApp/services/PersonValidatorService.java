package com.kvantino.springcourse.FirstSpringBootSecurityApp.services;

import com.kvantino.springcourse.FirstSpringBootSecurityApp.models.Person;
import com.kvantino.springcourse.FirstSpringBootSecurityApp.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonValidatorService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonValidatorService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Optional<Person> loadUserByUsername(String username) {
        return peopleRepository.findByUsername(username);
    }
}
