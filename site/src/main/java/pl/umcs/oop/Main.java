package pl.umcs.oop;


import pl.umcs.oop.database.DatabaseConnection;

import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection.connect("test.db");
        // Statement - obiekt używany do wykonywania "statycznego" polecenia SQL
        Statement stmt = DatabaseConnection.getConnection().createStatement();
        stmt.execute("""
           CREATE TABLE IF NOT EXISTS account (
           id INTEGER PRIMARY KEY AUTOINCREMENT,
           username TEXT UNIQUE,
           password TEXT
           )""");



    }
}