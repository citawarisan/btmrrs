package com.citawarisan.controller;

import com.citawarisan.dao.UserDao;
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
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GET " + req.getRequestURI()); // debug

        // url path decide where to go next
        String destination;
        switch (req.getRequestURI()) {
            case "/book": // TODO
            case "/home":
                regenerate(req);
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
        List<CourseInformation> ci = new UserDao().displaySchedule();

        RequestDispatcher rd = req.getRequestDispatcher("studentDisplay.jsp");
        req.setAttribute("errorMessage", "false");
        req.setAttribute("studentInfo", ci);

        rd.forward(req, resp);
    }

    private void deleteReserve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("rsid"));

        UserDao userDAO = new UserDao();

        userDAO.deleteReservation(id);
        RequestDispatcher rd = request.getRequestDispatcher("UserController");
        request.setAttribute("action", "regenerate");
        rd.forward(request, response);
    }

    private void regenerate(HttpServletRequest req) {
        UserDao userDAO = new UserDao();
        HttpSession session = req.getSession();
        String user = ((User) session.getAttribute("user")).getUsername();
        List<Course> c = userDAO.retrieveUserSubjects(user);
        List<Faculty> f = userDAO.retrieveUserFaculties(user);
        List<Room> r = userDAO.retrieveUserRooms(user);
        List<Reservation> rs = userDAO.retrieveUserReservations(user);

        req.setAttribute("f", f);
        req.setAttribute("r", r);
        req.setAttribute("rs", rs);
        req.setAttribute("courses", c);
    }
}
