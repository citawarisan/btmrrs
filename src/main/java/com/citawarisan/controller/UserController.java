package com.citawarisan.controller;

import com.citawarisan.dao.UserDao;
import com.citawarisan.model.*;
import com.citawarisan.util.DBConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

// it's the opposite, if you have no reason to use web.xml don't use it
@WebServlet(urlPatterns = {"/login", "/signup", "/logout", "/update"})
public class UserController extends HttpServlet {
    @Override
    public void init() {
        DBConnection.setConnection("root", "");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("GET " + request.getRequestURI()); // debug

        // url path decide where to go next
        String destination;
        switch (request.getRequestURI()) {
            case "/logout":
                logout(request);
                response.sendRedirect("/login");
                return;
            case "/update":
                HttpSession sess = request.getSession();
                User user = (User) sess.getAttribute("user");
                if (user.getType() != 1 && !user.getUsername().equals(request.getParameter("username"))) {
                    System.out.println("Unauthorized access to update user");
                    response.sendRedirect("/login");
                    return;
                }
                User editUser = new UserDao().getUser(request.getParameter("username"));
                request.setAttribute("editUser", editUser);
                destination = "/auth/edit.jsp";
                break;
            case "/signup":
                destination = "/auth/signup.jsp";
                break;
            case "/login":
            default:
                destination = "/auth/login.jsp";
                // but wait, if user session exists
                if (request.getSession().getAttribute("user") != null) {
                    response.sendRedirect("/dashboard.jsp");
                    return;
                }
        }

        System.out.println("Forwarding to " + destination); // debug
        request.getRequestDispatcher(destination).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("POST " + request.getRequestURI()); // debug

        // url path decide where to go next
        boolean success;
        String destination = "/login";
        switch (request.getRequestURI()) {
            case "/login":
                success = login(request);
                if (success) {
                    destination = "/dashboard.jsp";
                }
                break;
            case "/update":
                success = updateUser(request);
                destination = (success) ? "/dashboard.jsp" : "/update";
                break;
            case "/signup":
                success = signup(request);
                if (!success) {
                    destination = "/signup";
                }
        }

        System.out.println("Redirecting to " + destination); // debug
        response.sendRedirect(destination);
    }

    private void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        String username = (String) request.getSession().getAttribute("user");
        System.out.println(username);

        User user = new UserDao().getUser(username);
        System.out.println(user.toString());
        RequestDispatcher rd = request.getRequestDispatcher("auth/edit.jsp");

        request.setAttribute("user", user);
        rd.forward(request, response);
    }

    private void nukeUser(HttpServletRequest req) {
        String username = req.getParameter("username");

        // blame Gary
        User user = new User();
        user.setUsername(username);

        new UserDao().delete(user);

        HttpSession session = req.getSession();
        session.invalidate();
    }

    private boolean updateUser(HttpServletRequest req) {
        //get all data from update
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        int type = Integer.parseInt(req.getParameter("type"));
        String email = req.getParameter("email");

        //keep data into javabeans
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setPhone(phone);
        user.setType(type);
        user.setEmail(email);

        boolean success = new UserDao().updateUser(user);
        if (success) {
            HttpSession sess = req.getSession();
            sess.setAttribute("user", user);
        }

        return success;
    }

    private boolean login(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // check if user exists
        UserDao userDAO = new UserDao();
        User user = userDAO.matchUser(username, password);
        boolean success = user != null;

        HttpSession sess = req.getSession();
        sess.setAttribute("errorMessage", String.valueOf(success));
        if (success) {
            sess.setAttribute("user", user);
            System.out.println("Session user: " + sess.getAttribute("user"));
        } else {
            sess.setAttribute("error", "Invalid username or password!");
        }

        return success;
    }

    private boolean signup(HttpServletRequest req) {
        //get all data from signup
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        int type = Integer.parseInt(req.getParameter("type"));
        String email = req.getParameter("email");

        //keep data into javabeans
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setPhone(phone);
        user.setType(type);
        user.setEmail(email);

        HttpSession sess = req.getSession();

        //pass the bean to DAO
        System.out.println(user);
        boolean success = new UserDao().createUser(user) > 0;
        sess.setAttribute("errorMessage", String.valueOf(success));
        if (success) {
            //save the bean as attribute and pass to view
            sess.setAttribute("user", user);
            System.out.println("Session user: " + sess.getAttribute("user"));
        }

        return success;
    }

    private void logout(HttpServletRequest req) {
        HttpSession sess = req.getSession();
        sess.removeAttribute("user");
        System.out.println("Session user: " + sess.getAttribute("user"));
    }

    private void displayList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CourseInformation> ci = new UserDao().displaySchedule();

        RequestDispatcher rd = req.getRequestDispatcher("studentDisplay.jsp");
        req.setAttribute("errorMessage", "false");
        req.setAttribute("studentInfo", ci);

        rd.forward(req, resp);
    }

    private void deleteReserve(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {

        int id = Integer.parseInt(request.getParameter("rsid"));

        UserDao userDAO = new UserDao();

        userDAO.deleteReservation(id);
        RequestDispatcher rd = request.getRequestDispatcher("UserController");
        request.setAttribute("action", "regenerate");
        rd.forward(request, response);

    }

    private void regenerate(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException, ClassNotFoundException {
        UserDao userDAO = new UserDao();
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("user");
        RequestDispatcher rd = req.getRequestDispatcher("dashboard.jsp");
        List<Course> c = userDAO.retrieveUserSubjects(user);
        List<Faculty> f = userDAO.retrieveUserFaculties(user);
        List<Room> r = userDAO.retrieveUserRooms(user);
        List<Reservation> rs = userDAO.retrieveUserReservations(user);

        req.setAttribute("f", f);
        req.setAttribute("r", r);
        req.setAttribute("rs", rs);
        req.setAttribute("courses", c);
        rd.forward(req, res);

    }
}
