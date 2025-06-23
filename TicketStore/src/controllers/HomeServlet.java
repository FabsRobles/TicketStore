package controllers;

import models.Product;
import models.User;
import services.ProductsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            request.setAttribute("userName", user.getUsername());
            long loginTime = session.getCreationTime();
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - loginTime;
            
            request.setAttribute("loginTime", elapsedTime / 1000);

            List<Product> products = ProductsService.getProducts();
            request.setAttribute("products", products);
        }
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
