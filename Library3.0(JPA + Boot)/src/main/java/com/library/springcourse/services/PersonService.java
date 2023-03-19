package com.library.springcourse.services;

import com.library.springcourse.models.Book;
import com.library.springcourse.models.Person;
import com.library.springcourse.repositories.PersonRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }
    public Person findOne(int id){
        Optional<Person> foundPerson = personRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person){
        personRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatePerson){
        updatePerson.setId(id);
        personRepository.save(updatePerson);
    }

    @Transactional
    public void delete(int id){
        personRepository.deleteById(id);
    }

    public Optional<Person> getByFullName(String name){
        return personRepository.findByFullName(name);
    }
    public List<Book> getBooksByPersonId(int id) {
        Optional<Person> foundPerson = personRepository.findById(id);
        if (foundPerson.isPresent()) {
            Hibernate.initialize(foundPerson.get().getBooks());
            Person person = foundPerson.get();
            person.getBooks().forEach(book -> {
                if(Math.abs(new Date().getTime() - book.getTimeTaken().getTime()) > 864000000)
                    book.setDelay(true);
            });
            return person.getBooks();
        }else {
            return Collections.emptyList();
        }
    }
}
