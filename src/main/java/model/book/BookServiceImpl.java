package model.book;

import model.author.Author;
import model.author.AuthorLibraryDao;
import model.db.LibraryDao;
import model.exception.BookNotFoundException;
import model.reader.Reader;
import model.reader.ReaderLibraryDao;

import java.util.List;
import java.util.Objects;

public class BookServiceImpl implements BookService {
    private LibraryDao bookDao;
    private LibraryDao authorDao;
    private LibraryDao readerDao;

    public BookServiceImpl() {
        bookDao = new BookLibraryDao();
        authorDao = new AuthorLibraryDao();
        readerDao = new ReaderLibraryDao();
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = bookDao.getAll();
        return books;
    }

    @Override
    public Book getBookById(int id) throws BookNotFoundException {
        return getBookFromDb(id);
    }

    @Override
    public void update(int id, String title, Author author, Reader reader, boolean available) throws BookNotFoundException {
        Book book = getBookFromDb(id);

        if (Objects.nonNull(reader)) {
            available = false;
        }

        book.setTitle(title);
        book.setAuthor(author);
        book.setReader(reader);
        book.setAvailable(available);

        bookDao.update(book);
    }

    @Override
    public void delete(int id) {
        bookDao.delete(id);
    }

    private Book getBookFromDb(int id) throws BookNotFoundException {
        Book book = (Book) bookDao.getById(id);
        if (Objects.isNull(book)) {
            throw new BookNotFoundException();
        } else return book;
    }
}
