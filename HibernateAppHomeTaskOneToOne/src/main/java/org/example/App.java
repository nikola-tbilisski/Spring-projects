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
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Principal.class)
                .addAnnotatedClass(School.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            //Create Principal and link him to the new School
//            Principal principal = new Principal("David", 56);
//            principal.setSchool(new School(161));
//            session.save(principal);

            //Get any Principal and then get his School
//            Principal principal = session.get(Principal.class, 3);
//            System.out.println(principal.getSchool());

            //Get any of the Schools and then get its Principal
//            School school = session.get(School.class, 4);
//            System.out.println(school.getSchoolPrincipal().getName());

            //Change Principal for any school
//            School school = session.get(School.class, 3);
//            Principal principal = new Principal("Chichiko", 57);
//            school.setSchoolPrincipal(principal);
//            session.save(principal);

            //Try to add another School for any Principal and read the error
            Principal principal = session.get(Principal.class, 6);
            principal.setSchool(new School(567));

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
