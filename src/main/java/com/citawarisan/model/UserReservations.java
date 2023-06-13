/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.citawarisan.model;

import java.util.List;

/**
 *
 * @author Omar Alomory(S63955)
 */
public class UserReservations {
    private List<Faculty> faculties;
    private List<Room> rooms;
    private List<Reservation> reservations;

    public UserReservations(List<Faculty> faculties, List<Room> rooms, List<Reservation> reservations) {
        this.faculties = faculties;
        this.rooms = rooms;
        this.reservations = reservations;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}

