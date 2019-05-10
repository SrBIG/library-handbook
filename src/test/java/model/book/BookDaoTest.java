package model.book;

import model.author.Author;
import model.author.AuthorLibraryDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookDaoTest {
//    @Mock
//    private Book author;
//    private int authorId = 9999;
//    private String authorName = "Name";
//    private String authorCountry = "country";
//
    private BookLibraryDao bookDao = new BookLibraryDao();

    @Before
    public void setup() {
//        when(author.getId()).thenReturn(authorId);
//        when(author.getName()).thenReturn(authorName);
//        when(author.getCountry()).thenReturn(authorCountry);
    }

    @Test
    public void testGetAllUsers() {
        bookDao.getAll();
    }

    @Test
    public void testGetById() {
        bookDao.getById(0);
    }

    @Test
    public void testGetByName() {
        bookDao.getByName("Book");
    }

//    @Test
//    public void testAllCycle() {
//        authorDao.getNamesLike("L");
//        authorDao.save(author);
//        authorDao.getByName(authorName);
//        authorDao.getById(authorId);
//        authorDao.delete(authorId);
//    }
}
