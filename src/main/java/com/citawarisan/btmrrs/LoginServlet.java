package com.citawarisan.btmrrs;

import com.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the request has form parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Login logic check
        if (username != null && password != null) {
            // TODO: Get the user from the database
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setType(1);

            // TODO: Check if User matches with the record
            if (user != null) {
                request.getSession().setAttribute("user", user);

                // Redirect the user to the /app servlet
                response.sendRedirect(request.getContextPath() + "/app");
                return;
            }

            request.setAttribute("errorMessage", "Invalid username or password");
        }

        // Forward the request to the login.jsp file
        request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
    }
}
