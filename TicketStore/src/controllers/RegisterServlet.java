package controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import services.UserService;

import java.io.IOException;


public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        User user = UserService.createUser(username, password, email);

        if (user != null) {
            response.sendRedirect("login.jsp");
        } else {
            String error = "Email already in use or invalid input";
            response.sendRedirect("register.jsp?error=" + error);
        }
    }
}
