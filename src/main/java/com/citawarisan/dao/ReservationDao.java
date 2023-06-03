package com.citawarisan.dao;

import com.citawarisan.model.Reservation;
import com.citawarisan.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao extends ModelDao<Reservation> {
    protected List<Reservation> parseModels(ResultSet rs) throws SQLException {
        List<Reservation> reservations = new ArrayList<>();

        while (rs.next()) {
            Reservation reservation = new Reservation();
            reservation.setId(rs.getInt("id"));
            reservation.setUser(rs.getString("user"));
            reservation.setStartDateTime(rs.getTimestamp("start_datetime").toLocalDateTime());
            reservation.setEndDateTime(rs.getTimestamp("end_datetime").toLocalDateTime());
            reservation.setRoom(rs.getString("room"));
            reservation.setSeats(rs.getInt("seats"));
            reservation.setStatus(rs.getString("status"));
            reservation.setDetails(rs.getString("details"));
            reservations.add(reservation);
        }

        return reservations;
    }

    protected PreparedStatement parseQuery(String query, Reservation reservation) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, reservation.getUser());
        ps.setString(2, reservation.getRoom());
        ps.setInt(3, reservation.getSeats());
        ps.setTimestamp(4, Timestamp.valueOf(reservation.getStartDateTime()));
        ps.setTimestamp(5, Timestamp.valueOf(reservation.getEndDateTime()));
        ps.setString(6, reservation.getDetails());
        ps.setString(7, reservation.getStatus());
        return ps;
    }

    public boolean create(Reservation reservation) {
        try {
            String query = "INSERT INTO Reservation VALUE (?, ?, ?, ?, ?, ?, ?)";
            parseQuery(query, reservation).execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Reservation> read() {
        try {
            String query = "SELECT * FROM Reservation";
            ResultSet rs = connection.createStatement().executeQuery(query);

            return parseModels(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // select by username
    public List<Reservation> read(User user) {
        try {
            String query = "SELECT * FROM Reservation WHERE user = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getUsername());

            ResultSet rs = ps.executeQuery();

            return parseModels(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean update(Reservation reservation) {
        try {
            String query = "UPDATE Reservation SET user = ?, room = ?, seats = ?, start_datetime = ?, end_datetime = ?, details = ?, status = ? WHERE id = ?";
            PreparedStatement ps = parseQuery(query, reservation);
            ps.setInt(8, reservation.getId());
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(Reservation reservation) {
        try {
            String query = "DELETE FROM Reservation WHERE user = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, reservation.getUser());
            ps.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}

