package model.author;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthorDaoTest {
    @Mock
    private Author author;
    private int authorId = 9999;
    private String authorName = "Name";
    private String authorCountry = "country";

    private AuthorLibraryDao authorDao = new AuthorLibraryDao();

    @Before
    public void setup() {
        when(author.getId()).thenReturn(authorId);
        when(author.getName()).thenReturn(authorName);
        when(author.getCountry()).thenReturn(authorCountry);
    }

    @Test
    public void testGetAllUsers() {
        authorDao.getAll();
    }

    @Test
    public void testGetById() {
        authorDao.getById(0);
    }

    @Test
    public void testAllCycle() {
        authorDao.getNamesLike("L");
        authorDao.save(author);
        authorDao.getByName(authorName);
        authorDao.getById(authorId);
        authorDao.delete(authorId);
    }
}
