package com.citawarisan.dao;

import com.citawarisan.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends ModelDao<User> {

    protected List<User> parseModels(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();

        while (rs.next()) {
            User user = new User();
            user.setUsername(rs.getString(1));
            user.setPassword(rs.getString(2));
            user.setName(rs.getString(3));
            user.setPhone(rs.getString(4));
            user.setEmail(rs.getString(5));
        }

        return users;
    }

    protected PreparedStatement parseQuery(String query, User user) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setInt(3, user.getType());
        ps.setString(4, user.getName());
        ps.setString(5, user.getPhone());
        ps.setString(6, user.getEmail());

        return ps;
    }

    public boolean create(User user) {
        try {
            String query = "INSERT INTO User VALUE (?, ?, ?, ?, ?, ?)";
            parseQuery(query, user).execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<User> read() {
        try {
            String query = "SELECT * FROM User";
            ResultSet rs = connection.createStatement().executeQuery(query);

            return parseModels(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<User> read(User user) {
        try {
            String query = "SELECT * FROM User WHERE user = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getUsername());

            ResultSet rs = ps.executeQuery();

            return parseModels(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean update(User user) {
        try {
            String query = "UPDATE Reservation SET user = ?, password = ?, type = ?, name = ?, email = ?, phone = ?";
            PreparedStatement ps = parseQuery(query, user);
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM User WHERE user = ?");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
