package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

/**
 * @author Neil Alishev
 */
@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(name, birthdate) VALUES (?, ?)", person.getName(), person.getBirthdate());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, birthdate=? WHERE id=?", updatedPerson.getName(),
                updatedPerson.getBirthdate(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    public Optional<Person> getPersonByName(String name) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE name=?", new Object[]{name},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public List<Book> getBooksByPersonID(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }
}
