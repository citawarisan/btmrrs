/**
 *
 * @author 
 */
package com.citawarisan.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    private static final String URL = "jdbc:mysql://localhost:3306/btmrrs";
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";
    
    private static Connection conn;
    
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER_NAME);
            
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e){
            e.getMessage();
        }
        return conn;
    }
    
}
