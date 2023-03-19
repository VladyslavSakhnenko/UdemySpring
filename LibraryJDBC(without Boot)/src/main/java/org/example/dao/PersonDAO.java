package org.example.dao;

import org.example.models.Book;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    public final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person",new BeanPropertyRowMapper<>(Person.class));
    }
    public Optional<Person> show(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?",
                new Object[]{id},new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
    public void save(Person person){
        jdbcTemplate.update("INSERT INTO Person(fullname, year_of_birth) VALUES (?, ?)",
                person.getFullName(), person.getYearOfBirth());
    }

    public void update(int id, Person updatePerson) {
        jdbcTemplate.update("UPDATE Person SET fullname=?, year_of_birth=? WHERE id=?",
                updatePerson.getFullName(), updatePerson.getYearOfBirth(), id);
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Person Where id=?", id);
    }
    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }
    public Optional<Person> getByFullName(String fullName){
        return jdbcTemplate.query("SELECT * FROM Person WHERE fullname=?", new Object[]{fullName},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
}
