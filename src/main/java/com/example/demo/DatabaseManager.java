package com.example.demo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    // Hard-coded database connection details
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/stem_project";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "M@tthew0199";

    public DatabaseManager() throws SQLException {
        // Verify the database URL is not null
        if (DB_URL == null || DB_URL.isEmpty()) {
            throw new SQLException("The database URL cannot be null or empty");
        }
    }

    public Connection getConnection() throws SQLException {
        // Establish and return a connection to the database
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public void createSchema() throws SQLException {
        // Example schema creation logic
        try (Connection conn = getConnection()) {
            // Your schema creation logic goes here
            System.out.println("Schema creation logic goes here");
        }
    }

    public static void main(String[] args) {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            dbManager.createSchema();
            System.out.println("Schema created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
