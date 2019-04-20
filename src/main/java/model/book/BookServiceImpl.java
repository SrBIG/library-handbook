package model.book;

import model.db.LibraryDao;

import java.util.List;

public class BookServiceImpl implements BookService {
    private LibraryDao bookDao;

    public BookServiceImpl() {
        bookDao = new BookLibraryDao();
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = bookDao.getAll();
        return books;
    }
}
