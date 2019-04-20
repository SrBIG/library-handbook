package model.db.mysql;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseController {
    private final String DB = "handbook";
    private final String FIX = "?useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String URL = "jdbc:mysql://localhost:3306/" + DB + FIX;
    private final String USER = "root";
    private final String PASS = "root";
    private final String DRIVER = "com.mysql.jdbc.Driver";

    private static DatabaseController instance;

    public static DatabaseController getInstance() {
        if (instance == null) {
            synchronized (DatabaseController.class) {
                if (instance == null) {
                    instance = new DatabaseController();
                }
            }
        }
        return instance;
    }

    private DatabaseController() {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

