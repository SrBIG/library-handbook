package model.author;

import model.book.Book;
import model.book.BookLibraryDao;
import model.db.LibraryDao;
import model.exception.AuthorNotFoundException;

import java.util.List;
import java.util.Objects;

public class AuthorServiceImpl implements AuthorService {
    private LibraryDao authorDao;
    private LibraryDao bookDao;

    public AuthorServiceImpl() {
        authorDao = new AuthorLibraryDao();
        bookDao = new BookLibraryDao();
    }

    @Override
    public List<Author> getAllAuthors() {
        List<Author> authors = authorDao.getAll();
        return authors;
    }

    @Override
    public Author getByName(String name) throws AuthorNotFoundException {
        Author author = (Author) authorDao.getByName(name);

        if (Objects.isNull(author)) {
            throw new AuthorNotFoundException();
        }

        return author;
    }

    @Override
    public Author getById(int id) throws AuthorNotFoundException {
        return getAuthorFromDb(id);
    }

    @Override
    public void update(int id, String name, String country) throws AuthorNotFoundException {
        Author author = getAuthorFromDb(id);

        author.setName(name);
        author.setCountry(country);

        authorDao.update(author);
    }

    @Override
    public void delete(int id) {
        authorDao.delete(id);
        ((BookLibraryDao)bookDao).cleanBookFromReader(id);
    }

    @Override
    public void save(String name, String country) {
        Author author = new Author();

        author.setName(name);
        author.setCountry(country);

        authorDao.save(author);
    }

    private Author getAuthorFromDb(int id) throws AuthorNotFoundException {
        Author author = (Author) authorDao.getById(id);
        if (Objects.isNull(author)) {
            throw new AuthorNotFoundException();
        } else return author;
    }
}
