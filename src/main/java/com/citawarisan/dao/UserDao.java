package com.citawarisan.dao;

import com.citawarisan.model.User;
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

    private final Connection con;

    public UserDao() throws SQLException, ClassNotFoundException {
        con = DBConnection.getConnection();
    }

    public void save(User user) throws SQLException {
        try {
            String mySQLQuery = "insert into user(username, password, type, name, phone, email) values(?, ?, ?, ?, ?, ?)";

            PreparedStatement myPS = con.prepareStatement(mySQLQuery);

            myPS.setString(1, user.getUsername());
            myPS.setString(2, user.getPassword());
            myPS.setInt(3, user.getType());
            myPS.setString(4, user.getName());
            myPS.setString(5, user.getPhone());
            myPS.setString(6, user.getEmail());

            myPS.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            con.close();
        }
    }

    public User authentication(String username, String password) throws SQLException {
        ResultSet rs = null;
        User user = null;

        try {
            String mySQLQuery = "select * from user where username=? and password=?";
            PreparedStatement ps = con.prepareStatement(mySQLQuery);

            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();

            while (rs.next()) {
                user = new User();

                user.setName(rs.getString("name"));
                user.setPhone(rs.getString("phone_number"));
                user.setType(rs.getInt("type"));
                user.setEmail(rs.getString("email"));
                user.setPassword(password);
                user.setUsername(username);

            }
            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            if (rs != null) {
                rs.close();
            }
            con.close();
        }

        return user;
    }

    public List<User> retrieveAll() throws SQLException {
        List<User> userList = new ArrayList<>();

        User user;
        ResultSet rs = null;

        try {
            String sqlQuery = "select * from user";
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            rs = stat.executeQuery(sqlQuery);

            while (rs.next()) {
                user = new User();

                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setType(rs.getInt("type"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone_number"));

                userList.add(user);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            if (rs != null) {
                rs.close();
            }

            con.close();
        }

        return userList;
    }

    public User retrieveUserByUserId(int userid) throws ClassNotFoundException, SQLException {

        User user = new User();

        try {
            PreparedStatement myPS = DBConnection.getConnection()
                    .prepareStatement("select * from user where userid=?");

            myPS.setInt(1, userid);
            ResultSet rs = myPS.executeQuery();

            while (rs.next()) {
                user.setUsername(rs.getString(1));
                user.setPassword(rs.getString(2));
                user.setType(rs.getInt(3));
                user.setName(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setPhone(rs.getString(6));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.close();
        }
        return user;
    }

    public void update(User user) {

        try {
            String myQ = "update user set email=?, password=?, phone_number=?, name=? WHERE username=?";

            PreparedStatement myPS = con.prepareStatement(myQ);

            myPS.setString(1, user.getEmail());
            myPS.setString(2, user.getPassword());
            myPS.setString(3, user.getPhone());
            myPS.setString(4, user.getName());
            myPS.setString(5, user.getUsername());

            myPS.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void delete(User user) {

        String myQ = "DELETE FROM user WHERE username=?";
        try {
            PreparedStatement myPs = con.prepareStatement(myQ);
            myPs.setString(1, user.getUsername());

            myPs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
