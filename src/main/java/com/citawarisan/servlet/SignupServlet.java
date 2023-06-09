package com.citawarisan.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/BTMRRS";
            String dbUsername = "your_username"; //need some hands here
            String dbPassword = "your_password";
            conn = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query = "INSERT INTO users (username, password, name, phone, email) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, name);
            stmt.setString(4, phone);
            stmt.setString(5, email);
            stmt.executeUpdate();

            out.println("Registration successful");
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                    out.println("Error: " + e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    out.println("Error: " + e.getMessage());
                }
            }
        }
    }
}
