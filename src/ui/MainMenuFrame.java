package ui;

import javax.swing.*;

import components.GradientPanel;
import components.RoundedPanel;

import java.awt.*;

public class MainMenuFrame extends JFrame {

    public MainMenuFrame() {
        setTitle("Cat Café");
        setSize(412, 917);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setContentPane(buildMainPanel());
    }

    private JPanel buildMainPanel() {
        JPanel mainPanel = new JPanel(null); // Layout absoluto
        mainPanel.setPreferredSize(new Dimension(412, 917));
        mainPanel.setBackground(new Color(0xF7F7F7));

        // Top bar negra
        JPanel topPanel = buildTopBar();
        topPanel.setBounds(0, 0, 412, 266);
        mainPanel.add(topPanel);

        // Panel de productos con scroll
        JScrollPane scrollPane = buildProductScrollPane();
        scrollPane.setBounds(0, 320, 412, 600);
        mainPanel.add(scrollPane);

        JPanel welcomePanel = buildWelcomePanel();
        welcomePanel.setBounds(12, 190, 387, 260); // Este es el tamaño "real" del panel marrón
        mainPanel.add(welcomePanel);
        mainPanel.setComponentZOrder(welcomePanel, 0); // Al frente

        // 👇 Esto pone el welcomePanel en el frente visual (por encima de topPanel y scroll)
        mainPanel.setComponentZOrder(welcomePanel, 0);

        return mainPanel;
    }

    private JPanel buildTopBar() {
        GradientPanel topPanel = new GradientPanel(
            new Color(0x11, 0x11, 0x11),
            new Color(0x31, 0x31, 0x31),
            GradientPanel.Direction.DIAGONAL_TR_BL
        );
        topPanel.setPreferredSize(new Dimension(412, 266));
        topPanel.setLayout(new BorderLayout());

        return topPanel;
    }

    private JPanel buildWelcomePanel() {
        RoundedPanel welcomePanel = new RoundedPanel(20, new Color(0x94, 0x72, 0x57));
        welcomePanel.setLayout(null);
        welcomePanel.setOpaque(true); // Para que pinte el fondo marrón

        // Imagen del gato sobresaliendo hacia arriba
        JLabel catLabel = new JLabel(new ImageIcon("resources/images/cat_waving.png"));
        catLabel.setBounds(83, -60, 265, 265); // Y negativo para que sobresalga hacia arriba
        welcomePanel.add(catLabel);

        // Texto de bienvenida
        JLabel welcomeText = new JLabel("<html><div style='text-align: center;'>Bienvenido,<br><b>Nombre</b> ☕</div></html>");
        welcomeText.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        welcomeText.setForeground(Color.WHITE);
        welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeText.setBounds(43, 180, 239, 80); // Asegúrate que quepa en la altura del panel
        welcomePanel.add(welcomeText);

        return welcomePanel;
    }

    private JScrollPane buildProductScrollPane() {
        JPanel productGrid = new JPanel(new GridLayout(0, 2, 12, 12));
        productGrid.setBackground(new Color(0xF7F7F7));
        productGrid.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (int i = 0; i < 8; i++) {
            productGrid.add(createProductBox("Producto " + (i + 1), "2.50€"));
        }

        JScrollPane scrollPane = new JScrollPane(productGrid);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        return scrollPane;
    }

    private JPanel createProductBox(String title, String price) {
        JPanel box = new JPanel();
        box.setPreferredSize(new Dimension(150, 180));
        box.setBackground(Color.WHITE);
        box.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel img = new JLabel(new ImageIcon("resources/images/meowcha.png"));
        img.setPreferredSize(new Dimension(140, 100));
        img.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel priceLabel = new JLabel(price);
        priceLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        box.add(Box.createVerticalStrut(10));
        box.add(img);
        box.add(Box.createVerticalStrut(10));
        box.add(titleLabel);
        box.add(priceLabel);

        return box;
    }
}