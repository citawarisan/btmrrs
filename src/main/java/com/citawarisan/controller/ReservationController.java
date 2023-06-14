package com.citawarisan.controller;

import com.citawarisan.dao.ReservationDao;
import com.citawarisan.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

        String destination = "/home";
        System.out.println("What the heck? Redirecting to " + destination); // debug
        resp.sendRedirect(destination);
    }

    private void cancelReservation(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = (User) req.getSession().getAttribute("user");
        boolean success = new ReservationDao().cancel(id, user);
        System.out.println((success ? "Success" : "Failed") + " to cancel reservation"); // debug
    }

    private void reloadSessionAttributes(HttpServletRequest req) {
        HttpSession sess = req.getSession();
        String username = ((User) sess.getAttribute("user")).getUsername();
        sess.setAttribute("rv", new ReservationDao().readV(username));
    }
}
