package org.example;

import org.example.model.Director;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Director.class)
                .addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
//            //task2
//            Director director = session.get(Director.class, 2);
//            System.out.println(director);
//            List<Movie> movies = director.getMovies();
//            System.out.println(movies);
////            task3
//            Movie movie = session.get(Movie.class, 3);
//            System.out.println(movie);
//            Director director = movie.getDirector();
//            System.out.println(director);
////            task4
//            Director director = session.get(Director.class, 4);
//            Movie movie = new Movie("Name",1990,director);
//            director.getMovies().add(movie);
//            session.save(movie);
////            task5
//            Director director = new Director("Name",49);
//            Movie movie = new Movie("NewFilm", 1980,director);
//            director.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//            session.save(director);
//            session.save(movie);
////            task6
//            Movie movie = session.get(Movie.class, 3);
//            Director director = session.get(Director.class, 7);
//            movie.getDirector().getMovies().remove(movie);
//            movie.setDirector(director);
//            director.getMovies().add(movie);
//            task7
            Director director = session.get(Director.class, 1);
            Movie movie = director.getMovies().get(0);
            director.getMovies().remove(movie);
            movie.setDirector(null);
            session.remove(movie);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
