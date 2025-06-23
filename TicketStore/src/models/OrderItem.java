package models;

public class OrderItem {
    private int orderId;
    private int productId;
    private int quantity;
    private double price;
    private Product product;

    // Constructors
    public OrderItem() {}

    public OrderItem(int orderId, int productId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public OrderItem(int orderId, int productId, int quantity, Product product) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.product = product;
    }

    public OrderItem(int orderId, int productId, int quantity, Product product, double price) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.product = product;
        this.price = price;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
