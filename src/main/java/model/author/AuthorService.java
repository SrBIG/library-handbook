package model.author;

import model.exception.AuthorNotFoundException;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();

    Author getByName(String name) throws AuthorNotFoundException;
}
