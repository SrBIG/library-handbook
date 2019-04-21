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
    public static final String UPDATE_AUTHOR = "UPDATE authors SET name=?, country=? WHERE id=?";
    public static final String LAST_AUTHOR_ID = "SELECT MAX(id) FROM authors";
    public static final String READER_BY_ID = "SELECT * FROM readers WHERE id=?";
    public static final String ALL_READERS = "SELECT * FROM readers";
    public static final String READER_BY_NAME = "SELECT * FROM readers WHERE name=?";
    public static final String ADD_READER = "INSERT INTO readers (name, address, age) VALUES(?,?,?)";
    public static final String DELETE_READER = "DELETE FROM readers WHERE id=?";
    public static final String UPDATE_READER = "UPDATE readers SET name=?, address=?, age=? WHERE id=?";
    public static final String LAST_READER_ID = "SELECT MAX(id) FROM readers";
}
