package controllers;

import models.Cart;
import models.Product;
import models.User;
import utils.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        
        session.setAttribute("cart", cart);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            cart.getItemsFromDB(user.getId());
        }
     
        String url = request.getHeader("referer");
        String action = request.getParameter("action");
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        System.out.println("CartServlet " + action);
        System.out.println("CartServlet " + productId);
        
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM products WHERE id = ?");
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));

                if ("add".equals(action)) {
                    cart.addItem(user.getId(),product, quantity);
                } else if ("update".equals(action)) {
                    cart.updateItem(user.getId(),product, quantity);
                } else if ("remove".equals(action)) {
                    cart.removeItem(user.getId(),product);
                }
            }
            session.setAttribute("cart", cart);
            System.out.println("CartServlet " + cart.getItems());
            System.out.println("CartServlet " + url);
            if(url.equals("/Carrito/cart")) {
                response.sendRedirect("cart.jsp");
            } else {
                response.sendRedirect(url);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("cart?error=true");
        }
    }
}
