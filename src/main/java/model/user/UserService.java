package model.user;

import java.util.List;
import java.util.Objects;

public class UserService {
    private UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    public boolean checkOwner() {
        return userDao.checkOwner();
    }

    public void save(User user) {
        if (!checkOwner()) {
            user.setRole(UserRole.OWNER);
        } else if (Objects.isNull(user.getRole())) {
            user.setRole(UserRole.USER);
        }
        userDao.save(user);
    }

    public void checkUser(User user) {
        UserRole userRole = userDao.getById(user.getId());
        if (Objects.isNull(userRole)) {
            user.setRole(UserRole.USER);
            save(user);
        } else {
            user.setRole(userRole);
        }
    }

    public void addAdmin(long id) {
        userDao.updateAdmin(id);
    }

    public void deleteAdmin(long id) {
        userDao.deleteAdmin(id);
    }

    public List<User> getAllUsers() {
        List<User> users = userDao.getAll();
        users.removeIf(user -> user.getRole() == UserRole.OWNER);
        return users;
    }
}
