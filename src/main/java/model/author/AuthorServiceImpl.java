package model.author;

import model.db.LibraryDao;

import java.util.List;

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
}
