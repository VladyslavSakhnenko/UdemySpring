package org.example;

import org.example.model.Principal;
import org.example.model.School;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Principal.class)
                .addAnnotatedClass(School.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
////            task1
//            Principal principal = session.get(Principal.class,1);
//            System.out.println(principal);
//            School school = principal.getSchool();
//            System.out.println(school);
////            task2
//            School school = session.get(School.class, 1);
//            System.out.println(school);
//            Principal principal = school.getPrincipal();
//            System.out.println(principal);
////            task3
//            School school = new School(123);
//            Principal principal = new Principal("Tom",65);
//            principal.setSchool(school);
//            session.save(principal);
////            task4
//            School school = session.get(School.class,1);
//            Principal principal = new Principal("Jack", 77);
//            session.remove(school.getPrincipal());
//            principal.setSchool(school);
//            session.save(principal);
////            task5 find reason of the error
//            School school = new School(11);
//            Principal principal =  session.get(Principal.class, 1);
//            principal.setSchool(school);
//            session.save(school);
////            ERROR: duplicate key value violates unique constraint "school_principal_id_key"
////             because principal_id is UNIQ in db
            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
