package com.citawarisan.util;

import com.citawarisan.model.Course;
import com.citawarisan.model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ModelChronos {
    String username;

    public ModelChronos() {
    }

    public ModelChronos(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Room daoGetRoom(String id) {
        Room room = null;
        String query = "SELECT * FROM Room WHERE room_id = ?";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(query)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    room = new Room(rs.getString("room_id"), rs.getString("room_name"), rs.getInt("room_size"), rs.getInt("faculty"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    public List<Room> daoGetRooms() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM Room";
        try (Connection c = DBConnection.getConnection(); ResultSet rs = c.createStatement().executeQuery(query)) {
            while (rs.next()) {
                rooms.add(new Room(rs.getString("room_id"), rs.getString("room_name"), rs.getInt("room_size"), rs.getInt("faculty")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public void daoUpdateCourse(String courseCode, String courseName, int groupNumber, int numberOfStudents, int examHours) {
        String query = "UPDATE Course SET course_name = ?, group_number = ?, number_of_students = ?, exam_hours = ? WHERE course_code = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, courseName);
            ps.setInt(2, groupNumber);
            ps.setInt(3, numberOfStudents);
            ps.setInt(4, examHours);
            ps.setString(5, courseCode);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void daoDeleteCourse(String courseCode) {
        String query = "DELETE FROM Course WHERE course_code = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, courseCode);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Course daoGetCourse(String courseCode) {
        Course course = null;
        String query = "SELECT * FROM Course WHERE course_code = ?";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(query)) {
            ps.setString(1, courseCode);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    course = new Course(rs.getString("course_code"), rs.getString("course_name"), rs.getInt("group_number"), rs.getInt("number_of_students"), rs.getInt("exam_hours"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }

    public void daoAddCourse(String courseCode, String courseName, int groupNumber, int numberOfStudents, int examHours) {
        String query = "INSERT INTO Course VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, courseCode);
            ps.setString(2, courseName);
            ps.setInt(3, groupNumber);
            ps.setInt(4, numberOfStudents);
            ps.setInt(5, examHours);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Course> daoGetCourses() {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM Course";
        try (Connection c = DBConnection.getConnection(); ResultSet rs = c.createStatement().executeQuery(query)) {
            while (rs.next()) {
                courses.add(new Course(rs.getString("course_code"), rs.getString("course_name"), rs.getInt("group_number"), rs.getInt("number_of_students"), rs.getInt("exam_hours")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public List<Course> daoGetCourses(String username) {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM Course c JOIN User_Courses uc ON c.course_code = uc.course_code WHERE uc.user = ?";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(query)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    courses.add(new Course(rs.getString("course_code"), rs.getString("course_name"), rs.getInt("group_number"), rs.getInt("number_of_students"), rs.getInt("exam_hours")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    // get datetime now plus i days, j hours
    public String getFuture(int hours) {
        String n = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0).plusDays(1).plusHours(hours).toString();
        System.out.println(n);
        return n;
    }
}
