package com.citawarisan.dao;

import com.citawarisan.model.Reservation;
import com.citawarisan.model.User;
import com.citawarisan.util.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao {

    private final Connection connection;

    public ReservationDao() throws ClassNotFoundException {
        connection = DBConnection.getConnection();
    }

    public void displayReservation(Reservation user) {
        try {
            String insertion = "INSERT INTO users(userid, firstname, lastname) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertion);

//            preparedStatement.setString(1, user.getUserid());
//            preparedStatement.setString(2, user.getFirstname());
//            preparedStatement.setString(3, user.getLastname());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String userid) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE userid= ?");
            preparedStatement.setString(1, userid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updtateUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET firstname = ?, lastname = ?  WHERE userid = ?");
//            preparedStatement.setString(1, user.getFirstname());
//            preparedStatement.setString(2, user.getLastname());
//            preparedStatement.setString(3, user.getUserid());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Reservation> displayReservation() {
        List<Reservation> reservationList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM reservation");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Reservation resTemp = new Reservation();
                resTemp.setId(rs.getInt(1));
                Timestamp ts = rs.getTimestamp(2);

                Date date = new Date(ts.getTime());
                LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                resTemp.setDatetime(localDateTime);

                resTemp.setUser(rs.getString(3));
                resTemp.setRoom(rs.getString(4));
                resTemp.setSeats(rs.getInt(5));
                resTemp.setResStatus(rs.getString(6));
                resTemp.setDetails(rs.getString(7));
                reservationList.add(resTemp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationList;
    }

    public User getUserById(String id) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE userid = ?");
            preparedStatement.setString(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
//                user.setUserid(rs.getString("userid"));
//                user.setFirstname(rs.getString("firstname"));
//                user.setLastname(rs.getString("lastname"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}

