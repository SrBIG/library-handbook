package model.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.jws.soap.SOAPBinding;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest {
    @Mock
    private User user;
    private long userId = 999;


    private UserDao userDao = new UserDao();

    @Before
    public void setup(){
        when(user.getId()).thenReturn(userId);
        when(user.getRole()).thenReturn(UserRole.USER);
    }

    @Test
    public void testGetAllUsers(){
        userDao.getAll();
    }

    @Test
    public void testGetById(){
        userDao.getById(0);
    }

    @Test
    public void testAllCycle(){
        userDao.checkOwner();
        userDao.save(user);
        userDao.getById(userId);
        userDao.updateAdmin(userId);
        userDao.deleteAdmin(userId);
        userDao.delete(userId);
    }
}
