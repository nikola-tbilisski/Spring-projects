package org.example.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    @Column(name = "year_of_production")
    private int yearOfProduction;

    @ManyToMany(mappedBy = "movies")
    private List<Actor> actors;

    public Movie(){}

    public Movie(String name, int yearOfProduction) {
        this.name = name;
        this.yearOfProduction = yearOfProduction;
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

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != movie.id) return false;
        if (yearOfProduction != movie.yearOfProduction) return false;
        return Objects.equals(name, movie.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + yearOfProduction;
        return result;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", yearOfProduction=" + yearOfProduction +
                '}';
    }
}
