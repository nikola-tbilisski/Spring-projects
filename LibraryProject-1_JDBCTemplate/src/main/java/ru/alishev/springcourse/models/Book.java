package ru.alishev.springcourse.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;

    @NotEmpty(message = "Book name shouldn't be empty")
    @Size(min = 2, max = 50, message = "Book name should be between 2 and 50 characters")
    private String name;
    @NotEmpty(message = "Author name shouldn't be empty")
    @Size(min = 2, max = 30, message = "Author name should be between 2 and 30 characters")
    private String author;

    @Min(value = 1000, message = "წიგნის გამოშვების თარიღი უნდა აღემატებოდეს 1000")
    private int year;

    public Book(){}

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
