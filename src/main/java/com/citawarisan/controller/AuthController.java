package com.citawarisan.controller;

import com.citawarisan.dao.UserDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.citawarisan.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// it's the opposite, if you have no reason to use web.xml don't use it
@WebServlet(urlPatterns = {"/login", "/signup"})
public class AuthController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "register":
                    saveUser(request, response);
                    break;

                case "login":
                    retrieveUser(request, response);
                    break;

                case "update":
                    updateUser(request, response);
                    break;
                case "showUpdateForm":
                    showUpdatForm(request, response);
                    break;

                case "delete":
                    deleteUser(request, response);
                    break;

                case "signout":
                    signOut(request, response);
                    break;
            }
        } else {

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getRequestURI()); // debug

        // url path decide where to go next
        String destination;
        switch (request.getRequestURI()) {
            case "/signup":
                destination = "/auth/signup.jsp";
                break;
            case "/login":
            default:
                destination = "/auth/login.jsp";
        }

        request.getRequestDispatcher(destination).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("enter");
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AuthController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuthController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException, ClassNotFoundException {

        //get all data from signup
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        int type = Integer.parseInt(req.getParameter("type"));
        String email = req.getParameter("email");

        //keep data into javabeans
        User newUser = new User();

        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setName(name);
        newUser.setPhone(phone);
        newUser.setType(type);
        newUser.setEmail(email);

        //pass the bean to DAO
        UserDao user = new UserDao();
        RequestDispatcher rd = req.getRequestDispatcher("auth/signup.jsp");
        int result = user.save(newUser);
        if (result > 0) {
            //save the bean as attribute and pass to view
            req.setAttribute("user", newUser);
            req.setAttribute("errorMessage", "success");
            rd.forward(req, resp);
        } else {
            req.setAttribute("errorMessage", "fasle");
            rd.forward(req, resp);
        }
    }

    private void retrieveUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException, ClassNotFoundException {

        if (req.getParameter("action") != null) {

            String username = req.getParameter("username");
            String password = req.getParameter("password");

            UserDao userDAO = new UserDao();

            try {
                User user = userDAO.authentication(username, password);

                if (user != null) {

                    HttpSession session = req.getSession();
                    session.setAttribute("user", user.getUsername());
                    session.setAttribute("userObject", user);
                    System.out.println(session.getAttribute("user"));
                    resp.sendRedirect("dashboard.jsp");

                } else {
                    req.setAttribute("error", "Invalid username or password!");
                    req.setAttribute("errorMessage", "false");
                    RequestDispatcher rd = req.getRequestDispatcher("auth/login.jsp");
                    rd.forward(req, resp);
                }

            } catch (SQLException e) {
                throw new ServletException(e);
            }
        }

    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        User user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setPhone(phone);
        user.setEmail(email);

        // we can do anything with user object coming back here, but idk for now(sendRedirect)
        new UserDao().update(user);

//        HttpSession session = request.getSession();
//        session.setAttribute("user", user);
        System.out.println(user);
        response.sendRedirect("dashboard.jsp");
    }

    private void showUpdatForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
        String username = (String) request.getSession().getAttribute("user");
        System.out.println(username);

        User user = new UserDao().retrieveUserByUserId(username);
        System.out.println(user.toString());
        RequestDispatcher rd = request.getRequestDispatcher("auth/userProfile.jsp");

        request.setAttribute("user", user);
        rd.forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {

        String username = request.getParameter("username");

        User user = new User();

        user.setUsername(username);

        UserDao userDAO = new UserDao();

        userDAO.delete(user);

        HttpSession session = request.getSession();
        session.invalidate();

        response.sendRedirect("userAdmin.jsp");

    }

    private void signOut(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {

        req.getSession().removeAttribute("user");

        resp.sendRedirect("index.html");
    }

}
