package com.citawarisan.dao;

import com.citawarisan.model.*;
import com.citawarisan.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao {
    public int createUser(User user) {
        String query = "INSERT INTO User(user, password, type, name, phone_number, email) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getType());
            ps.setString(4, user.getName());
            ps.setString(5, user.getPhone());
            ps.setString(6, user.getEmail());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public User matchUser(String username, String password) {
        User user = null;
        String query = "SELECT * FROM User WHERE user=? AND password=? AND type<>0";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user = new User();
                user.setUsername(rs.getString("user"));
                user.setPassword(rs.getString("password"));
                user.setType(rs.getInt("type"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone_number"));
                System.out.println("User name is = " + rs.getString("user"));
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM User";

        try (Connection conn = DBConnection.getConnection(); ResultSet rs = conn.createStatement().executeQuery(query)) {
            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("user"));
                user.setPassword(rs.getString("password"));
                user.setType(rs.getInt("type"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone_number"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public User getUser(String username) {
        User user = null;
        String query = "SELECT * FROM User WHERE user=?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUsername(rs.getString(1));
                user.setPassword(rs.getString(2));
                user.setType(rs.getInt(3));
                user.setName(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setPhone(rs.getString(6));
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public boolean updateUser(User user) {
        String query = "UPDATE User SET email=?, password=?, phone_number=?, name=?, type=? WHERE user=?";
        boolean success = false;

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, user.getEmail());
            String password = user.getPassword();
            if (password == "")
                password = getUser(user.getUsername()).getPassword();
            ps.setString(2, password);
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getName());
            ps.setString(6, user.getUsername());
            ps.setInt(5, user.getType());
            ps.executeUpdate();

            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    public void deleteUser(String username) {
//        String query = "DELETE FROM User WHERE user=?";
        String query = "UPDATE User SET type = 0 WHERE user = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
