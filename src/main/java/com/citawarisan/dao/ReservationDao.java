package com.citawarisan.dao;

import com.citawarisan.model.*;
import com.citawarisan.util.DBConnection;
import com.citawarisan.view.ReservationView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservationDao {

    private void parseModel(Reservation r, ResultSet rs) throws SQLException {
        r.setId(rs.getInt("id"));
        r.setUser(rs.getString("user"));
        r.setStartDateTime(rs.getTimestamp("start_datetime").toLocalDateTime());
        r.setEndDateTime(rs.getTimestamp("end_datetime").toLocalDateTime());
        r.setRoom(rs.getString("room"));
        r.setSeats(rs.getInt("seats"));
        r.setStatus(rs.getString("status"));
        r.setDetails(rs.getString("details"));
    }

    private void parseStatement(Reservation r, PreparedStatement ps) throws SQLException {
        ps.setString(1, r.getUser());
        ps.setString(2, r.getRoom());
        ps.setInt(3, r.getSeats());
        ps.setTimestamp(4, Timestamp.valueOf(r.getStartDateTime()));
        ps.setTimestamp(5, Timestamp.valueOf(r.getEndDateTime()));
        ps.setString(6, r.getDetails());
        ps.setString(7, r.getStatus());
    }

    protected List<Reservation> parseModels(ResultSet rs) throws SQLException {
        List<Reservation> reservations = new ArrayList<>();

        while (rs.next()) {
            Reservation reservation = new Reservation();
            parseModel(reservation, rs);
            reservations.add(reservation);
        }
        rs.close();

        return reservations;
    }

    public boolean create(Reservation r) {
        String query = "INSERT INTO Reservation (user, room, seats, start_datetime, end_datetime, details, status) VALUE (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            parseStatement(r, ps);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Reservation> read() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM Reservation";
            ResultSet rs = conn.createStatement().executeQuery(query);

            return parseModels(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Reservation read(int id) {
        String query = "SELECT * FROM Reservation WHERE id = ?";
        Reservation r = null;

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                r = new Reservation();
                parseModel(r, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return r;
    }

    // select by username
    public List<Reservation> read(String username) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM Reservation WHERE user = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            return parseModels(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ReservationView readV(String username) {
        String query = "SELECT * FROM Reservation_View WHERE user = ? ORDER BY id DESC LIMIT 5;";
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            // convoluted scam
            ReservationView rv = new ReservationView();
            List<ReservationView> rvs = new ArrayList<>();
            while (rs.next()) {
                rv = new ReservationView();
                rv.setId(rs.getInt("id"));
                rv.setUser(rs.getString("user"));
                rv.setDate(rs.getTimestamp("date").toLocalDateTime());
                rv.setTime(rs.getString("time"));
                rv.setRoom(rs.getString("room"));
                rv.setStatus(rs.getString("status"));
                rv.setDetails(rs.getString("details"));
                rvs.add(rv);
            }
            rs.close();

            rv.setList(rvs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ReservationView();
    }

    public boolean update(Reservation r) {
        String query = "UPDATE Reservation SET user = ?, room = ?, seats = ?, start_datetime = ?, end_datetime = ?, details = ?, status = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            parseStatement(r, ps);
            ps.setInt(8, r.getId());
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM Reservation WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean allowed(User u, Reservation r) {
        return u.getType() == 1 || r.getUser().equals(u.getUsername());
    }

    public boolean cancel(int id, User u) {
        Reservation r = read(id);
        if (r == null) {
            System.out.println("Reservation not found");
            return false;
        }

        if (!allowed(u, r)) {
            System.out.println("Illegal access");
            return false;
        }

        r.setStatus("Cancelled");
        return update(r);
    }

    public List<CourseInformation> displaySchedule() {
        List<CourseInformation> info = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT c.course_code, c.course_name, u.name AS user_name, c.number_of_students, " + "DATE(r.start_datetime) AS date, DAYNAME(r.start_datetime) AS day, " + "c.exam_hours AS number_of_hours, TIME_FORMAT(r.start_datetime, '%H:%i') " + "AS start_time, TIME_FORMAT(r.end_datetime, '%H:%i') AS end_time, f.faculty_name, rm.room_name FROM Course c JOIN User_Courses uc ON c.course_code = uc.course_code " + "JOIN Reservation r ON uc.user = r.user " + "JOIN Room rm ON r.room = rm.room_id " + "JOIN Faculty f ON rm.faculty = f.faculty_id " + "JOIN User u ON uc.user = u.user " + "WHERE u.type = 2 ORDER BY c.course_code");
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
            System.out.println("number of rows" + count);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }

    public List<Course> getUserCourses(String username) {
        List<Course> c = new ArrayList<>();
        String myQ = "SELECT c.course_code, c.course_name, c.group_number, c.number_of_students, c.exam_hours " + "FROM course c " + "JOIN user_courses i ON c.course_code = i.course_code " + "JOIN user u ON u.user = i.user " + "WHERE u.user = ?";
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement myPS = DBConnection.getConnection().prepareStatement(myQ);
            myPS.setString(1, username);

            ResultSet rs = myPS.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setCourseCode(rs.getString(1));
                course.setCourseName(rs.getString(2));
                course.setGroupNumber(rs.getInt(3));
                course.setNumberOfStudents(rs.getInt(4));
                course.setExamHours(rs.getInt(5));
                c.add(course);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public List<Faculty> getUserFaculties(String u) {
        List<Faculty> faculties = new ArrayList<>();
        String query = "SELECT f.faculty_id, f.faculty_name FROM faculty f JOIN room r ON f.faculty_id = r.faculty JOIN reservation rs ON r.room_id = rs.room JOIN user u on rs.user = u.user WHERE u.user = ?";
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, u);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Faculty faculty = new Faculty();
                faculty.setFacultyId(rs.getInt("faculty_id"));
                faculty.setFacultyName(rs.getString("faculty_name"));

                faculties.add(faculty);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return faculties;
    }

    public List<Room> getUserRooms(String username) {
        List<Room> rooms = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT  r.room_id, r.room_name, r.room_size,r.faculty FROM  room r  JOIN reservation rs ON r.room_id = rs.room JOIN user u on rs.user = u.user WHERE u.user = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setRoomId(rs.getString("room_id"));
                room.setRoomName(rs.getString("room_name"));
                room.setRoomSize(rs.getInt("room_size"));
                room.setFaculty(rs.getInt("faculty"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    public void deleteReservation(int id) {

        String myQ = "DELETE FROM reservation WHERE id = ?;";
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement myPs = conn.prepareStatement(myQ);
            myPs.setInt(1, id);

            myPs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

