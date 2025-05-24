package ui.home;

import javax.swing.*;

import components.panel.RoundedPanel;
import ui.BaseFrame;
import components.bar.NavigationBar;
import components.button.RoundedButton;

import java.awt.*;

public class HomeFrame extends BaseFrame {

    public HomeFrame(String title) {
        super(title);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(0xF7F7F7));

        mainPanel.add(topSection());
        mainPanel.add(productScroll());
        mainPanel.add(new NavigationBar("Inicio"));

        add(mainPanel);
    }

    private JPanel topSection() {
        JPanel container = new JPanel();
        container.setLayout(new OverlayLayout(container));
        container.setPreferredSize(new Dimension(412, 330));
        container.setMaximumSize(new Dimension(412, 330));

        JLabel imageLabel = headerImage();
        JPanel textPanel = headerText();

        container.add(textPanel);
        container.add(imageLabel);

        return container;
    }

    private JLabel headerImage() {
        ImageIcon icon = new ImageIcon("resources/images/cabecera_bienvenida.png");
        Image scaled = icon.getImage().getScaledInstance(412, 330, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaled));
        imageLabel.setAlignmentX(0.5f);
        imageLabel.setAlignmentY(0.5f);
        return imageLabel;
    }

    private JPanel headerText() {
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setOpaque(false);
        textPanel.setAlignmentX(0.5f);
        textPanel.setAlignmentY(0.5f);

        JLabel welcomeText = new JLabel("<html><div style='text-align: left;'>Bienvenido,<br><b>Nombre ☕</b></div></html>");
        welcomeText.setForeground(Color.WHITE);
        welcomeText.setFont(new Font("Segoe UI Emoji", Font.BOLD, 32));
        welcomeText.setHorizontalAlignment(SwingConstants.LEFT);
        welcomeText.setVerticalAlignment(SwingConstants.BOTTOM);
        welcomeText.setBorder(BorderFactory.createEmptyBorder(0, 40, 40, 0));

        textPanel.add(welcomeText, BorderLayout.CENTER);
        return textPanel;
    }

    private JScrollPane productScroll() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(0xF7F7F7));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel destacadosLabel = new JLabel("PRODUCTOS DESTACADOS");
        destacadosLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 20));
        destacadosLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        destacadosLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 16, 0));

        JPanel productGrid = productGrid();

        contentPanel.add(destacadosLabel);
        contentPanel.add(productGrid);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        return scrollPane;
    }

    private JPanel productGrid() {
        JPanel grid = new JPanel(new GridLayout(0, 2, 12, 12));
        grid.setBackground(new Color(0xF7F7F7));
        grid.setAlignmentX(Component.CENTER_ALIGNMENT);

        String[] names = {"Meowcha", "Catpuccino", "Gatogalletas", "Pawffins", "Empanacat", "Pink Paw"};
        String[] descriptions = {"Moka", "Deep Foam", "Crumble-cookies", "Muffin choco", "Carne o verdura", "Limonada frutos"};
        String[] prices = {"3.50€", "3.20€", "2.50€", "2.80€", "2.80€", "3.20€"};
        String[] images = {
            "resources/images/meowcha.png",
            "resources/images/catpuccino.png",
            "resources/images/gatogalletas.png",
            "resources/images/pawffins.png",
            "resources/images/empanacat.png",
            "resources/images/pink_paw.png"
        };

        for (int i = 0; i < names.length; i++) {
            grid.add(productBox(names[i], descriptions[i], prices[i], images[i]));
        }

        return grid;
    }

    private JPanel productBox(String title, String subtitle, String price, String imagePath) {
        RoundedPanel box = new RoundedPanel(16);
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setPreferredSize(new Dimension(160, 240));
        box.setMaximumSize(new Dimension(160, 240));
        box.setBackground(Color.WHITE);

        box.add(Box.createVerticalStrut(10));
        box.add(productImage(imagePath));
        box.add(Box.createVerticalStrut(5));
        box.add(createLabel(title, "Sora SemiBold", 16));
        box.add(Box.createVerticalStrut(5));
        box.add(createLabel(subtitle, "Sora Regular", 12, Color.GRAY));
        box.add(Box.createVerticalStrut(5));
        box.add(createPricePanel(price));

        return box;
    }

    private JLabel productImage(String path) {
        ImageIcon icon = new ImageIcon(path);
        Image scaled = icon.getImage().getScaledInstance(140, 130, Image.SCALE_SMOOTH);
        JLabel img = new JLabel(new ImageIcon(scaled));
        img.setAlignmentX(Component.CENTER_ALIGNMENT);
        return img;
    }

    private JLabel createLabel(String text, String fontName, int fontSize) {
        return createLabel(text, fontName, fontSize, Color.BLACK);
    }

    private JLabel createLabel(String text, String fontName, int fontSize, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(fontName, Font.PLAIN, fontSize));
        label.setForeground(color);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JPanel createPricePanel(String price) {
        JLabel priceLabel = new JLabel(price);
        priceLabel.setFont(new Font("Poppins", Font.BOLD, 18));

        RoundedButton plusButton = new RoundedButton("+", 8);
        plusButton.setFont(new Font("Poppins", Font.BOLD, 14));
        plusButton.setBackground(new Color(0xC67C4E));
        plusButton.setForeground(Color.WHITE);
        plusButton.setContentAreaFilled(false);
        plusButton.setOpaque(false);
        plusButton.setBorderPainted(false);
        plusButton.setPreferredSize(new Dimension(55, 55));
        plusButton.setMaximumSize(new Dimension(55, 55));

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 0));
        panel.setOpaque(false);
        panel.add(priceLabel);
        panel.add(plusButton);

        return panel;
    }
}
