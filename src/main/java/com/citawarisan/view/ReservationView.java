package com.citawarisan.view;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationView {
    private static List<ReservationView> rvs = new ArrayList<>();
    private int id;
    private String user;
    private LocalDateTime date;
    private String time, room, details, status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void add() {
        rvs.add(this);
    }

    public List<ReservationView> getList() {
        return rvs;
    }

    public static void flush() {
        rvs.clear();
    }
}
