package model.order;

import java.util.HashMap;
import java.util.Map;

import model.product.ProductData;

/**
 * Carrito de pedidos para Catffee. Permite añadir, quitar y consultar productos del pedido actual.
 * Implementa el patrón Singleton.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
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

    /**
     * Añade un producto al carrito.
     * @param data Producto a añadir
     */
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

    /**
     * Decrementa la cantidad de un producto en el carrito.
     * @param data Producto a decrementar
     */
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

    /**
     * Obtiene el total del carrito.
     * @return Total en euros
     */
    public double getTotal() {
        return total;
    }

    /**
     * Obtiene los items del carrito.
     * @return Mapa de items
     */
    public Map<String, CartItem> getItems() {
        return items;
    }

    /**
     * Representa un ítem del carrito.
     */
    public static class CartItem {
        public ProductData data;
        public int quantity;
        public CartItem(ProductData data, int quantity) {
            this.data = data;
            this.quantity = quantity;
        }
    }

    public void clear() {
        items.clear();
        total = 0.0;
    }
}
