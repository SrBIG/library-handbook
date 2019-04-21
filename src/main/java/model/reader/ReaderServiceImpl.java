package model.reader;

import model.book.BookLibraryDao;
import model.db.LibraryDao;
import model.exception.ReaderNotFoundException;

import java.util.Objects;

public class ReaderServiceImpl implements ReaderService {
    public static final Byte AGE_MINIMUM = 12;
    private LibraryDao readerDao;
    private LibraryDao bookDao;

    public ReaderServiceImpl() {
        readerDao = new ReaderLibraryDao();
        bookDao = new BookLibraryDao();
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

    @Override
    public void delete(int id) {
        readerDao.delete(id);
        ((BookLibraryDao)bookDao).cleanBookFromReader(id);
    }

    private Reader getReaderFromDb(int id) throws ReaderNotFoundException {
        Reader reader = (Reader) readerDao.getById(id);
        if (Objects.isNull(reader)) {
            throw new ReaderNotFoundException();
        } else return reader;
    }

    private void checkAge(byte age) throws IllegalArgumentException{
        if (age < AGE_MINIMUM){
            throw new IllegalArgumentException();
        }
    }
}
