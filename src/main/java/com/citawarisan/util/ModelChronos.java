package com.citawarisan.util;

import com.citawarisan.model.Course;
import com.citawarisan.model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public List<Course> daoGetCourses() {
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
}
