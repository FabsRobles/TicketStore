package models;

import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private int userId;
    private List<OrderItem> orderItems;
    private Date orderDate;
    private String address;
    private String methodPayment;
    private double total;
    // Constructor
    public Order() {}

    public Order(int id, int userId, Date orderDate, String address, String methodPayment) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.address = address;
        this.methodPayment = methodPayment;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMethodPayment() {
        return methodPayment;
    }

    public void setMethodPayment(String methodPayment) {
        this.methodPayment = methodPayment;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}