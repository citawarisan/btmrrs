package com.citawarisan.dao;

import com.citawarisan.model.CourseInformation;
import com.citawarisan.model.User;
import com.citawarisan.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        String query = "SELECT * FROM User WHERE user=? AND password=?";

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
                user.setUsername(rs.getString("username"));
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
        String query = "UPDATE User SET email=?, password=?, phone_number=?, name=? WHERE user=?";
        boolean success = false;

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getName());
            ps.setString(5, user.getUsername());
            ps.executeUpdate();

            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    public void delete(User user) {
        String query = "DELETE FROM User WHERE user=?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, user.getUsername());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CourseInformation> displaySchedule() {
        List<CourseInformation> info = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            ResultSet rs = conn.createStatement().executeQuery(
                    "SELECT c.course_code, c.course_name, u.name AS user_name, c.number_of_students, "
                            + "DATE(r.start_datetime) AS date, DAYNAME(r.start_datetime) AS day, "
                            + "c.exam_hours AS number_of_hours, TIME_FORMAT(r.start_datetime, '%H:%i') "
                            + "AS start_time, TIME_FORMAT(r.end_datetime, '%H:%i') AS end_time, f.faculty_name, rm.room_name FROM Course c JOIN User_Courses uc ON c.course_code = uc.course_code "
                            + "JOIN Reservation r ON uc.user = r.user "
                            + "JOIN Room rm ON r.room = rm.room_id "
                            + "JOIN Faculty f ON rm.faculty = f.faculty_id "
                            + "JOIN User u ON uc.user = u.user "
                            + "WHERE u.type = 2 ORDER BY c.course_code");
            int count = 0;
            while (rs.next()) {
                count++;
                CourseInformation courseInformation = new CourseInformation();
                courseInformation.setCourseCode(rs.getString(1));
                courseInformation.setCourseName(rs.getString(2));
                courseInformation.setUserName(rs.getString(3));
                courseInformation.setNumberOfStudents(rs.getInt(4));
                courseInformation.setDate(rs.getString(5));
                courseInformation.setDay(rs.getString(6));
                courseInformation.setNumberOfHours(rs.getInt(7));
                courseInformation.setStartTime(rs.getString(8));
                courseInformation.setEndTime(rs.getString(9));
                courseInformation.setFaculty(rs.getString(10));
                courseInformation.setRoom(rs.getString(11));

                info.add(courseInformation);
            }
            System.out.println("number of rows"+count);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }
}
