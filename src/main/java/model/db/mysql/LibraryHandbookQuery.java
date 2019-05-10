package model.db.mysql;

public class LibraryHandbookQuery {
    public static final String BOOK_BY_ID =
            "SELECT b.id, b.title, a.id, a.name, a.country, r.id, r.name, r.address, r.age, b.available\n" +
                    "FROM books b\n" +
                    "LEFT JOIN authors a ON b.author_id = a.id \n" +
                    "LEFT JOIN readers r ON b.reader_id = r.id\n" +
                    "WHERE b.id = ?";
    public static final String ALL_BOOKS =
            "SELECT b.id, b.title, a.id, a.name, a.country, r.id, r.name, r.address, r.age, b.available\n" +
                    "FROM books b\n" +
                    "LEFT JOIN authors a ON b.author_id = a.id\n" +
                    "LEFT JOIN readers r ON b.reader_id = r.id\n";
    public static final String BOOK_BY_TITLE =
            "SELECT b.id, b.title, a.id, a.name, a.country, r.id, r.name, r.address, r.age, b.available\n" +
                    "FROM books b\n" +
                    "LEFT JOIN authors a ON b.author_id = a.id\n" +
                    "LEFT JOIN readers r ON b.reader_id = r.id\n" +
                    "WHERE b.title = ?";
    public static final String ADD_BOOK = "INSERT INTO books (title, author_id, reader_id, available) VALUES(?,?,?,?)";
    public static final String DELETE_BOOK = "DELETE FROM books WHERE id=?";
    public static final String UPDATE_BOOK = "UPDATE books SET title=?, author_id=?, reader_id=?, available=? WHERE id=?";
    public static final String LAST_BOOK_ID = "SELECT MAX(id) FROM books";
    public static final String BOOK_BY_READER_ID = "SELECT id FROM books WHERE reader_id=?";
    public static final String BOOK_BY_AUTHOR_ID = "SELECT id FROM books WHERE author_id=?";
    public static final String AUTHOR_BY_ID = "SELECT * FROM authors WHERE id=?";
    public static final String ALL_AUTHORS = "SELECT * FROM authors";
    public static final String AUTHOR_BY_NAME = "SELECT * FROM authors WHERE name=?";
    public static final String ADD_AUTHOR = "INSERT INTO authors (name, country) VALUES(?,?)";
    public static final String DELETE_AUTHOR = "DELETE FROM authors WHERE id=?";
    public static final String CLEAN_BOOK_FROM_AUTHOR = "UPDATE books SET books.author_id=0 WHERE books.author_id=?";
    public static final String UPDATE_AUTHOR = "UPDATE authors SET name=?, country=? WHERE id=?";
    public static final String LAST_AUTHOR_ID = "SELECT MAX(id) FROM authors";
    public static final String SELECT_AUTHOR_NAME_LIKE = "SELECT authors.name FROM authors WHERE authors.name LIKE ?";
    public static final String READER_BY_ID = "SELECT * FROM readers WHERE id=?";
    public static final String ALL_READERS = "SELECT * FROM readers";
    public static final String READER_BY_NAME = "SELECT * FROM readers WHERE name=?";
    public static final String ADD_READER = "INSERT INTO readers (name, address, age) VALUES(?,?,?)";
    public static final String CLEAN_BOOK_FROM_READER = "UPDATE books SET books.reader_id=0, books.available=1 WHERE books.reader_id=?";
    public static final String DELETE_READER = "DELETE FROM readers WHERE id=?";
    public static final String UPDATE_READER = "UPDATE readers SET name=?, address=?, age=? WHERE id=?";
    public static final String LAST_READER_ID = "SELECT MAX(id) FROM readers";
    public static final String SELECT_READER_NAME_LIKE = "SELECT readers.name FROM readers WHERE readers.name LIKE ?";
    public static final String CHECK_OWNER = "SELECT * FROM users WHERE users.role=2";
    public static final String ADD_USER = "INSERT INTO users (id, role, name) VALUES(?,?,?)";
    public static final String USER_BY_ID = "SELECT users.role FROM users WHERE users.id=?";
    public static final String ADMIN_UP = "UPDATE users SET role=1 WHERE id=?";
    public static final String ADMIN_DELETE = "UPDATE users SET role=0 WHERE id=?";
    public static final String ALL_USERS = "SELECT * FROM users";
    public static final String DELETE_USER = "DELETE FROM users WHERE id=?";
}
