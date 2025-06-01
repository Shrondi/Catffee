package controller.order;

import model.order.OrderCart;
import model.product.Product;

import java.util.List;
import java.util.ArrayList;

/**
 * Controlador para la lógica del pedido de productos en Catffee.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
public class ProductOrderController {
    private final OrderCart cart = OrderCart.getInstance();
    private final List<Runnable> listeners = new ArrayList<>();
    private boolean pedidoConfirmado = false;
    private int numeroPedido = -1;

    public ProductOrderController() {}

    public void addProductToOrder(Product product) {
        cart.addProduct(product);
        notifyListeners();
    }

    public void decrementProductFromOrder(Product product) {
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

    public void marcarPedidoConfirmado() {
        pedidoConfirmado = true;
    }

    public void resetPedidoConfirmado() {
        pedidoConfirmado = false;
    }

    public boolean isPedidoConfirmado() {
        return pedidoConfirmado;
    }

    public void generarNumeroPedido() {
        numeroPedido = (int)(Math.random() * 900000) + 100000; // 6 dígitos
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void resetNumeroPedido() {
        numeroPedido = -1;
    }

    public void clearCart() {
        cart.clear();
        notifyListeners();
    }
}
