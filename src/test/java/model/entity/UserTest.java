package model.entity;

import model.user.User;
import model.user.UserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {
    private User user = new User();

    @Test
    public void testSetAll(){
        user.setRole(UserRole.USER);
        user.setId(1111);
        user.setFirstName("TRY");
        user.setLogin("TRY");
        user.setLastName("TRY");
    }

    @Test
    public void testGetAll(){
        user.getFirstName();
        user.getLogin();
        user.getLastName();
        user.getId();
        user.getRole();
    }
}
