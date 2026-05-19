package pl.umcs.oop.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    public static void connect (String file_path){
        try {
            // DriverManager -- manager do łączenia się z bazami danych
            connection = DriverManager.getConnection("jdbc:sqlite:" + file_path);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void disconnect () {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
