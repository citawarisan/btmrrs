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
            String mySQLQuery = "insert into user2(username, password, name, phone, email) values(?, ?, ?, ?, ?)";

            PreparedStatement myPS = con.prepareStatement(mySQLQuery);

            myPS.setString(1, user.getUsername());
            myPS.setString(2, user.getPassword());
            myPS.setString(3, user.getName());
            myPS.setString(4, user.getPhone());
            myPS.setString(5, user.getEmail());

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
            String mySQLQuery = "select * from user2 where username=? and password=?";
            PreparedStatement ps = con.prepareStatement(mySQLQuery);

            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();

            while (rs.next()) { //dont get it yet
                user = new User();

                user.setName(rs.getString("name"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                user.setPassword(password);
                user.setUsername(rs.getString(1));

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
            String sqlQuery = "select * from user2";
            PreparedStatement stat = con.prepareStatement(sqlQuery);
            rs = stat.executeQuery(sqlQuery);

            while (rs.next()) {
                user = new User();

                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));

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
                    .prepareStatement("select * from user2 where userid=?");

            myPS.setInt(1, userid);
            ResultSet rs = myPS.executeQuery();

            while (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
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
            String myQ = "update user2 set email=?, password=?, phone=? WHERE username=?";

            PreparedStatement myPS = con.prepareStatement(myQ);

            myPS.setString(1, user.getEmail());
            myPS.setString(2, user.getPassword());
            myPS.setString(3, user.getPhone());
            myPS.setString(4, user.getUsername());

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

        String myQ = "DELETE FROM user2 WHERE username=?";
        try {
            PreparedStatement myPs = con.prepareStatement(myQ);
            myPs.setString(1, user.getUsername());

            myPs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
