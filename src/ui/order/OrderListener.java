package ui.order;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderListener implements ActionListener {
    private final OrderPanel orderPanel;

    public OrderListener(OrderPanel orderPanel) {
        this.orderPanel = orderPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        orderPanel.showOrderConfirmationFooter();
    }
} 