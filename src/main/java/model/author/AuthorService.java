package model.author;

import model.exception.AuthorNotFoundException;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();

    Author getByName(String name) throws AuthorNotFoundException;

    Author getById(int id) throws AuthorNotFoundException;

    void update(int id, String name, String country) throws AuthorNotFoundException;

    void delete(int id);
}
