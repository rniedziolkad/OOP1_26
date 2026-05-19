package pl.umcs.oop;


import pl.umcs.oop.database.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection.connect("test.db");

    }
}