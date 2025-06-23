package models;

import java.util.HashMap;
import java.util.Map;

import services.CartService;
import services.ProductsService;
import models.Product;

public class Cart {
    private Map<Product, Integer> items;

    public Cart() {
        items = new HashMap<>();
    }

    public void addItem(int userId ,Product product, int quantity) {
        Product dbProduct = ProductsService.getProductById(product.getId());
        if (dbProduct.getStock() < quantity) {
            return;
        }

        CartService.addItem(userId, product.getId(), quantity);
        ProductsService.updateProductStock(product.getId(), dbProduct.getStock() - quantity);
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public void removeItem(int userId, Product product) {
        CartService.removeItem(userId,product.getId());
        System.out.println(product.getStock() + items.get(product));
        ProductsService.updateProductStock(product.getId(), product.getStock() + items.get(product));
        items.remove(product);
    }

    public void updateItem(int userId, Product product, int quantity) {
        Product dbProduct = ProductsService.getProductById(product.getId());
        if (dbProduct.getStock() < quantity) {
            return;
        }

        if (quantity <= 0) {
            removeItem(userId, product);
        } else {
            int currentQuantity = items.get(product);
            if (currentQuantity < quantity) {
                int diff = quantity - currentQuantity;
                CartService.updateItem(userId, product.getId(), quantity);
                ProductsService.updateProductStock(product.getId(), diff);
                items.put(product, quantity);
            } else {
                int diff = currentQuantity - quantity;
                CartService.updateItem(userId, product.getId(), quantity);
                ProductsService.updateProductStock(product.getId(), diff);
                items.put(product, quantity);
            }
        }
    }

    public void getItemsFromDB(int userId) {
        this.items = CartService.getItems(userId);
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public double getTotalPrice() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }
}
