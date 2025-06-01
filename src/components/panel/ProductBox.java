package components.panel;

import components.button.RoundedButton;
import model.ProductData;
import components.popup.PopupMessage;
import java.awt.Window;
import utils.I18n;
import java.net.URL;
import javax.swing.*;
import java.awt.*;

/**
 * Componente visual para mostrar un producto en la carta de Catffee.
 * Incluye imagen, nombre, descripción, precio y botón para añadir al pedido.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
/**
 * Panel visual para un producto de la carta.
 */
public class ProductBox extends RoundedPanel {

    private static final int WIDTH = 160;
    private static final int HEIGHT = 240;

    private final Runnable onAddCallback;
    private RoundedButton plusBtn;

    public ProductBox(ProductData product, Runnable onAddCallback) {
        super(16); // radio de los bordes redondeados

        this.onAddCallback = onAddCallback;

        setupLayout();
        add(Box.createVerticalStrut(10));
        add(createImageLabel(product.getImagePath()));
        add(Box.createVerticalStrut(5));
        add(createTextLabel(product.getName(), "Sora SemiBold", 16, Color.BLACK));
        add(Box.createVerticalStrut(5));
        add(createTextLabel(product.getDescription(), "Sora Regular", 12, Color.GRAY));
        add(Box.createVerticalStrut(5));
        add(createBottomPanel(product.getPrice()));
    }

    private void setupLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder());
    }

    private JLabel createImageLabel(URL path) {
        ImageIcon icon = new ImageIcon(path);
        Image scaled = icon.getImage().getScaledInstance(140, 130, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(scaled));
        label.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 22));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JLabel createTextLabel(String text, String fontName, int size, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(fontName, Font.PLAIN, size));
        label.setForeground(color);
        label.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 22));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JPanel createBottomPanel(double price) {
        JLabel priceLabel = new JLabel(String.format("%.2f €", price));
        priceLabel.setFont(new Font("Poppins", Font.BOLD, 18));
        priceLabel.setForeground(Color.BLACK);

        plusBtn = createPlusButton();

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 0));
        bottom.setOpaque(false);
        bottom.setBorder(BorderFactory.createEmptyBorder());
        bottom.add(priceLabel);
        bottom.add(plusBtn);

        return bottom;
    }

    private RoundedButton createPlusButton() {
        RoundedButton button = new RoundedButton("+", 8);
        button.addActionListener(_ -> {
            if (onAddCallback != null) onAddCallback.run();
            Window parentWindow = SwingUtilities.getWindowAncestor(this);
            PopupMessage.show(parentWindow, I18n.getTranslation("product_added_popup"), 1200);
        });
        button.setFont(new Font("Poppins", Font.BOLD, 15));
        button.setBackground(new Color(0xC67C4E));
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorderPainted(false);
        return button;
    }

    public void setAddEnabled(boolean enabled) {
        if (plusBtn != null) {
            plusBtn.setEnabled(enabled);
            plusBtn.setVisible(enabled);
        }
    }
}
