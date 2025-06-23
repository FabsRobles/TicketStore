package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import models.OrderItem;
import models.Product;
import services.ProductsService;
import utils.DBConnection;

public class OrderItemService {
    public static OrderItem createOrderItem(int orderId, int productId, int quantity) {
         try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ? ,?)");
            ps.setInt(1, orderId);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);
            ps.setDouble(4, ProductsService.getProductById(productId).getPrice());
            ps.executeUpdate();

            return new OrderItem(orderId, productId, quantity); 
         }
         catch(Exception e) {
             e.printStackTrace();
         }  
         return null;
    }

    public static List<OrderItem> getOrderItemsWithProductsByOrderId(int orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM order_items WHERE order_id = ?");
            ps.setInt(1, orderId);
            var rs = ps.executeQuery();
            while (rs.next()) {
                Product product = ProductsService.getProductById(rs.getInt("product_id"));
                OrderItem orderItem = new OrderItem(rs.getInt("order_id"), rs.getInt("product_id"), rs.getInt("quantity"), product, rs.getDouble("price"));
                orderItems.add(orderItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderItems;
    }
}
