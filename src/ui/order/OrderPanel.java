package ui.order;

import components.panel.ProductCard;
import model.ProductData;
import javax.swing.*;
import java.awt.*;
import controller.ProductOrderController;

public class OrderPanel extends JPanel {
    private JPanel itemsPanel;
    private JLabel totalLabel;
    private final ProductOrderController controller;

    public OrderPanel(ProductOrderController controller) {
        this.controller = controller;

        setLayout(new BorderLayout());
        add(buildTopBar(), BorderLayout.NORTH);
        add(scrollableItemPanel(), BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        southPanel.setOpaque(false);
        southPanel.add(footerPanel());

        add(southPanel, BorderLayout.SOUTH);
        
        // Escuchar cambios en el carrito para actualizar la UI
        controller.addCartListener(this::refreshCartView);
    }

    private JPanel buildTopBar() {
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.decode("#252424"));
        topPanel.setPreferredSize(new Dimension(413, 85));
        topPanel.setMaximumSize(new Dimension(413, 85));
        topPanel.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Pedido");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Sora Semibold", Font.PLAIN, 30));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(25, 39, 24, 250));
        topPanel.add(titleLabel, BorderLayout.CENTER);
        return topPanel;
    }

    private JScrollPane scrollableItemPanel() {
        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setOpaque(false);
        itemsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
        return scrollPane;
    }

    private JPanel footerPanel() {
        JPanel footer = new JPanel();
        footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));
        footer.setBackground(Color.WHITE);
        footer.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));
        JSeparator sep = new JSeparator();
        sep.setForeground(Color.LIGHT_GRAY);
        JPanel totalRow = new JPanel(new BorderLayout());
        totalRow.setOpaque(false);
        JLabel totalText = new JLabel("Total");
        totalText.setFont(new Font("Sora SemiBold", Font.PLAIN, 18));
        totalLabel = new JLabel("0.00 €");
        totalLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 18));
        totalRow.add(totalText, BorderLayout.WEST);
        totalRow.add(totalLabel, BorderLayout.EAST);
        totalRow.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        JButton pedirBtn = new JButton("Pedir");
        pedirBtn.setBackground(new Color(193, 124, 77));
        pedirBtn.setForeground(Color.WHITE);
        pedirBtn.setFont(new Font("Sora SemiBold", Font.PLAIN, 16));
        pedirBtn.setFocusPainted(false);
        pedirBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        pedirBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        pedirBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        pedirBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        footer.add(sep);
        footer.add(totalRow);
        footer.add(pedirBtn);
        return footer;
    }

    // --- Lógica visual: solo delega en el modelo y actualiza la UI ---
    public void addProductCard(ProductData data) {
        controller.addProductToOrder(data);
        refreshCartView();
    }

    private void updateTotalLabel() {
        totalLabel.setText(String.format("%.2f €", controller.getTotal()));
    }

    private void refreshCartView() {
        itemsPanel.removeAll();
        for (ProductCard card : controller.getProductCards()) {
            itemsPanel.add(card);
            itemsPanel.add(Box.createVerticalStrut(10));
        }
        updateTotalLabel();
        revalidate();
        repaint();
    }
}
