package model.entity;

import model.reader.Reader;
import model.user.UserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReaderTest {
    private Reader reader = new Reader();

    @Test
    public void testSetAll(){
        reader.setId(1111);
        reader.setAddress("TRY");
        reader.setName("TRY");
    }

    @Test
    public void testGetAll(){
        reader.getId();
        reader.getAddress();
        reader.getAge();
        reader.getName();
    }
}
