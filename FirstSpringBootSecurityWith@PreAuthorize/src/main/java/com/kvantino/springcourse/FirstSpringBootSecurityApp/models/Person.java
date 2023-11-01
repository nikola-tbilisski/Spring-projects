package com.kvantino.springcourse.FirstSpringBootSecurityApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "Person")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    @NotEmpty(message = "The name shouldn't be empty")
    @Size(min = 2, max = 100, message = "Should be between 2 to 100 characters")
    private String username;

    @Column(name = "year_of_birth")
    @Min(value = 1900, message = "Should be greater then 1900")
    private int yearOfBirth;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    public Person(String username, int yearOfBirth) {
        this.username = username;
        this.yearOfBirth = yearOfBirth;
    }
}
