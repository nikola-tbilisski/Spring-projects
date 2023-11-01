package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM BooK WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(name, author, year) VALUES (?, ?, ?)", book.getName(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE book SET name=?, author=?, year=? WHERE id=?",
                updatedBook.getName(), updatedBook.getAuthor(), updatedBook.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }

    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.person_id = Person.id " +
                "WHERE Book.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE id=?", id);
    }

    public void assign(int id, Person selectedPerson) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", selectedPerson.getId(), id);
    }
}
