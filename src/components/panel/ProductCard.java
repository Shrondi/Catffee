package components.panel;

import javax.swing.*;
import java.awt.*;

import components.button.RoundedButton;
import model.ProductData;
import controller.order.ProductOrderController;

/**
 * Componente visual para mostrar un producto en el pedido de Catffee.
 * Incluye imagen, nombre, cantidad, precio y botones para modificar la cantidad.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
/**
 * Panel visual para un producto en el carrito/pedido.
 */
public class ProductCard extends JPanel {
    private int quantity = 1;
    private final JLabel quantityLabel;
    private ProductData productData;

    public ProductCard(ProductData data, ProductOrderController controller, boolean readOnly) {
        this.productData = data;

        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(360, 70));
        setBackground(Color.decode("#F9F9F9"));
        setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        // Nombre y descripción
        JLabel name = new JLabel(data.getName());
        name.setFont(new Font("Sora", Font.BOLD, 14));

        JLabel desc = new JLabel(data.getDescription());
        desc.setFont(new Font("Sora", Font.PLAIN, 12));
        desc.setForeground(Color.GRAY);

        // Etiqueta de precio individual
        JLabel price = new JLabel(String.format("%.2f €", data.getPrice()));
        price.setFont(new Font("Sora", Font.PLAIN, 12));
        price.setForeground(new Color(80, 80, 80));

        JPanel infoPanel = new JPanel();
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        infoPanel.setOpaque(false);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(name);
        infoPanel.add(desc);
        infoPanel.add(price);

        // Imagen
        JLabel img = new JLabel(new ImageIcon(new ImageIcon(data.getImagePath()).getImage().getScaledInstance(54, 54, Image.SCALE_SMOOTH)));
        img.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

        // Contador
        RoundedButton minus = new RoundedButton("–", 20);
        quantityLabel = new JLabel(String.valueOf(quantity));
        quantityLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 14));
        RoundedButton plus = new RoundedButton("+", 20);

        minus.setBackground(Color.WHITE);
        plus.setBackground(Color.WHITE);

        minus.setBorderColor(Color.decode("#F9F2ED"));
        minus.setForeground(Color.decode("#A2A2A2"));
        plus.setBorderColor(Color.decode("#F9F2ED"));

        minus.setFont(new Font("Sora", Font.BOLD, 18));
        plus.setFont(new Font("Sora", Font.BOLD, 18));

        minus.addActionListener(_ -> controller.decrementProductFromOrder(data));
        plus.addActionListener(_ -> controller.addProductToOrder(data));

        JPanel counter = new JPanel();
        counter.setOpaque(false);

        if (readOnly) {
            // Mantener el espacio de los botones aunque no se vean
            int btnWidth = 40; // Tamaño estándar de los botones
            counter.add(Box.createHorizontalStrut(btnWidth));
            counter.add(quantityLabel);
            counter.add(Box.createHorizontalStrut(btnWidth));
        } else {
            counter.add(minus);
            counter.add(quantityLabel);
            counter.add(plus);
        }

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setOpaque(false);
        rightPanel.add(counter, BorderLayout.CENTER);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        add(img, BorderLayout.WEST);
        add(infoPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        quantityLabel.setText(String.valueOf(quantity));
    }

    public ProductData getProductData() {
        return productData;
    }

    public int getQuantity() {
        return quantity;
    }
}
