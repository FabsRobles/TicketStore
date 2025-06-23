package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import models.Cart;
import models.Product;
import models.Order;
import utils.DBConnection;

public class OrderService {
    public static Order createOrder(Cart cart, int userId, String address, String payment) {
        Date orderDate = new java.sql.Date(new java.util.Date().getTime());
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO orders (user_id, order_date, address, method_payment) VALUES (?, ?, ?, ?)", new String[]{"id"});
            ps.setInt(1, userId);
            ps.setDate(2, orderDate);
            ps.setString(3, address);
            ps.setString(4, payment);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                int orderId = rs.getInt(1);
                for (Product product : cart.getItems().keySet()) {
                    OrderItemService.createOrderItem(orderId, product.getId(), cart.getItems().get(product));
                }
                return new Order(orderId, userId, orderDate, address, payment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Order> getOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM orders WHERE user_id = ?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("user_id"));
                order.setOrderDate(rs.getDate("order_date"));
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    public static List<Order> getOrdersWithProductsByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM orders WHERE user_id = ? ORDER BY id DESC");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setUserId(rs.getInt("user_id"));
                order.setOrderDate(rs.getDate("order_date"));
                order.setOrderItems(OrderItemService.getOrderItemsWithProductsByOrderId(order.getId()));
                double total =  order.getOrderItems().stream().mapToDouble(orderItem -> orderItem.getProduct().getPrice() * orderItem.getQuantity()).sum();
                order.setTotal(total);
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
}
