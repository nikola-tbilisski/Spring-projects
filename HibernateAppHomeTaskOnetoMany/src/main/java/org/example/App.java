package org.example;

import org.example.model.Director;
import org.example.model.Movies;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.Collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Director.class)
                .addAnnotatedClass(Movies.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            //get director and get his movies
//            Director director = session.get(Director.class, 4);
//            System.out.println(director);
//            System.out.println(director.getMovies());

            //get Movie and then get his director
//            Movies movies = session.get(Movies.class, 5);
//            System.out.println(movies);
//            System.out.println(movies.getMovieDirector());

//            //Add one more Movie for any director
//            Director director = session.get(Director.class, 2);
//            Movies movie = new Movies("The Irishman", 2019, "No", director);
//            //for hibernate cache normal functionality
//            director.getMovies().add(movie);
//            //hibernate save command in DB
//            session.save(movie);

//            //Create new director, his movie and link them together
//            Director director = new Director("Alejandro Amenábar", 51, "Yes");
//            Movies movie = new Movies("Agora", 2009, "No", director);
//            director.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//            session.save(director);
//            session.save(movie);

//            //Swap directors for movie
//            Movies movies = session.get(Movies.class, 8);
//            Director oldDirector = movies.getMovieDirector();
//            Director newDirector = session.get(Director.class, 1);
//            oldDirector.getMovies().remove(movies);
//            movies.setMovieDirector(newDirector);
//            session.save(movies);

            //delete one movie for any director
            Director director = session.get(Director.class, 2);
            //List<Movies> moviesList = director.getMovies();
            Movies movie = director.getMovies().stream().filter(movies -> movies.getId() == 9).findAny().orElse(null);
            //Hibernate command to remove movie
            session.remove(movie);
            //Hibernate cache to function correctly
            director.getMovies().remove(movie);

            System.out.println(movie);

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
