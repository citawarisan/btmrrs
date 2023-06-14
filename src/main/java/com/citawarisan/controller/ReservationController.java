package com.citawarisan.controller;

import com.citawarisan.dao.ReservationDao;
import com.citawarisan.model.*;
import com.citawarisan.util.ModelChronos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(urlPatterns = {"/home", "/book", "/revise", "/cancel"})
public class ReservationController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sess = req.getSession();
        System.out.println("GET " + req.getRequestURI()); // debug

        // bar access
        if (sess.getAttribute("user") == null) {
            System.out.println("Unauthorized access to /home"); // debug
            resp.sendRedirect("/login");
            return;
        }

        // url path decide where to go next
        String destination;
        switch (req.getRequestURI()) {
            case "/book":
//                generateReservationPage(req);
                String username = ((User) sess.getAttribute("user")).getUsername();
                req.setAttribute("c", new ModelChronos(username));
                destination = "/book/make.jsp";
                break;
            case "/revise":
                Reservation r = new ReservationDao().read(Integer.parseInt(req.getParameter("id")));
                if (r == null) {
                    System.out.println("Failed to read reservation"); // debug
                    resp.sendRedirect("/home");
                    return;
                }
                req.setAttribute("r", r);
                ModelChronos c = new ModelChronos(r.getUser());
                req.setAttribute("c", c);
                destination = "/book/edit.jsp";
                break;
            case "/cancel":
                cancelReservation(req);
            case "/home":
            default:
                sess.setAttribute("error", null);
                destination = "/dashboard.jsp";
        }

        reloadSessionAttributes(req);

        System.out.println("Forwarding to " + destination); // debug
        req.getRequestDispatcher(destination).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("POST " + req.getRequestURI()); // debug

        // url path decide where to go next
        String destination = "/home";
        switch (req.getRequestURI()) {
            case "/revise":
                if (!editReservation(req)) {
                    destination = "/revise?id=" + req.getParameter("id");
                }
                break;
            case "/book":
                if (!createReservation(req)) {
                    destination = "/book";
                }
        }

        System.out.println("Redirecting to " + destination); // debug
        resp.sendRedirect(destination);
    }

    private boolean check(HttpSession sess, Reservation r) {
        ReservationDao dao = new ReservationDao();
//        Reservation rec = dao.read(r.getId());

//        if (rec == null) {
//            sess.setAttribute("error", "Reservation doesnt exists");
//            return false;
//        }

        // check if reservation cancelled
//        System.out.println("Reservation status: " + r.getStatus());
        String status = r.getStatus();
        if (status != null && status.equals("cancelled")) {
            System.out.println("Reservation already cancelled");
            sess.setAttribute("error", "Reservation already cancelled");
            return false;
        }

        // if reservation is outdated
        if (r.getStartDateTime().isBefore(LocalDateTime.now())) {
            System.out.println("Reservation already outdated");
            sess.setAttribute("error", "Reservation already outdated");
            return false;
        }

        // start date and end date should be the same (different time)
        if (!r.getStartDateTime().toLocalDate().equals(r.getEndDateTime().toLocalDate())) {
            System.out.println("Reservation should be in the same day");
            sess.setAttribute("error", "Reservation should be in the same day");
            return false;
        }

        // start date should be older than end date
        if (r.getStartDateTime().isAfter(r.getEndDateTime())) {
            System.out.println("Start date should be older than end date");
            sess.setAttribute("error", "Start date should be older than end date");
            return false;
        }

        // check if any reservation in same room has time conflict
        List<Reservation> reservations = dao.read();
        for (Reservation res : reservations) {
            if (res.getRoom().equals(r.getRoom())) {
                if (r.getStartDateTime().isBefore(res.getEndDateTime()) && r.getEndDateTime().isAfter(res.getStartDateTime())) {
                    System.out.println("Time conflict with another reservation");
                    sess.setAttribute("error", "Time conflict with another reservation");
                    return false;
                }
            }
        }

        // user not altered
        if (!r.getUser().equals(r.getUser())) {
            System.out.println("User altered");
            sess.setAttribute("error", "User altered");
            return false;
        }

        // seats smaller than one
        if (r.getSeats() < 1) {
            System.out.println("Seats must be at least one");
            sess.setAttribute("error", "Seats must be at least one");
            return false;
        }

        // seats larger than room
        if (r.getSeats() > new ModelChronos().daoGetRoom(r.getRoom()).getRoomSize()) {
            System.out.println("Seats larger than room capacity");
            sess.setAttribute("error", "Seats larger than room capacity");
            return false;
        }

        System.out.println("Reservation passed all checks");
        return true;
    }

    private boolean createReservation(HttpServletRequest req) {
        // get reservation data, then create object with it
        LocalDateTime startDateTime = LocalDateTime.parse(req.getParameter("start_datetime"));
        LocalDateTime endDateTime = LocalDateTime.parse(req.getParameter("end_datetime"));
        String room = req.getParameter("room");
        String user = req.getParameter("user");
        int seats = Integer.parseInt(req.getParameter("seats"));
        String details = req.getParameter("course");
        Reservation r = new Reservation();
        r.setUser(user);
        r.setStartDateTime(startDateTime);
        r.setEndDateTime(endDateTime);
        r.setRoom(room);
        r.setSeats(seats);
        r.setDetails(details);
        boolean passed = check(req.getSession(), r);
        System.out.println("Passed: " + passed);
        if (passed) {
            new ReservationDao().create(r);
        }
        return passed;
    }

    private boolean editReservation(HttpServletRequest req) {
        // get reservation data, then create object with it
        int id = Integer.parseInt(req.getParameter("id"));
        LocalDateTime startDateTime = LocalDateTime.parse(req.getParameter("start_datetime")).withSecond(0).withNano(0);
        LocalDateTime endDateTime = LocalDateTime.parse(req.getParameter("end_datetime")).withSecond(0).withNano(0);
        String room = req.getParameter("room");
        String user = req.getParameter("user");
        int seats = Integer.parseInt(req.getParameter("seats"));
        String details = req.getParameter("course");
        Reservation r = new Reservation(id, startDateTime, endDateTime, user, room, seats, "success", details);
        boolean passed = check(req.getSession(), r);
        if (passed) {
            new ReservationDao().update(r);
        }
        return passed;
    }

    private boolean cancelReservation(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = (User) req.getSession().getAttribute("user");
        boolean s = new ReservationDao().cancel(id, user);
        System.out.println((s ? "Success" : "Failed") + " to cancel reservation"); // debug
        return s;
    }

    private void reloadSessionAttributes(HttpServletRequest req) {
        HttpSession sess = req.getSession();
        String username = ((User) sess.getAttribute("user")).getUsername();
        sess.setAttribute("rv", new ReservationDao().readV(username));
    }

    private void generateReservationPage(HttpServletRequest req) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = ((User) session.getAttribute("user")).getUsername();
        List<Course> c = new ReservationDao().getUserCourses(username);
        List<Reservation> rs = new ReservationDao().read(username);
        List<Room> r = new ReservationDao().readRooms();
        req.setAttribute("c", c);
        req.setAttribute("rs", rs);
        req.setAttribute("r", r);
        session.setAttribute("error", null);
    }
}