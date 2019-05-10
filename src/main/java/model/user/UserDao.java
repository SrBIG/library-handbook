package model.user;

import model.db.mysql.DatabaseController;
import model.db.mysql.LibraryHandbookQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private DatabaseController dbController;
    private Connection connection;

    public UserDao() {
        this.dbController = DatabaseController.getInstance();
    }

    public boolean checkOwner() {
        try {
            connection = dbController.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(LibraryHandbookQuery.CHECK_OWNER);
            boolean hasOwner = resultSet.next();
            statement.close();
            dbController.closeConnection(connection);
            return hasOwner;
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    public void save(User user) {
        try {
            connection = dbController.getConnection();
            PreparedStatement statement = connection.prepareStatement(LibraryHandbookQuery.ADD_USER);
            statement.setLong(1, user.getId());
            statement.setInt(2, user.getRole().getRole());
            String userName = "";
            if (user.getFirstName() != null && !user.getFirstName().isEmpty()) {
                userName += user.getFirstName();
                if (user.getLastName() != null && !user.getLastName().isEmpty()) {
                    userName += " " + user.getLastName();
                }
            } else if (user.getLogin() != null &&!user.getLogin().isEmpty()) {
                userName += user.getLogin();
            }
            statement.setString(3, userName);
            statement.execute();
            statement.close();
            dbController.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        try {
            connection = dbController.getConnection();
            PreparedStatement statement = connection.prepareStatement(LibraryHandbookQuery.DELETE_USER);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            dbController.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserRole getById(long id) {
        try {
            connection = dbController.getConnection();
            PreparedStatement statement = connection.prepareStatement(LibraryHandbookQuery.USER_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            UserRole userRole = null;
            int role = 0;
            if (resultSet.next()) {
                role = resultSet.getInt(1);
                switch (role) {
                    case 0:
                        userRole = UserRole.USER;
                        break;
                    case 1:
                        userRole = UserRole.ADMIN;
                        break;
                    case 2:
                        userRole = UserRole.OWNER;
                        break;
                    default:
                        userRole = UserRole.USER;
                }
            }
            resultSet.close();
            statement.close();
            dbController.closeConnection(connection);
            return userRole;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> getAll() {
        try {
            connection = dbController.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(LibraryHandbookQuery.ALL_USERS);
            List<User> users = new ArrayList<User>();

            while (resultSet.next()) {
                User user = createUser(resultSet);
                users.add(user);
            }
            resultSet.close();
            statement.close();
            dbController.closeConnection(connection);
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void updateAdmin(long id) {
        try {
            connection = dbController.getConnection();
            PreparedStatement statement = connection.prepareStatement(LibraryHandbookQuery.ADMIN_UP);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            dbController.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAdmin(long id) {
        try {
            connection = dbController.getConnection();
            PreparedStatement statement = connection.prepareStatement(LibraryHandbookQuery.ADMIN_DELETE);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            dbController.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(1);
        int role = resultSet.getInt(2);
        String name = resultSet.getString(3);
        User user = new User();
        user.setId(id);
        user.setFirstName(name);
        UserRole userRole;
        switch (role) {
            case 0:
                userRole = UserRole.USER;
                break;
            case 1:
                userRole = UserRole.ADMIN;
                break;
            case 2:
                userRole = UserRole.OWNER;
                break;
            default:
                userRole = UserRole.USER;
        }
        user.setRole(userRole);
        return user;
    }
}
