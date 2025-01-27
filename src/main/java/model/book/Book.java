package model.book;

import model.author.Author;
import model.reader.Reader;

public class Book {
    private int id;
    private String title;
    private Author author;
    private Reader reader;
    private boolean available;

    public Book() {
    }

    public Book(int id, String title, Author author, Reader reader, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.reader = reader;
        this.available = available;
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
