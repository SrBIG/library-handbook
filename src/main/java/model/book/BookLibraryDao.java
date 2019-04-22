package model.book;

import model.author.Author;
import model.db.LibraryDao;
import model.db.mysql.DatabaseController;
import model.db.mysql.LibraryHandbookQuery;
import model.reader.Reader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookLibraryDao implements LibraryDao<Book> {
    private DatabaseController dbController;
    private Connection connection;

    public BookLibraryDao() {
        this.dbController = DatabaseController.getInstance();
    }

    @Override
    public void save(Book book) {
        try {
            connection = dbController.getConnection();
            PreparedStatement statement = connection.prepareStatement(LibraryHandbookQuery.ADD_BOOK);
            int authorId = book.getAuthor().getId();
            int readerId = Objects.nonNull(book.getReader()) ? book.getReader().getId() : 0;
            statement.setString(1, book.getTitle());
            statement.setInt(2, authorId);
            statement.setInt(3, readerId);
            statement.setBoolean(4, book.isAvailable());
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
            PreparedStatement statement = connection.prepareStatement(LibraryHandbookQuery.DELETE_BOOK);
            statement.setInt(1, id);
            statement.execute();
            statement.close();
            dbController.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Book book) {
        try {
            connection = dbController.getConnection();
            PreparedStatement statement = connection.prepareStatement(LibraryHandbookQuery.UPDATE_BOOK);
            int authorId = book.getAuthor().getId();
            int readerId = Objects.nonNull(book.getReader()) ? book.getReader().getId() : 0;
            statement.setString(1, book.getTitle());
            statement.setInt(2, authorId);
            statement.setInt(3, readerId);
            statement.setBoolean(4, book.isAvailable());
            statement.setInt(5, book.getId());
            statement.execute();
            statement.close();
            dbController.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book getById(int id) {
        try {
            connection = dbController.getConnection();
            PreparedStatement statement = connection.prepareStatement(LibraryHandbookQuery.BOOK_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Book book = null;
            if (resultSet.next()) {
                book = createBook(resultSet);
            }
            resultSet.close();
            statement.close();
            dbController.closeConnection(connection);
            return book;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Book> getAll() {
        try {
            connection = dbController.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(LibraryHandbookQuery.ALL_BOOKS);
            List<Book> books = new ArrayList<Book>();

            while (resultSet.next()) {
                Book book = createBook(resultSet);
                books.add(book);
            }
            resultSet.close();
            statement.close();
            dbController.closeConnection(connection);
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Book getByName(String name) {
        try {
            connection = dbController.getConnection();
            PreparedStatement statement = connection.prepareStatement(LibraryHandbookQuery.BOOK_BY_TITLE);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            Book book = null;
            if (resultSet.next()) {
                book = createBook(resultSet);
            }
            resultSet.close();
            statement.close();
            dbController.closeConnection(connection);
            return book;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void cleanBookFromReader(int readerId) {
        try {
            connection = dbController.getConnection();
            PreparedStatement statement = connection.prepareStatement(LibraryHandbookQuery.CLEAN_BOOK_FROM_READER);
            statement.setInt(1, readerId);
            statement.executeQuery();
            statement.close();
            dbController.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cleanBookFromAuthor(int readerId) {
        try {
            connection = dbController.getConnection();
            PreparedStatement statement = connection.prepareStatement(LibraryHandbookQuery.CLEAN_BOOK_FROM_AUTHOR);
            statement.setInt(1, readerId);
            statement.executeQuery();
            statement.close();
            dbController.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Book createBook(ResultSet resultSet) throws SQLException {
        int bookId = resultSet.getInt(1);
        String title = resultSet.getString(2);

        int authorId = resultSet.getInt(3);
        Author author = null;
        if (authorId != 0) {
            String authorName = resultSet.getString(4);
            String authorCountry = resultSet.getString(5);
            author = new Author(authorId, authorName, authorCountry);
        }

        int readerId = resultSet.getInt(6);
        Reader reader = null;
        if (readerId != 0) {
            String readerName = resultSet.getString(7);
            String readerAddress = resultSet.getString(8);
            byte readerAge = resultSet.getByte(9);
            reader = new Reader(readerId, readerName, readerAddress, readerAge);
        }

        boolean available = resultSet.getBoolean(10);

        return new Book(bookId, title, author, reader, available);
    }
}
