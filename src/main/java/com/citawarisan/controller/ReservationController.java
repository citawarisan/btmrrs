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
import java.util.List;

@WebServlet(urlPatterns = {"/home"})
public class ReservationController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sess = req.getSession();
        System.out.println("GET " + req.getRequestURI()); // debug

        // url path decide where to go next
        String destination;
        switch (req.getRequestURI()) {
            case "/book": // TODO
            case "/home":
                if (sess.getAttribute("user") == null) {
                    resp.sendRedirect("/login");
                    System.out.println("Unauthorized access to /home"); // debug
                    return;
                }
                reloadSessionAttributes(req);
            default:
                destination = "/dashboard.jsp";
        }

        System.out.println("Forwarding to " + destination); // debug
        req.getRequestDispatcher(destination).forward(req, resp);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        System.out.println("POST " + req.getRequestURI()); // debug
//
//        // url path decide where to go next
//        String destination;
//
//        System.out.println("Redirecting to " + destination); // debug
//        resp.sendRedirect(destination);
//    }

    private void displayList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CourseInformation> ci = new ReservationDao().displaySchedule();

        RequestDispatcher rd = req.getRequestDispatcher("studentDisplay.jsp");
        req.setAttribute("errorMessage", "false");
        req.setAttribute("studentInfo", ci);

        rd.forward(req, resp);
    }

    private void deleteReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("rsid"));

        new ReservationDao().delete(id);
        RequestDispatcher rd = request.getRequestDispatcher("UserController");
        request.setAttribute("action", "regenerate");
        rd.forward(request, response);
    }

    private void reloadSessionAttributes(HttpServletRequest req) {
        HttpSession sess = req.getSession();
        String username = ((User) sess.getAttribute("user")).getUsername();
        sess.setAttribute("rv", new ReservationDao().readV(username));
    }
}
