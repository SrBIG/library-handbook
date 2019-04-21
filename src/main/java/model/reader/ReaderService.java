package model.reader;

import model.exception.ReaderNotFoundException;

public interface ReaderService {
    Reader getByName(String name) throws ReaderNotFoundException;

    Reader getById(int id) throws ReaderNotFoundException;

    void update(int id, String name, String address, byte age) throws ReaderNotFoundException, IllegalArgumentException;
}
