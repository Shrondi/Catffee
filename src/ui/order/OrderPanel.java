package ui.order;

import components.button.RoundedButton;
import components.panel.ProductCard;
import model.ProductData;
import javax.swing.*;
import java.awt.*;
import controller.order.ProductOrderController;
import utils.I18n;

/**
 * Panel de pedido para Catffee. Muestra los productos añadidos al carrito y permite modificar cantidades y finalizar el pedido.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
/**
 * Panel que gestiona la visualización y edición del pedido actual.
 */
public class OrderPanel extends JPanel {
    private JPanel itemsPanel = new JPanel();
    private JLabel totalLabel;
    private final ProductOrderController controller;

    private final JPanel emptyCart = buildEmptyOrderPanel();
    private final JScrollPane productPanel = scrollableItemPanel();
    private final JPanel southPanel = new JPanel();
    private final JPanel pricePanel = footerPanel();

    public OrderPanel(ProductOrderController controller) {
        this.controller = controller;
        setBackground(Color.decode("#F9F9F9"));
        setLayout(new BorderLayout());
        add(buildTopBar(), BorderLayout.NORTH);

        // Cuando la clase se crea el carrito esta vacio:
        add(emptyCart, BorderLayout.CENTER);

        // Aunque al principio no se muestre, añadimos ya el panel hijo del precio al padre
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        southPanel.setOpaque(false);
        southPanel.add(pricePanel);
        
        // Escuchar cambios en el carrito para actualizar la UI
        controller.addCartListener(this::refreshCartView);
    }

    private JPanel buildTopBar() {
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.decode("#252424"));
        topPanel.setPreferredSize(new Dimension(413, 85));
        topPanel.setMaximumSize(new Dimension(413, 85));
        topPanel.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel(I18n.t("order_title"));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Sora Semibold", Font.PLAIN, 30));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(25, 39, 24, 250));
        topPanel.add(titleLabel, BorderLayout.CENTER);
        return topPanel;
    }

    private JScrollPane scrollableItemPanel() {
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setOpaque(false);
        itemsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));

        return scrollPane;
    }

    private JPanel footerPanel() {
        JPanel footer = new JPanel();
        footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));
        footer.setBackground(Color.decode("#F9F9F9"));
        footer.setBorder(BorderFactory.createEmptyBorder(10, 30, 120, 30));

        JSeparator sep = new JSeparator();
        sep.setForeground(Color.decode("#E3E3E3"));
        sep.setMaximumSize(new Dimension(295, 1));

        JPanel totalRow = new JPanel(new BorderLayout());
        totalRow.setOpaque(false);

        JLabel totalText = new JLabel(I18n.t("order_total"));
        totalText.setFont(new Font("Sora SemiBold", Font.PLAIN, 20));
        totalLabel = new JLabel("0.00 €");
        totalLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 15));
        totalRow.add(totalText, BorderLayout.WEST);
        totalRow.add(totalLabel, BorderLayout.EAST);
        totalRow.setBorder(BorderFactory.createEmptyBorder(10, 0, 60, 0));

        RoundedButton pedirBtn = new RoundedButton(I18n.t("order_order"), 25);
        pedirBtn.setMaximumSize(new Dimension(327, 56));
        pedirBtn.setBackground(new Color(193, 124, 77));
        pedirBtn.setForeground(Color.WHITE);
        pedirBtn.setFont(new Font("Sora SemiBold", Font.PLAIN, 16));
        pedirBtn.setFocusPainted(false);
        pedirBtn.setBorder(BorderFactory.createEmptyBorder(16, 140, 16, 140));
        pedirBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        pedirBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        footer.add(sep);
        footer.add(totalRow);
        footer.add(pedirBtn);

        return footer;
    }

     private JPanel buildEmptyOrderPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // Espaciador arriba
        contentPanel.add(Box.createVerticalStrut(150));

        // Icono carrito
        ImageIcon icon = new ImageIcon("resources/images/ui/cart.png");
        Image scaledImage = icon.getImage().getScaledInstance(113, 108, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(scaledImage));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconLabel.setBorder(BorderFactory.createEmptyBorder(100, 138, 0, 160));
        contentPanel.add(iconLabel);

        // Espaciador
        contentPanel.add(Box.createVerticalStrut(38));

        // Texto principal
        JLabel mainText = new JLabel(I18n.t("order_empty_title"));
        mainText.setFont(new Font("Poppins SemiBold", Font.PLAIN, 28));
        mainText.setForeground(Color.decode("#000000"));
        mainText.setBorder(BorderFactory.createEmptyBorder(0, 90, 10, 85));
        mainText.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(mainText);

        // Texto secundario
        JLabel secondaryText = new JLabel("<html><div style='text-align: center;'>" + I18n.t("order_empty_desc") + "</div></html>");
        secondaryText.setFont(new Font("Poppins Regular", Font.PLAIN, 17));
        secondaryText.setBorder(BorderFactory.createEmptyBorder(10, 90, 10, 88));
        secondaryText.setForeground(Color.decode("#000000"));
        secondaryText.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(secondaryText);

        return contentPanel;
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
        if (controller.getCartItems().isEmpty()) {
            remove(productPanel);
            remove(southPanel);
            add(emptyCart, BorderLayout.CENTER);
        } else {
            remove(emptyCart);
            add(productPanel, BorderLayout.CENTER);
            add(southPanel, BorderLayout.SOUTH);
            itemsPanel.removeAll();
            for (var item : controller.getCartItems()) {
                ProductCard card = new ProductCard(item.data, controller);
                card.setQuantity(item.quantity);
                itemsPanel.add(card);
                itemsPanel.add(Box.createVerticalStrut(10));
            }
            updateTotalLabel();
        }
        revalidate();
        repaint();
    }
}
