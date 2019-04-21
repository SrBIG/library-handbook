package model.reader;

import model.db.LibraryDao;
import model.exception.ReaderNotFoundException;

import java.util.Objects;

public class ReaderServiceImpl implements ReaderService {
    public static final Byte AGE_MINIMUM = 12;
    private LibraryDao readerDao;

    public ReaderServiceImpl() {
        readerDao = new ReaderLibraryDao();
    }

    @Override
    public Reader getByName(String name) throws ReaderNotFoundException {
        Reader reader = (Reader) readerDao.getByName(name);

        if (Objects.isNull(reader)) {
            throw new ReaderNotFoundException();
        }

        return reader;
    }

    @Override
    public Reader getById(int id) throws ReaderNotFoundException {
        return getReaderFromDb(id);
    }

    @Override
    public void update(int id, String name, String address, byte age) throws ReaderNotFoundException, IllegalArgumentException {
        checkAge(age);
        Reader reader = getReaderFromDb(id);

        reader.setName(name);
        reader.setAddress(address);
        reader.setAge(age);

        readerDao.update(reader);
    }

    private Reader getReaderFromDb(int id) throws ReaderNotFoundException {
        Reader reader = (Reader) readerDao.getById(id);
        if (Objects.isNull(reader)) {
            throw new ReaderNotFoundException();
        } else return reader;
    }

    void checkAge(byte age) throws IllegalArgumentException{
        if (age < AGE_MINIMUM){
            throw new IllegalArgumentException();
        }
    }
}
