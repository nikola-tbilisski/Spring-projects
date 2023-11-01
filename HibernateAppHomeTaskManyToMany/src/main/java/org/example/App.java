package org.example;

import org.example.model.Actor;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        //try with resources
        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

//            //Create Movie, create two Actors and link them
//            Movie movie = new Movie("Pulp fiction", 1994);
//            Actor actor1 = new Actor("Harvey Ketel", 81);
//            Actor actor2 = new Actor("Samuel L. Jackson", 72);
//            movie.setActors(new ArrayList<>(List.of(actor1, actor2)));
//            actor1.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//            actor2.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//            session.save(movie);
//            session.save(actor1);
//            session.save(actor2);

//            //Get actors list from any Movie in DB
//            Movie movie = session.get(Movie.class, 1);
//            System.out.println(movie.getActors());

//            //Get Movie list from any Actor in DB
//            Actor actor = session.get(Actor.class, 2);
//            System.out.println(actor.getMovies());

//            //Add new movie for one of the Actors and link it to actor
//            Movie movie = new Movie("Django Unchained", 2012);
//            Actor actor = session.get(Actor.class, 2);
//            movie.setActors(new ArrayList<>(Collections.singletonList(actor)));
//            actor.getMovies().add(movie);
//            session.save(movie);

            //Delete one of the movie
            Actor actor = session.get(Actor.class, 1);
            System.out.println(actor.getMovies());
            Movie movieToRemove = actor.getMovies().get(0);
            actor.getMovies().remove(0);
            movieToRemove.getActors().remove(actor);


            session.getTransaction().commit();

        }
    }
}
