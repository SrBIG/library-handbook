package model.book;

import model.author.Author;
import model.exception.BookNotFoundException;
import model.reader.Reader;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book getBookById(int id) throws BookNotFoundException;

    void update(int id, String title, Author author, Reader reader, boolean available) throws BookNotFoundException;

    void delete(int id);
}
