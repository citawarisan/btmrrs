package com.citawarisan.controller;

import com.citawarisan.dao.ReservationDao;
import com.citawarisan.model.*;
import com.citawarisan.util.ModelChronos;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(urlPatterns = {"/home", "/book", "/revise", "/cancel","/approve"})
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
                generateReservationPage(req);
                destination = "/book/reserve.jsp";
                break;
              case "/approve":
               approveReservation(req);
                destination = "/home";
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
                    destination = "/book?id=" + req.getParameter("id");
                }
        }

        System.out.println("Redirecting to " + destination); // debug
        resp.sendRedirect(destination);
    }

    private boolean check(HttpSession sess, Reservation r) {
        ReservationDao dao = new ReservationDao();
        Reservation rec = dao.read(r.getId());

        if (rec == null) {
            sess.setAttribute("error", "Reservation doesnt exists");
            return false;
        }

        // check if reservation cancelled
        if (rec.getStatus().equals("cancelled")) {
            sess.setAttribute("error", "Reservation already cancelled");
            return false;
        }

        // if reservation is outdated
        if (rec.getStartDateTime().isBefore(LocalDateTime.now())) {
            sess.setAttribute("error", "Reservation already outdated");
            return false;
        }

        for (Reservation s : dao.read()) {
            if (r.getRoom().equals(s.getRoom()) && // same room
                    (r.getStartDateTime() == s.getStartDateTime() || // same start time
                            (r.getStartDateTime().isAfter(s.getStartDateTime()) && r.getStartDateTime().isBefore(s.getEndDateTime())) || // between duration
                            (r.getEndDateTime().isAfter(s.getStartDateTime()) && r.getEndDateTime().isBefore(s.getEndDateTime())))) {
                sess.setAttribute("error", "Reservation time conflict");
                return false;
            }
        }

        // user not altered
        if (!rec.getUser().equals(r.getUser())) {
            sess.setAttribute("error", "User altered");
            return false;
        }

        // seats smaller than one
        if (r.getSeats() < 1) {
            sess.setAttribute("error", "Seats must be at least one");
            return false;
        }

        return true;
    }

    private boolean createReservation(HttpServletRequest req) {
//        sess.setAttribute("error", "Failed to create reservation");
//        System.out.println("Failed to create reservation"); // debug
        return false;
    }

    private boolean editReservation(HttpServletRequest req) {
        // get reservation data, then create object with it
        int id = Integer.parseInt(req.getParameter("id"));
        LocalDateTime startDateTime = LocalDateTime.parse(req.getParameter("start_datetime"));
        LocalDateTime endDateTime = LocalDateTime.parse(req.getParameter("end_datetime"));
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
    private boolean approveReservation(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = (User) req.getSession().getAttribute("user");
        boolean s = new ReservationDao().approve(id, user);
        System.out.println((s ? "Success" : "Failed") + " to approve reservation"); // debug
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
  private void generateReservationAdmin(HttpServletRequest req) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = ((User) session.getAttribute("user")).getUsername();

        List<Reservation> rs =  new ReservationDao().read();
        req.setAttribute("rs", rs);


    }
}