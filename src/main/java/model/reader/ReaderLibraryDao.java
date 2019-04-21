package model.reader;

import model.db.mysql.DatabaseController;
import model.db.mysql.LibraryHandbookQuery;
import model.db.LibraryDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderLibraryDao implements LibraryDao<Reader> {
    private DatabaseController dbController;
    private Connection connection;

    public ReaderLibraryDao() {
        this.dbController = DatabaseController.getInstance();
    }

    @Override
    public void save(Reader reader) {
        try {
            connection = dbController.getConnection();
            PreparedStatement statement = connection.prepareStatement(LibraryHandbookQuery.ADD_READER);
            statement.setString(1, reader.getName());
            statement.setString(2, reader.getAddress());
            statement.setInt(3, reader.getAge());
            reader.setId(getLastId());
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
            PreparedStatement statement = connection.prepareStatement(LibraryHandbookQuery.DELETE_READER);
            statement.setInt(1, id);
            statement.execute();
            statement.close();
            dbController.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Reader reader) {
        try {
            connection = dbController.getConnection();
            PreparedStatement statement = connection.prepareStatement(LibraryHandbookQuery.UPDATE_READER);
            statement.setString(1, reader.getName());
            statement.setString(2, reader.getAddress());
            statement.setInt(3, reader.getAge());
            statement.setInt(4, reader.getId());
            statement.execute();
            statement.close();
            dbController.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Reader getById(int id) {
        try {
            connection = dbController.getConnection();
            PreparedStatement statement = connection.prepareStatement(LibraryHandbookQuery.READER_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Reader reader = null;
            if (resultSet.next()) {
                reader = createReader(resultSet);
            }
            resultSet.close();
            statement.close();
            dbController.closeConnection(connection);
            return reader;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reader> getAll() {
        try {
            connection = dbController.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(LibraryHandbookQuery.ALL_READERS);
            List<Reader> readers = new ArrayList<Reader>();

            while (resultSet.next()) {
                Reader reader = createReader(resultSet);
                readers.add(reader);
            }
            resultSet.close();
            statement.close();
            dbController.closeConnection(connection);
            return readers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Reader getByName(String name) {
        try {
            connection = dbController.getConnection();
            PreparedStatement statement = connection.prepareStatement(LibraryHandbookQuery.READER_BY_NAME);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            Reader reader = null;
            if (resultSet.next()) {
                reader = createReader(resultSet);
            }
            resultSet.close();
            statement.close();
            dbController.closeConnection(connection);
            return reader;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Integer getLastId() {
        try {
            connection = dbController.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(LibraryHandbookQuery.LAST_READER_ID);
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

    private Reader createReader(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        String address = resultSet.getString(3);
        byte age = resultSet.getByte(4);
        return new Reader(id, name, address, age);
    }
}
