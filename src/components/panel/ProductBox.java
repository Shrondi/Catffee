package components.panel;

import components.button.RoundedButton;

import javax.swing.*;
import java.awt.*;

public class ProductBox extends RoundedPanel {

    private static final int WIDTH = 160;
    private static final int HEIGHT = 240;

    public ProductBox(String title, String subtitle, String price, String imagePath) {
        super(16); // radio de los bordes redondeados

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

    private JLabel createImageLabel(String path) {
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
        JLabel priceLabel = new JLabel(Double.toString(price)+"€");
        priceLabel.setFont(new Font("Poppins", Font.BOLD, 18));
        priceLabel.setForeground(Color.BLACK);

        RoundedButton plusBtn = createPlusButton();

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 0));
        bottom.setOpaque(false);
        bottom.setBorder(BorderFactory.createEmptyBorder());
        bottom.add(priceLabel);
        bottom.add(plusBtn);

        return bottom;
    }

    private RoundedButton createPlusButton() {
        RoundedButton button = new RoundedButton("+", 8);
        button.setFont(new Font("Poppins", Font.BOLD, 15));
        button.setBackground(new Color(0xC67C4E));
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorderPainted(false);
        return button;
    }
}
