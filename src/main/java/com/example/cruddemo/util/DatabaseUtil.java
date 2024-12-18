package com.example.cruddemo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/erp";
    private static final String USER = "root";
    private static final String PASSWORD = "vuvu";
    private static Connection connection;

    private DatabaseUtil() {}

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                synchronized (DatabaseUtil.class) {
                    if (connection == null || connection.isClosed()) {
                        connection = DriverManager.getConnection(URL, USER, PASSWORD);
                        System.out.println("Database connection established successfully.");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error establishing database connection: " + e.getMessage());
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing database connection: " + e.getMessage());
        }
    }
}
