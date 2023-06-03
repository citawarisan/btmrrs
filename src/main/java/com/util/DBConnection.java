package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Omar Alomory(S63955)
 */
public class DBConnection {
    private static Connection myConnection = null;
    private static final String myURL = "jdbc:mysql://localhost:3306/core";

    public static Connection getConnection() throws ClassNotFoundException {
        if (myConnection != null) {
            return myConnection;
        } else try {
            Class.forName("com.mysql.jdbc.Driver");
            myConnection = DriverManager.getConnection(myURL, "root", "admin");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return myConnection;
    }

    // closing the DB connection
    public static void closeConnection() {
        try {
            myConnection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}