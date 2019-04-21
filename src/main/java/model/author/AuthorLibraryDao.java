package model.author;

import model.db.mysql.DatabaseController;
import model.db.mysql.LibraryHandbookQuery;
import model.db.LibraryDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorLibraryDao implements LibraryDao<Author> {
    private DatabaseController dbController;
    private Connection connection;

    public AuthorLibraryDao() {
        this.dbController = DatabaseController.getInstance();
    }

    @Override
    public void save(Author author) {
        try {
            connection = dbController.getConnection();
            PreparedStatement statement = connection.prepareStatement(LibraryHandbookQuery.ADD_AUTHOR);
            statement.setString(1, author.getName());
            statement.setString(2, author.getCountry());
            author.setId(getLastId());
            statement.execute();
            statement.close();
            dbController.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            connection = dbController.getConnection();
            PreparedStatement statement = connection.prepareStatement(LibraryHandbookQuery.DELETE_AUTHOR);
            statement.setInt(1, id);
            statement.execute();
            statement.close();
            dbController.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Author author) {
        try {
            connection = dbController.getConnection();
            PreparedStatement statement = connection.prepareStatement(LibraryHandbookQuery.UPDATE_AUTHOR);
            statement.setString(1, author.getName());
            statement.setString(2, author.getCountry());
            statement.setInt(3, author.getId());
            statement.execute();
            statement.close();
            dbController.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Author getById(int id) {
        try {
            connection = dbController.getConnection();
            PreparedStatement statement = connection.prepareStatement(LibraryHandbookQuery.AUTHOR_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Author author = null;
            if (resultSet.next()) {
                author = createAuthor(resultSet);
            }
            resultSet.close();
            statement.close();
            dbController.closeConnection(connection);
            return author;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Author> getAll() {
        try {
            connection = dbController.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(LibraryHandbookQuery.ALL_AUTHORS);
            List<Author> authors = new ArrayList<Author>();
            while (resultSet.next()) {
                Author author = createAuthor(resultSet);
                authors.add(author);
            }
            resultSet.close();
            statement.close();
            dbController.closeConnection(connection);
            return authors;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Author getByName(String name) {
        try {
            connection = dbController.getConnection();
            PreparedStatement statement = connection.prepareStatement(LibraryHandbookQuery.AUTHOR_BY_NAME);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            Author author = null;
            if (resultSet.next()) {
                author = createAuthor(resultSet);
            }
            resultSet.close();
            statement.close();
            dbController.closeConnection(connection);
            return author;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Integer getLastId() {
        try {
            connection = dbController.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(LibraryHandbookQuery.LAST_AUTHOR_ID);
            Integer lastId = resultSet.getInt(1);
            resultSet.close();
            statement.close();
            dbController.closeConnection(connection);
            return lastId;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private Author createAuthor(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        String country = resultSet.getString(3);
        return new Author(id, name, country);
    }
}
