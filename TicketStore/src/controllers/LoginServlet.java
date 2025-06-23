package controllers;

import models.Cart;
import models.User;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user =  UserService.verifyUser(username, password);

        if (user != null) {
            HttpSession session = request.getSession();
            Cart cart = new Cart();
            cart.getItemsFromDB(user.getId());

            session.setAttribute("user", user);
            response.sendRedirect("home");
        } else {
            String error = "Invalid username or password";
            response.sendRedirect("login.jsp?error=" + error);
        }

    }
}
