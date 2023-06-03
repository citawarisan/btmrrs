package com.citawarisan.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/BTMRRS";
    private static Connection connection = null;
    private static String user = "root", password = ""; // use setConnection() instead of changing this

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        } else try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "root", "admin");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void setConnection(String user, String password) {
        DBConnection.user = user;
        DBConnection.password = password;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}