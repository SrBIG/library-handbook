package model.reader;

import model.exception.ReaderNotFoundException;

public interface ReaderService {
    Reader getByName(String name) throws ReaderNotFoundException;
}
