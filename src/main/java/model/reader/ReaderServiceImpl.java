package model.reader;

import model.db.LibraryDao;
import model.exception.ReaderNotFoundException;

import java.util.Objects;

public class ReaderServiceImpl implements ReaderService {
    private LibraryDao readerDao;

    public ReaderServiceImpl(){
        readerDao = new ReaderLibraryDao();
    }

    @Override
    public Reader getByName(String name) throws ReaderNotFoundException {
        Reader reader = (Reader) readerDao.getByName(name);

        if (Objects.isNull(reader)){
            throw new ReaderNotFoundException();
        }

        return reader;
    }
}
