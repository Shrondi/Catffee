package ui;

import javax.swing.*;

import components.RoundedPanel;
import components.RoundedButton;

import java.awt.*;

public class MainMenuFrame extends JFrame {

    public MainMenuFrame() {
        setTitle("Menu Principal");
        setSize(412, 917);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setContentPane(buildMainPanel());
    }

    private JPanel buildMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(0xF7F7F7));

        // Panel superior con imagen y bienvenida
        JPanel headerPanel = buildTopSection();
        headerPanel.setMaximumSize(new Dimension(412, 330));
        mainPanel.add(headerPanel);

        // Panel de productos con scroll
        JScrollPane scrollPane = buildProductScrollPane();
        mainPanel.add(scrollPane);

        return mainPanel;
    }

    private JPanel buildTopSection() {
        // Panel contenedor con OverlayLayout
        JPanel container = new JPanel();
        container.setLayout(new OverlayLayout(container));
        container.setPreferredSize(new Dimension(412, 330));
        container.setMaximumSize(new Dimension(412, 330));

        // Imagen escalada
        ImageIcon icon = new ImageIcon("resources/images/cabecera_bienvenida.png");
        Image scaled = icon.getImage().getScaledInstance(412, 330, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaled));
        imageLabel.setAlignmentX(0.5f);
        imageLabel.setAlignmentY(0.5f);

        // Panel con texto encima
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setOpaque(false);
        textPanel.setAlignmentX(0.5f);
        textPanel.setAlignmentY(0.5f);

        JLabel welcomeText = new JLabel("<html><div style='text-align: left;'>Bienvenido,<br><b>Nombre ☕</b></div></html>");
        welcomeText.setForeground(Color.WHITE);
        welcomeText.setFont(new Font("Segoe UI Emoji", Font.BOLD, 32));
        welcomeText.setHorizontalAlignment(SwingConstants.LEFT);
        welcomeText.setVerticalAlignment(SwingConstants.BOTTOM);
        welcomeText.setBorder(BorderFactory.createEmptyBorder(0, 40, 40, 0)); // margen izquierdo aumentado

        textPanel.add(welcomeText, BorderLayout.CENTER);

        // Añadir primero imagen, luego texto (para que quede encima)
        container.add(textPanel);
        container.add(imageLabel);

        return container;
    }

    private JScrollPane buildProductScrollPane() {
        // Panel vertical que contendrá el título y la cuadrícula
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(0xF7F7F7));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel destacadosLabel = new JLabel("PRODUCTOS DESTACADOS");
        destacadosLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 20));
        destacadosLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        destacadosLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 16, 0));
        contentPanel.add(destacadosLabel);

        // Grid de productos
        JPanel productGrid = new JPanel(new GridLayout(0, 2, 12, 12));
        productGrid.setBackground(new Color(0xF7F7F7));
        productGrid.setAlignmentX(Component.CENTER_ALIGNMENT);

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
            productGrid.add(createProductBox(names[i], descriptions[i], prices[i], images[i]));
        }

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

    private JPanel createProductBox(String title, String subtitle, String price, String imagePath) {
        RoundedPanel box = new RoundedPanel(16);
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setPreferredSize(new Dimension(160, 240));
        box.setMaximumSize(new Dimension(160, 240));
        box.setBackground(Color.WHITE);
        box.add(Box.createVerticalStrut(10));

        // Imagen
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaled = icon.getImage().getScaledInstance(140, 130, Image.SCALE_SMOOTH);
        JLabel img = new JLabel(new ImageIcon(scaled));
        img.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 22));
        img.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Título
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 22));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Descripción
        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setFont(new Font("Sora Regular", Font.PLAIN, 12));
        subtitleLabel.setForeground(Color.GRAY);
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 22));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Precio
        JLabel priceLabel = new JLabel(price);
        priceLabel.setFont(new Font("Poppins", Font.BOLD, 18));
        priceLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        // Botón "+"
        RoundedButton plusButton = new RoundedButton("+", 8);
        plusButton.setFont(new Font("Poppins", Font.BOLD, 14));
        plusButton.setBackground(new Color(0xC67C4E));
        plusButton.setForeground(Color.WHITE);
        plusButton.setContentAreaFilled(false);
        plusButton.setOpaque(false);
        plusButton.setBorderPainted(false);
        plusButton.setPreferredSize(new Dimension(55, 55));
        plusButton.setMaximumSize(new Dimension(55, 55));

        box.add(img);
        box.add(titleLabel);
        box.add(subtitleLabel);

        // Contenedor inferior para precio y botón
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 0)); // 8 píxeles de espacio horizontal
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        bottomPanel.add(priceLabel);
        bottomPanel.add(plusButton);

        box.add(img);
        box.add(Box.createVerticalStrut(5));
        box.add(titleLabel);
        box.add(Box.createVerticalStrut(5));
        box.add(subtitleLabel);
        box.add(Box.createVerticalStrut(5));
        box.add(bottomPanel);

        return box;
    }
}