package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "Movies")
public class Movies {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "movie_title")
    private String movie_title;

    @Column(name = "production_date")
    private int production_date;

    @Column(name = "oscar")
    private String oscar;

    @ManyToOne
    @JoinColumn(name = "director_id", referencedColumnName = "id")
    private Director movieDirector;

    public Movies() {
    }

    public Movies(String movie_title, int production_date, String oscar, Director movieDirector) {
        this.movie_title = movie_title;
        this.production_date = production_date;
        this.oscar = oscar;
        this.movieDirector = movieDirector;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public int getProduction_date() {
        return production_date;
    }

    public void setProduction_date(int production_date) {
        this.production_date = production_date;
    }

    public String getOscar() {
        return oscar;
    }

    public void setOscar(String oscar) {
        this.oscar = oscar;
    }

    public Director getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(Director movieDirector) {
        this.movieDirector = movieDirector;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "id=" + id +
                ", movie_title='" + movie_title + '\'' +
                ", production_date=" + production_date +
                ", oscar='" + oscar + '\'' +
                '}';
    }
}
