/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.citawarisan.dao;

import com.citawarisan.util.DBConnection;
import com.citawarisan.model.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author azimm
 */
public class AdminDao {

    private final Connection con;

    public AdminDao() throws SQLException, ClassNotFoundException {
        con = DBConnection.getConnection();
    }

    public Admin authentication(String email, String password) throws SQLException {
        ResultSet rs = null;
        Admin user = null;

        try {
            String mySQLQuery = "select * from admin where email=? and password=?";
            PreparedStatement ps = con.prepareStatement(mySQLQuery);

            ps.setString(1, email);
            ps.setString(2, password);

            rs = ps.executeQuery();

            while (rs.next()) {
                user = new Admin();

                user.setEmail(email);
                user.setPassword(password);
                user.setAdminid(rs.getString(1));

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
//    
//    public List<User> retrieveAll() throws SQLException {
//        List<User> userList = new ArrayList<>();
//        
//        User user;
//        ResultSet rs = null;
//        
//        try {
//            String sqlQuery = "select * from users";
//            PreparedStatement stat = con.prepareStatement(sqlQuery);
//            rs = stat.executeQuery(sqlQuery);
//            
//            while(rs.next()){
//                user = new User();
//                
//                user.setFullName(rs.getString(1));
//                user.setEmail(rs.getString(2));
//                user.setPassword(rs.getString(3));
//                
//                userList.add(user);
//            }
//            
//        }catch (SQLException e){
//            System.out.println(e.getMessage());
//            
//        } finally {
//            if(rs != null){
//                rs.close();
//            }
//            
//            con.close();
//        }
//        
//        return userList;
//    }
//    

    public Admin retrieveUserByUserId(int userid) throws ClassNotFoundException, SQLException {

        Admin user = new Admin();

        try {
            PreparedStatement myPS = DBConnection.getConnection()
                    .prepareStatement("select * from user2 where userid=?");

            myPS.setInt(1, userid);
            ResultSet rs = myPS.executeQuery();

            while (rs.next()) {
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("pass"));
                user.setAdminid(rs.getString("userid"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.close();
        }
        return user;
    }

    public void update(Admin user) {

        try {
            String myQ = "update user2 set email=?, pass=?, WHERE userid=?";

            PreparedStatement myPS = con.prepareStatement(myQ);

            myPS.setString(1, user.getEmail());
            myPS.setString(2, user.getPassword());
            myPS.setString(3, user.getAdminid());

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
//    
//    public void delete(User user){
//        
//        String myQ = "DELETE FROM users WHERE userid=?";
//        try {
//            PreparedStatement myPs = con.prepareStatement(myQ);
//            myPs.setInt(1, user.getUserid());
//        
//            myPs.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }

    public void save(Admin newAdmin) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
