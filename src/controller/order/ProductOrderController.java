package controller.order;

import model.OrderCart;
import model.ProductData;
import java.util.List;
import java.util.ArrayList;

public class ProductOrderController {
    private final OrderCart cart = OrderCart.getInstance();
    private final List<Runnable> listeners = new ArrayList<>();

    public ProductOrderController() {}

    public void addProductToOrder(ProductData product) {
        cart.addProduct(product);
        notifyListeners();
    }

    public void decrementProductFromOrder(ProductData product) {
        cart.decrementProduct(product);
        notifyListeners();
    }

    public List<OrderCart.CartItem> getCartItems() {
        return new ArrayList<>(cart.getItems().values());
    }

    public double getTotal() {
        return cart.getTotal();
    }

    public void addCartListener(Runnable r) {
        listeners.add(r);
    }

    public void removeCartListener(Runnable r) {
        listeners.remove(r);
    }

    private void notifyListeners() {
        for (Runnable r : listeners) r.run();
    }
}
