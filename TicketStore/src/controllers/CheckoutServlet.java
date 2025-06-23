package controllers;

import models.Cart;
import models.User;
import models.Order;
import services.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;


public class CheckoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if(cart == null || cart.getItems().isEmpty()) {
            response.sendRedirect("cart");
            return;
        }

        request.getRequestDispatcher("checkout.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("cart");
        String payment = request.getParameter("payment");
        String address = request.getParameter("address");

        if( payment == null || address == null || payment.isEmpty() || address.isEmpty()){
            response.sendRedirect("checkout.jsp?error=true");
            return;
        }

        if (user == null || cart == null || cart.getItems().isEmpty()) {
            response.sendRedirect("cart.jsp");
            return;
        }

        Order order = OrderService.createOrder(cart, user.getId(), address, payment);

        if(order != null) {
            session.removeAttribute("cart");
            response.sendRedirect("order-history");
        } else {
            response.sendRedirect("checkout.jsp?error=true");
        }
    }
}
