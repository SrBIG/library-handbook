package model.author;

import model.book.Book;
import model.db.LibraryDao;
import model.exception.AuthorNotFoundException;
import model.exception.BookNotFoundException;

import java.util.List;
import java.util.Objects;

public class AuthorServiceImpl implements AuthorService {
    private LibraryDao authorDao;

    public AuthorServiceImpl() {
        authorDao = new AuthorLibraryDao();
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

    private Author getAuthorFromDb(int id) throws AuthorNotFoundException {
        Author author = (Author) authorDao.getById(id);
        if (Objects.isNull(author)) {
            throw new AuthorNotFoundException();
        } else return author;
    }

    @Override
    public void update(int id, String name, String country) throws AuthorNotFoundException {
        Author author = getAuthorFromDb(id);

        author.setName(name);
        author.setCountry(country);

        authorDao.update(author);
    }
}
