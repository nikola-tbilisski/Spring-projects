package ru.alishev.springcourse.models;

import javax.validation.constraints.*;

/**
 * @author Neil Alishev
 */
public class Person {
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Min(value = 1931, message = "Birthdate should be greater or equal to 1930")
    @Max(value = 2014, message = "Birthdate should be equal or less then 2014")
    private int birthdate;

//    @Min(value = 0, message = "Age should be greater than 0")
//    private int age;

//    @NotEmpty(message = "Email should not be empty")
//    @Email(message = "Email should be valid")
//    private String email;

    public Person() {

    }

    public Person(String name, int birthdate) {
        this.name = name;
        this.birthdate = birthdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(int birthdate) {
        this.birthdate = birthdate;
    }
}
