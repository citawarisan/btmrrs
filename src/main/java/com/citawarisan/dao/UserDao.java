package com.citawarisan.dao;

import com.citawarisan.model.Course;
import com.citawarisan.model.CourseInformation;
import com.citawarisan.model.Faculty;
import com.citawarisan.model.Reservation;
import com.citawarisan.model.Room;
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

    public int save(User user) throws SQLException {
        try {
            String mySQLQuery = "insert into user(user, password, type, name, phone_number, email) values(?, ?, ?, ?, ?, ?)";

            PreparedStatement myPS = con.prepareStatement(mySQLQuery);

            myPS.setString(1, user.getUsername());
            myPS.setString(2, user.getPassword());
            myPS.setInt(3, user.getType());
            myPS.setString(4, user.getName());
            myPS.setString(5, user.getPhone());
            myPS.setString(6, user.getEmail());

            return myPS.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return 0;
    }

    public User authentication(String username, String password) throws SQLException {
        ResultSet rs = null;
        User user = null;

        try {
            String mySQLQuery = "select * from user where user=? and password=?";
            PreparedStatement ps = con.prepareStatement(mySQLQuery);

            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();

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

        } catch (SQLException e) {
            System.out.println(e.getMessage());

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

        }

        return userList;
    }

    public User retrieveUserByUserId(String username) throws ClassNotFoundException, SQLException {

        User user = new User();

        try {
            PreparedStatement myPS = DBConnection.getConnection()
                    .prepareStatement("select * from user where user=?");

            myPS.setString(1, username);
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
        }
        return user;
    }

    public void update(User user) {

        try {
            String myQ = "update user set email=?, password=?, phone_number=?, name=? WHERE user=?";

            PreparedStatement myPS = con.prepareStatement(myQ);

            myPS.setString(1, user.getEmail());
            myPS.setString(2, user.getPassword());
            myPS.setString(3, user.getPhone());
            myPS.setString(4, user.getName());
            myPS.setString(5, user.getUsername());

            myPS.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

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

    public List<CourseInformation> displaySchedule() {
        List<CourseInformation> info = new ArrayList<>();
        try {
            ResultSet rs = con.createStatement().executeQuery(
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
            System.out.println("number of rows" + count);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }

    public List<Course> retrieveUserSubjects(String username) {
        List<Course> c = new ArrayList<>();
        String myQ = "SELECT c.course_code, c.course_name, c.group_number, c.number_of_students, c.exam_hours "
                + "FROM course c "
                + "JOIN user_courses i ON c.course_code = i.course_code "
                + "JOIN user u ON u.user = i.user "
                + "WHERE u.user = ?";
        try {
            PreparedStatement myPS = DBConnection.getConnection()
                    .prepareStatement(myQ);
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

    public List<Faculty> retrieveUserFaculties(String u) {
        List<Faculty> faculties = new ArrayList<>();
        String query = "SELECT f.faculty_id, f.faculty_name FROM faculty f JOIN room r ON f.faculty_id = r.faculty JOIN reservation rs ON r.room_id = rs.room JOIN user u on rs.user = u.user WHERE u.user = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
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

    public List<Room> retrieveUserRooms(String username) {
    List<Room> rooms = new ArrayList<>();

    try{
        String query = "SELECT  r.room_id, r.room_name, r.room_size,r.faculty FROM  room r  JOIN reservation rs ON r.room_id = rs.room JOIN user u on rs.user = u.user WHERE u.user = ?";
        PreparedStatement ps = con.prepareStatement(query);
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
    }catch(SQLException e){
        e.printStackTrace();
    }

    return rooms;
}


    public List<Reservation> retrieveUserReservations(String u) {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT  rs.id, rs.user,rs.room, rs.seats, rs.start_datetime, rs.end_datetime, rs.details, rs.status FROM  reservation rs  JOIN user u on rs.user = u.user WHERE u.user = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, u);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setId(rs.getInt("id"));
                reservation.setUser(rs.getString("user"));
                reservation.setRoom(rs.getString("room"));
                reservation.setSeats(rs.getInt("seats"));
                reservation.setStartDateTime(rs.getTimestamp("start_datetime").toLocalDateTime());
                reservation.setEndDateTime(rs.getTimestamp("end_datetime").toLocalDateTime());
                reservation.setDetails(rs.getString("details"));
                reservation.setStatus(rs.getString("status"));
                // Set other reservation properties as needed
                reservations.add(reservation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservations;
    }
    public void deleteReservation(int id) {

        String myQ = "DELETE FROM reservation WHERE id = ?;";
        try {
            PreparedStatement myPs = con.prepareStatement(myQ);
            myPs.setInt(1, id);

            myPs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
