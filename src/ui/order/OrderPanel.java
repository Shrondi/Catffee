package ui.order;

import components.button.RoundedButton;
import components.panel.ProductCard;
import model.ProductData;
import javax.swing.*;
import java.awt.*;
import controller.order.ProductOrderController;
import utils.I18n;
import components.bar.TopBar;
import ui.order.OrderListener;

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
    private final String userName;

    private final JPanel emptyCart = buildEmptyOrderPanel();
    private final JScrollPane productPanel = scrollableItemPanel();
    private final JPanel southPanel = new JPanel();
    private JPanel pricePanel = footerPanel();
    private RoundedButton pedirBtn;

    private final OrderListener orderListener;

    public OrderPanel(ProductOrderController controller, String userName) {
        this.controller = controller;
        this.userName = userName;
        setBackground(Color.decode("#F9F9F9"));
        setLayout(new BorderLayout());
        add(buildTopBar(), BorderLayout.NORTH);

        // Cuando la clase se crea el carrito esta vacio:
        if (controller.getCartItems().isEmpty()) {
            add(emptyCart, BorderLayout.CENTER);
        } else {
            add(productPanel, BorderLayout.CENTER);
            southPanel.removeAll();
            southPanel.add(pricePanel);
            add(southPanel, BorderLayout.SOUTH);
            refreshCartView();
        }

        // Aunque al principio no se muestre, añadimos ya el panel hijo del precio al padre
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        southPanel.setOpaque(false);
        southPanel.add(pricePanel);
        
        // Escuchar cambios en el carrito para actualizar la UI
        controller.addCartListener(this::refreshCartView);

        // Asignar el listener al botón pedir después de inicializar controller y pedirBtn
        pedirBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        orderListener = new OrderListener(this, controller);
        pedirBtn.addActionListener(orderListener);
    }

    private JPanel buildTopBar() {
        return new TopBar(I18n.getTranslation("order_title"));
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

        JLabel totalText = new JLabel(I18n.getTranslation("order_total"));
        totalText.setFont(new Font("Sora SemiBold", Font.PLAIN, 20));
        totalLabel = new JLabel("0.00 €");
        totalLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 15));
        totalRow.add(totalText, BorderLayout.WEST);
        totalRow.add(totalLabel, BorderLayout.EAST);
        totalRow.setBorder(BorderFactory.createEmptyBorder(10, 0, 60, 0));

        pedirBtn = new RoundedButton(I18n.getTranslation("order_order"), 25);
        pedirBtn.setMaximumSize(new Dimension(330, 56));
        pedirBtn.setBackground(new Color(193, 124, 77));
        pedirBtn.setForeground(Color.WHITE);
        pedirBtn.setFont(new Font("Sora SemiBold", Font.PLAIN, 16));
        pedirBtn.setFocusPainted(false);
        pedirBtn.setBorder(BorderFactory.createEmptyBorder(16, 140, 16, 140));
        pedirBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        pedirBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Eliminar listeners antiguos antes de añadir uno nuevo
        for (var listener : pedirBtn.getActionListeners()) {
            pedirBtn.removeActionListener(listener);
        }

        pedirBtn.addActionListener(orderListener);

        footer.add(sep);
        footer.add(totalRow);
        footer.add(pedirBtn);

        return footer;
    }

    private JPanel confirmationFooterPanel() {
        JPanel footer = new JPanel();
        footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));
        footer.setBackground(Color.decode("#F9F9F9"));
        footer.setBorder(BorderFactory.createEmptyBorder(10, 30, 120, 30));

        JSeparator sep = new JSeparator();
        sep.setForeground(Color.decode("#E3E3E3"));
        sep.setMaximumSize(new Dimension(295, 1));
        footer.add(sep);

        JPanel totalRow = new JPanel(new BorderLayout());
        totalRow.setOpaque(false);
        JLabel totalText = new JLabel(I18n.getTranslation("order_total"));
        totalText.setFont(new Font("Sora SemiBold", Font.PLAIN, 20));
        JLabel totalValue = new JLabel(String.format("%.2f €", controller.getTotal()));
        totalValue.setFont(new Font("Sora SemiBold", Font.PLAIN, 15));
        totalRow.add(totalText, BorderLayout.WEST);
        totalRow.add(totalValue, BorderLayout.EAST);
        totalRow.setBorder(BorderFactory.createEmptyBorder(10, 0, 60, 0));
        footer.add(totalRow);

        JLabel thanks = new JLabel("<html><div style='width:260px; text-align:center;'>¡Miau-chas gracias por tu pedido, <br>"+ userName + " ☕!</div></html>");
        thanks.setFont(new Font("Sora Light", Font.PLAIN, 16));
        thanks.setAlignmentX(Component.CENTER_ALIGNMENT);
        thanks.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        footer.add(thanks);

        JLabel waitMsg = new JLabel("<html><div style='width:260px; text-align:center;'>Mientras esperas tómate un momento para conocer a nuestros adorables gatitos.</div></html>");
        waitMsg.setFont(new Font("Sora Light", Font.PLAIN, 16));
        waitMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        waitMsg.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        footer.add(waitMsg);

        return footer;
    }

    public void showOrderConfirmationFooter() {
        southPanel.removeAll();
        pricePanel = confirmationFooterPanel();
        southPanel.add(pricePanel);
        southPanel.revalidate();
        southPanel.repaint();

        // Generar número de pedido en el controller
        controller.generarNumeroPedido();

        // Regenerar los productos en modo readOnly
        itemsPanel.removeAll();
        for (var item : controller.getCartItems()) {
            ProductCard readOnlyCard = new ProductCard(item.data, null, true);
            readOnlyCard.setQuantity(item.quantity);
            readOnlyCard.setBackground(getBackground());
            itemsPanel.add(readOnlyCard);
            itemsPanel.add(Box.createVerticalStrut(10));
        }
        itemsPanel.revalidate();
        itemsPanel.repaint();

        // Añadir título y número fuera del scroll
        Container parent = productPanel.getParent();
        if (parent != null) {
            parent.remove(productPanel); // Eliminar el scroll para poner antes el título y número
        }
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        
        JLabel title = new JLabel("¡Pedido confirmado!");
        title.setFont(new Font("Sora SemiBold", Font.PLAIN, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JLabel number = new JLabel("#" + controller.getNumeroPedido());
        number.setFont(new Font("Sora Regular", Font.PLAIN, 16));
        number.setForeground(Color.GRAY);
        number.setAlignmentX(Component.CENTER_ALIGNMENT);
        number.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        centerPanel.add(title);
        centerPanel.add(number);
        centerPanel.add(productPanel); // Añadir el scroll después del título y número

        add(centerPanel, BorderLayout.CENTER);

        revalidate();
        repaint();

    }

    private JPanel buildEmptyOrderPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // Espaciador arriba
        contentPanel.add(Box.createVerticalStrut(150));

        // Icono carrito
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("images/ui/cart.png"));
        Image scaledImage = icon.getImage().getScaledInstance(113, 108, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(scaledImage));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconLabel.setBorder(BorderFactory.createEmptyBorder(100, 138, 0, 160));
        contentPanel.add(iconLabel);

        // Espaciador
        contentPanel.add(Box.createVerticalStrut(38));

        // Texto principal
        JLabel mainText = new JLabel(I18n.getTranslation("order_empty_title"));
        mainText.setFont(new Font("Poppins SemiBold", Font.PLAIN, 28));
        mainText.setForeground(Color.decode("#000000"));
        mainText.setBorder(BorderFactory.createEmptyBorder(0, 90, 10, 85));
        mainText.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(mainText);

        // Texto secundario
        JLabel secondaryText = new JLabel("<html><div style='text-align: center;'>" + I18n.getTranslation("order_empty_desc") + "</div></html>");
        secondaryText.setFont(new Font("Poppins Regular", Font.PLAIN, 17));
        secondaryText.setBorder(BorderFactory.createEmptyBorder(10, 90, 10, 88));
        secondaryText.setForeground(Color.decode("#000000"));
        secondaryText.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(secondaryText);

        return contentPanel;
    }

    // --- Lógica visual: solo delega en el modelo y actualiza la UI ---
    public void addProductCard(ProductData product) {
        try {
            controller.addProductToOrder(product);
            refreshCartView();
        } catch (Exception e) {
            utils.Error.mostrarErrorCritico("Error crítico al añadir producto al carrito");
            e.printStackTrace();
        }
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
                ProductCard card = new ProductCard(item.data, controller, false);
                card.setQuantity(item.quantity);
                itemsPanel.add(card);
                itemsPanel.add(Box.createVerticalStrut(10));
            }
            updateTotalLabel();
        }
        revalidate();
        repaint();
    }

    /**
     * Restaura el panel al estado vacío tras un pedido confirmado, para cuando se vuelve a la vista de pedido.
     */
    public void resetToEmpty() {
        removeAll();

        southPanel.removeAll();
        pricePanel = footerPanel();
        southPanel.add(pricePanel);

        southPanel.revalidate();
        southPanel.repaint();

        add(buildTopBar(), BorderLayout.NORTH);
        add(emptyCart, BorderLayout.CENTER);
        controller.resetPedidoConfirmado();
        controller.clearCart();

        revalidate();
        repaint();
    }

    public boolean isPedidoConfirmado() {
        return controller.isPedidoConfirmado();
    }
}
