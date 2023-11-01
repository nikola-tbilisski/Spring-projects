package org.example.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Director")
public class Director {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "oscar")
    private String oscar;

    @OneToMany(mappedBy = "movieDirector")
    private List<Movies> movies;

    public Director() {
    }

    public Director(String name, int age, String oscar) {
        this.name = name;
        this.age = age;
        this.oscar = oscar;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOscar() {
        return oscar;
    }

    public void setOscar(String oscar) {
        this.oscar = oscar;
    }

    public List<Movies> getMovies() {
        return movies;
    }

    public void setMovies(List<Movies> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "Director{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", oscar='" + oscar + '\'' +
                '}';
    }
}
