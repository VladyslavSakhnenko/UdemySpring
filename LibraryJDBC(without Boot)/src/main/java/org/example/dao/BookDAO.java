package org.example.dao;

import org.example.models.Book;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM Book",new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional<Book> show(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?",new Object[]{id},new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny();
    }
    public void save(Book book){
        jdbcTemplate.update("INSERT INTO Book(title, author, year) VALUES (?,?,?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book updateBook){
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year=? WHERE id=?",
                updateBook.getTitle(), updateBook.getAuthor(), updateBook.getYear(), id);
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Book Where id=?", id);
    }
    public Optional<Person> getBookOwner(int id){
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.person_id = Person.id " +
                "WHERE Book.id = ?", new Object[]{id},new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void release(int id){
        jdbcTemplate.update("UPDATE Book SET person_id=NULL where id=?", id);
    }
    public void assign(int id, Person selectedPerson){
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id=?", selectedPerson.getId(),id);
    }
}
