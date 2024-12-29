// RegisterServlet.java
package com.example.servlet;

import com.example.dao.UserDAO;
import com.example.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String department = request.getParameter("department");
        String role = "EMPLOYEE"; // Default role

        User user = new User(0, username, password, role, department);
        try {
            userDAO.registerUser(user);
            response.sendRedirect("login.jsp");
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Error registering user", e);
        }
    }
}