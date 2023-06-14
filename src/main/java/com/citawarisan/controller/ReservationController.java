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
import java.time.LocalDate;
import java.time.LocalDateTime;

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
                destination = "/book/reserve.jsp";
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
                    destination = "/book";
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
        new ReservationDao().update(r);
        return true;
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
}
