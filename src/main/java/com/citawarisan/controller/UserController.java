package com.citawarisan.controller;

import com.citawarisan.dao.UserDao;
import com.citawarisan.model.*;
import com.citawarisan.util.DBConnection;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login", "/signup", "/logout", "/update"})
public class UserController extends HttpServlet {
    @Override
    public void init() {
       // DBConnection.setConnection("root", null);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sess = req.getSession();

        System.out.println("GET " + req.getRequestURI()); // debug

        // url path decide where to go next
        String destination;
        switch (req.getRequestURI()) {
            case "/logout":
                logout(req);
                resp.sendRedirect("/login");
                return;
            case "/update":
                User user = (User) sess.getAttribute("user");
                if (user.getType() != 1 && !user.getUsername().equals(req.getParameter("username"))) {
                    System.out.println("Unauthorized access to update user");
                    resp.sendRedirect("/login");
                    return;
                }
                User editUser = new UserDao().getUser(req.getParameter("username"));
                req.setAttribute("editUser", editUser);
                destination = "/auth/edit.jsp";
                break;
            case "/signup":
                destination = "/auth/signup.jsp";
                break;
            case "/login":
            default:
                destination = "/auth/login.jsp";
                // but wait, if user session exists
                if (sess.getAttribute("user") != null) {
                    resp.sendRedirect("/home");
                    return;
                }
        }

        System.out.println("Forwarding to " + destination); // debug
        req.getRequestDispatcher(destination).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("POST " + req.getRequestURI()); // debug

        // url path decide where to go next
        boolean success;
        String destination = "/login";
        switch (req.getRequestURI()) {
            case "/login":
                success = login(req);
                if (success) {
                    destination = "/home";
                }
                break;
            case "/update":
                success = updateUser(req);
                destination = (success) ? "/home" : "/update";
                break;
            case "/signup":
                success = signup(req);
                if (!success) {
                    destination = "/signup";
                }
        }

        System.out.println("Redirecting to " + destination); // debug
        resp.sendRedirect(destination);
    }

    private void nukeUser(HttpServletRequest req) {
        String username = req.getParameter("username");
        new UserDao().deleteUser(username);

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

    private void logout(HttpServletRequest req) {
        HttpSession sess = req.getSession();
        sess.removeAttribute("user");
        System.out.println("Session user: " + sess.getAttribute("user"));
    }
}
