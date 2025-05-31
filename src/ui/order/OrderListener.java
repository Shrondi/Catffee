package ui.order;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.order.ProductOrderController;

public class OrderListener implements ActionListener {
    private final OrderPanel orderPanel;
    private final ProductOrderController controller;

    public OrderListener(OrderPanel orderPanel, ProductOrderController controller) {
        this.orderPanel = orderPanel;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        orderPanel.showOrderConfirmationFooter();
        controller.confirmOrder();
    }
} 