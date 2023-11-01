package org.example;

import org.example.model.Item;
import org.example.model.Person;
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
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

//            Person person = session.get(Person.class, 3);
//            System.out.println(person);
//
//            List<Item> items = person.getItems();
//            System.out.println(items);
//

//            Item item = session.get(Item.class, 4);
//            System.out.println(item);
//
//            Person person = item.getOwner();
//            System.out.println(person);

//            Person person = session.get(Person.class, 2);
//            Item newItem = new Item("Item from hibernate", person);
//            person.getItems().add(newItem);
//            session.save(newItem);

//            Person person = new Person("Kilian", 30);
//            Item newItem = new Item("Fifa ball", person);
//            person.setItems(new ArrayList<>(Collections.singletonList(newItem)));
//            session.save(person);
//            session.save(newItem);

//            Person person = session.get(Person.class, 3);
//            List<Item> items = person.getItems();
//            for(Item el : items)
//                session.remove(el);
//            person.getItems().clear();

//            Person person = session.get(Person.class,2);
//            //SQL hibernate action
//            session.remove(person);
//
//            //Command for hibernate cash
//            person.getItems().forEach(el -> el.setOwner(null));

//            Person person = session.get(Person.class, 4);
//            Item item = session.get(Item.class, 1);
//            //Hibernate cash
//            item.getOwner().getItems().remove(item);
//            //SQL
//            item.setOwner(person);
//            //Hibernate cash
//            person.getItems().add(item);

//            Person person = new Person("Test Cascading", 30);
//            Item item = new Item("Test Cascading Item", person);
//            person.setItems(new ArrayList<>(Collections.singletonList(item)));
//            session.persist(person);

            //refactored with method Person addItem();
//            Person person = new Person("Test cascading refacotred", 30);
//            person.addItem(new Item("Item1"));
//            person.addItem(new Item("Item2"));
//            person.addItem(new Item("Item3"));
//            session.save(person);

            Person person = session.get(Person.class, 8);
            session.remove(person);

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
