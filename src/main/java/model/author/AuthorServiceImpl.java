package model.author;

import model.db.LibraryDao;
import model.exception.AuthorNotFoundException;

import java.util.List;
import java.util.Objects;

public class AuthorServiceImpl implements AuthorService {
    private LibraryDao authorDao;

    public AuthorServiceImpl(){
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

        if (Objects.isNull(author)){
            throw new AuthorNotFoundException();
        }

        return author;
    }
}
