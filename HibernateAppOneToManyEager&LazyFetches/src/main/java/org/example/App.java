package org.example;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Hibernate;
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

        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
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

//            Person person = new Person("Tom", 40);
//            Item newItem1 = new Item("Book", person);
//            Item newItem2 = new Item("Airpods", person);
//            Item newItem3 = new Item("Iphone", person);
//            person.setItems(new ArrayList<>(List.of(newItem1, newItem2, newItem3)));
//            session.save(person);

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

//            Person person = session.get(Person.class, 8);
//            session.remove(person);

            //OneToMany default Lazy fetch example
            Person person = session.get(Person.class, 1);
            System.out.println("We got Person " + person.getName());
            //Get linked entities (Lazy)
            //System.out.println(person.getItems());
            //Hibernate.initialize(person.getItems());

//            //ManyToOne default Eager fetch example
//            Item item = session.get(Item.class, 1);
//            System.out.println("We got Item " + item.getItemName());
//            //Get linked person (Eager fetch)
//            System.out.println(item.getOwner());


            session.getTransaction().commit();

            //first solution(best):  Opening new transaction and merge detached person in first session
//            System.out.println("Session closed");
//            //Opening new session and transaction(we can open session in any place in our code)
//            session = sessionFactory.getCurrentSession();
//            session.beginTransaction();
//            System.out.println("We are in new second transaction");
//            person = (Person) session.merge(person);
//            Hibernate.initialize(person.getItems());
//            session.getTransaction().commit();
//            System.out.println("Out of second session");
//            //Gets Items for this Person, because linked items where loaded in hibernate
//            System.out.println(person.getItems());

            //second solution with HQL request
            System.out.println("first Session closed");
            //Opening new session and transaction(we can open session in any place in our code)
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            System.out.println("We are in new second transaction of the old session");
            //HQL request
            List<Item> items = session.createQuery("select i from Item i where i.owner.id=:personId", Item.class)
                            .setParameter("personId", person.getId()).getResultList();
            System.out.println(items);
            session.getTransaction().commit();
            System.out.println("Out of second session transaction");
        }
    }
}
