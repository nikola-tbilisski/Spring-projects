package org.example;

import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

//            Person person = session.get(Person.class, 1);
//            System.out.println(person.getName() + " " + person.getAge());

            for (int i = 1; i < 10001; i++) {
                session.persist(new Person("Person" + i, i * 10));
            }

//            Person person1 = new Person("Test1", 30);
//            Person person2 = new Person("Test2", 40);
//            Person person3 = new Person("Test3", 50);
//            session.persist(person1);
//            session.persist(person2);
//            session.persist(person3);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
