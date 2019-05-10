package model.entity;

import model.author.Author;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AuthorTest {
    private Author author = new Author();

    @Test
    public void testSetAll(){
        author.setId(1111);
        author.setCountry("TRY");
        author.setName("TRY");
    }

    @Test
    public void testGetAll(){
        author.getId();
        author.getCountry();
        author.getName();
    }
}
