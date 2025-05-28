package components.panel;

import javax.swing.*;
import java.awt.*;

import model.ProductData;
import controller.ProductOrderController;

public class ProductCard extends JPanel {
    private int quantity = 1;
    private final JLabel quantityLabel;

    public ProductCard(ProductData data, ProductOrderController controller) {

        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(360, 70));
        setBackground(Color.decode("#F9F9F9"));

        // Nombre y descripción
        JLabel name = new JLabel(data.getName());
        name.setFont(new Font("Sora", Font.BOLD, 14));

        JLabel desc = new JLabel(data.getDescription());
        desc.setFont(new Font("Sora", Font.PLAIN, 12));
        desc.setForeground(Color.GRAY);

        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(false);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(name);
        infoPanel.add(desc);

        // Imagen
        JLabel img = new JLabel(new ImageIcon(new ImageIcon(data.getImagePath()).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));

        // Contador
        JButton minus = new JButton("–");
        quantityLabel = new JLabel(String.valueOf(quantity));
        JButton plus = new JButton("+");

        minus.addActionListener(_ -> controller.decrementProductFromOrder(data));
        plus.addActionListener(_ -> controller.addProductToOrder(data));

        JPanel counter = new JPanel();
        counter.setOpaque(false);
        counter.add(minus);
        counter.add(quantityLabel);
        counter.add(plus);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setOpaque(false);
        rightPanel.add(counter, BorderLayout.CENTER);

        add(img, BorderLayout.WEST);
        add(infoPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        quantityLabel.setText(String.valueOf(quantity));
    }

}
