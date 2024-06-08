package com.example.demo;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Load {
    private static final String EVENTS_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS events (" +
            "id INT PRIMARY KEY," +
            "title VARCHAR(255)," +
            "description TEXT," +
            "venue VARCHAR(255)," +
            "date DATE," +
            "start_time TIME," +
            "end_time TIME" +
            ")";

    private static final String QUIZ_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS quiz (" +
            "id INT PRIMARY KEY," +
            "title VARCHAR(255)," +
            "description TEXT," +
            "theme VARCHAR(20)," +
            "content VARCHAR(255)" +
            ")";

    private Connection con;

    public Load() {
        try {
            createConnection();
            createTables();
        } catch (SQLException e) {
            Logger.getLogger(Load.class.getName()).log(Level.SEVERE, "Table creation failed", e);
        }
    }

    public Connection getConnection() {
        return con;
    }

    void createTables() throws SQLException {
        executeQuery(EVENTS_TABLE_QUERY);
        executeQuery(QUIZ_TABLE_QUERY);
    }

    private void executeQuery(String query) throws SQLException {
        try (Statement stmt = con.createStatement()) {
            stmt.execute(query);
        }
    }

    private void createConnection() {
        try {
            Class.forName("org.sqlite.JDBC"); // Ensure the SQLite driver is loaded
            con = DriverManager.getConnection("jdbc:sqlite:HackingTheFuture.db");
            Logger.getLogger(Load.class.getName()).log(Level.INFO, "Database connection established");

            // Check if the database file exists, if not, create it
            File dbFile = new File("HackingTheFuture.db");
            if (!dbFile.exists()) {
                createDatabase();
            }
        } catch (ClassNotFoundException e) {
            Logger.getLogger(Load.class.getName()).log(Level.SEVERE, "SQLite JDBC driver not found", e);
        } catch (SQLException e) {
            Logger.getLogger(Load.class.getName()).log(Level.SEVERE, "Failed to connect to database", e);
        }
    }


    private void createDatabase() {
        try (Connection tempCon = DriverManager.getConnection("jdbc:sqlite:HackingTheFuture.db")) {
            Logger.getLogger(Load.class.getName()).log(Level.INFO, "Creating database...");
        } catch (SQLException e) {
            Logger.getLogger(Load.class.getName()).log(Level.SEVERE, "Failed to create database", e);
        }
    }
}
