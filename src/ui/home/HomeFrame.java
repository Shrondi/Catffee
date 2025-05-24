package ui.home;

import javax.swing.*;
import java.awt.*;

import components.bar.NavigationBar;
import components.button.RoundedButton;
import components.panel.RoundedPanel;
import controller.NavigationManager;
import ui.BaseFrame;
import utils.UserStorage;

public class HomeFrame extends BaseFrame {

    public HomeFrame(String title) {
        super(title);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(0xF7F7F7));

        mainPanel.add(topSection());
        mainPanel.add(productScroll());

        add(mainPanel);

        NavigationBar navBar = new NavigationBar("Inicio");
        new NavigationManager(this, navBar);
        add(navBar, BorderLayout.SOUTH);
    }

    private JPanel topSection() {
        JPanel container = new JPanel();
        container.setLayout(new OverlayLayout(container));
        Dimension size = new Dimension(412, 330);
        container.setPreferredSize(size);
        container.setMaximumSize(size);

        container.add(headerText());
        container.add(headerImage());

        return container;
    }

    private JLabel headerImage() {
        ImageIcon icon = new ImageIcon("resources/images/cabecera_bienvenida.png");
        Image scaled = icon.getImage().getScaledInstance(412, 330, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(scaled));
        label.setAlignmentX(0.5f);
        label.setAlignmentY(0.5f);
        return label;
    }

    private JPanel headerText() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setAlignmentX(0.5f);
        panel.setAlignmentY(0.5f);

        String nombre = UserStorage.getInstance().getCurrentUser().getNombreCompleto();
        JLabel welcomeText = new JLabel(
            String.format("<html><div style='text-align: left;'>Bienvenido,<br><b>%s ☕</b></div></html>", nombre));
        welcomeText.setForeground(Color.WHITE);
        welcomeText.setFont(new Font("Segoe UI Emoji", Font.BOLD, 32));
        welcomeText.setHorizontalAlignment(SwingConstants.LEFT);
        welcomeText.setVerticalAlignment(SwingConstants.BOTTOM);
        welcomeText.setBorder(BorderFactory.createEmptyBorder(0, 40, 40, 0));

        panel.add(welcomeText, BorderLayout.CENTER);
        return panel;
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

        contentPanel.add(destacadosLabel);
        contentPanel.add(productGrid());

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
        Dimension size = new Dimension(170, 250);
        box.setPreferredSize(size);
        box.setMaximumSize(size);
        box.setBackground(Color.WHITE);
        box.setBorder(BorderFactory.createEmptyBorder(10, 10, 40, 20));

        box.add(Box.createVerticalStrut(10));
        box.add(productImage(imagePath));
        box.add(Box.createVerticalStrut(5));

        box.add(createLeftAlignedLabel(title, "Sora SemiBold", 16));
        box.add(Box.createVerticalStrut(5));
        box.add(createLeftAlignedLabel(subtitle, "Sora Regular", 12, Color.GRAY));
        box.add(Box.createVerticalStrut(5));
        box.add(createPricePanel(price));

        return box;
    }

    private JPanel createLeftAlignedLabel(String text, String fontName, int fontSize) {
        return createLeftAlignedLabel(text, fontName, fontSize, Color.BLACK);
    }

    private JPanel createLeftAlignedLabel(String text, String fontName, int fontSize, Color color) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panel.setOpaque(false);
        JLabel label = new JLabel(text);
        label.setFont(new Font(fontName, Font.PLAIN, fontSize));
        label.setForeground(color);
        panel.add(label);
        return panel;
    }

    private JLabel productImage(String path) {
        ImageIcon icon = new ImageIcon(path);
        Image scaled = icon.getImage().getScaledInstance(140, 130, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(scaled));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JPanel createPricePanel(String price) {
        JLabel priceLabel = new JLabel(price);
        priceLabel.setFont(new Font("Poppins", Font.BOLD, 18));
        priceLabel.setForeground(Color.BLACK);

        RoundedButton plusButton = new RoundedButton("+", 8);
        plusButton.setFont(new Font("Poppins", Font.PLAIN, 20));
        plusButton.setMargin(new Insets(0, 0, 0, 0));
        plusButton.setBackground(new Color(0xC67C4E));
        plusButton.setForeground(Color.WHITE);
        plusButton.setContentAreaFilled(true);
        plusButton.setOpaque(true);
        plusButton.setBorderPainted(false);
        
        Dimension btnSize = new Dimension(30, 30);
        plusButton.setPreferredSize(btnSize);
        plusButton.setMaximumSize(btnSize);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.add(priceLabel, BorderLayout.WEST);
        panel.add(plusButton, BorderLayout.EAST);

        return panel;
    }
}
