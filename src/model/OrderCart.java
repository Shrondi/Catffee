package model;

import java.util.HashMap;
import java.util.Map;

public class OrderCart {
    private static OrderCart instance;
    private final Map<String, CartItem> items = new HashMap<>();
    private double total = 0.0;

    private OrderCart() {}

    public static OrderCart getInstance() {
        if (instance == null) {
            instance = new OrderCart();
        }
        return instance;
    }

    public void addProduct(ProductData data) {
        String key = data.getName();
        if (items.containsKey(key)) {
            CartItem item = items.get(key);
            item.quantity++;
            total += data.getPrice();
        } else {
            CartItem item = new CartItem(data, 1);
            items.put(key, item);
            total += data.getPrice();
        }
    }

    public void decrementProduct(ProductData data) {
        String key = data.getName();
        if (items.containsKey(key)) {
            CartItem item = items.get(key);
            if (item.quantity > 1) {
                item.quantity--;
                total -= data.getPrice();
            } else {
                items.remove(key);
                total -= data.getPrice();
            }
        }
    }

    public double getTotal() {
        return total;
    }

    public Map<String, CartItem> getItems() {
        return items;
    }


    public static class CartItem {
        public ProductData data;
        public int quantity;
        public CartItem(ProductData data, int quantity) {
            this.data = data;
            this.quantity = quantity;
        }
    }
}
