/**
 * @author
 */
package com.citawarisan.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/btmrrs", DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static String user = "root", password = "admin";

    private static Connection conn;

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER_NAME);
            conn = DriverManager.getConnection(URL, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void setConnection(String user, String password) {
        DBConnection.user = user;
        DBConnection.password = password;
    }
}
