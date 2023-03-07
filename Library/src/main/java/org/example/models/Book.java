package org.example.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;
    @NotEmpty(message = "Enter name of book!")
    @Size(min = 2, max = 100, message = "Title of the book must be between 2 to 100 symbols!")
    private String title;
    @NotEmpty(message = "Enter the author!")
    @Size(min = 2, max = 100, message = "Author name must be between 2 to 100 symbols!")
    private String author;
    @Min(value = 1500, message = "Year must be more then 1500")
    private int year;

    public Book(){

    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
