package model.reader;

import model.exception.ReaderNotFoundException;

import java.util.List;

public interface ReaderService {
    Reader getByName(String name) throws ReaderNotFoundException;

    Reader getById(int id) throws ReaderNotFoundException;

    void update(int id, String name, String address, byte age) throws ReaderNotFoundException, IllegalArgumentException;

    void delete(int id);

    void save(String name, String address, byte age) throws IllegalArgumentException;

    List<String> getReadersNamesLike(String term);
}
