package model.reader;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReaderDaoTest {
    private Reader reader = new Reader();

    private int readerId = 9999;
    private String readerName = "Name";
    private String readerAddress = "address";

    private ReaderLibraryDao readerDao = new ReaderLibraryDao();

    @Before
    public void setup() {
        reader.setName(readerName);
        reader.setAddress(readerAddress);
    }

    @Test
    public void testGetAllUsers() {
        readerDao.getAll();
    }

    @Test
    public void testGetById() {
        readerDao.getById(0);
    }

    @Test
    public void testAllCycle() {
        readerDao.getNamesLike("L");
        readerDao.save(reader);
        readerDao.getByName(readerName);
        readerDao.getById(readerId);
        readerDao.delete(reader.getId());
    }
}
