package ui;

import javax.swing.*;

import java.awt.*;

public class OrderEmptyFrame extends JFrame {

    public OrderEmptyFrame() {
        setTitle("Pedido");
        setSize(412, 917);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new BorderLayout());

        add(buildTopBar(), BorderLayout.NORTH);
        add(buildEmptyOrderPanel(), BorderLayout.CENTER);
    }

    private JPanel buildTopBar() {
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.decode("#252424"));
        topPanel.setPreferredSize(new Dimension(413, 85));
        topPanel.setMaximumSize(new Dimension(413, 85));
        topPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Pedido");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Sora Semibold", Font.PLAIN, 30));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(25, 39, 24, 250));

        topPanel.add(titleLabel, BorderLayout.CENTER);

        return topPanel;
    }

    private JPanel buildEmptyOrderPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // Espaciador arriba
        contentPanel.add(Box.createVerticalStrut(150));

        // Icono carrito
        ImageIcon icon = new ImageIcon("resources/images/cart.png");
        Image scaledImage = icon.getImage().getScaledInstance(113, 108, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(scaledImage));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconLabel.setBorder(BorderFactory.createEmptyBorder(100, 138, 0, 160));
        contentPanel.add(iconLabel);

        // Espaciador
        contentPanel.add(Box.createVerticalStrut(38));

        // Texto principal
        JLabel mainText = new JLabel("Sin gatos ni café");
        mainText.setFont(new Font("Poppins SemiBold", Font.PLAIN, 28));
        mainText.setForeground(Color.decode("#000000"));
        mainText.setBorder(BorderFactory.createEmptyBorder(0, 90, 10, 85));
        mainText.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(mainText);

        // Texto secundario
        JLabel secondaryText = new JLabel("<html><div style='text-align: center;'>Selecciona algo de la carta<br>para añadir al pedido</div></html>");
        secondaryText.setFont(new Font("Poppins Regular", Font.PLAIN, 17));
        secondaryText.setBorder(BorderFactory.createEmptyBorder(10, 90, 10, 88));
        secondaryText.setForeground(Color.decode("#000000"));
        secondaryText.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(secondaryText);

        return contentPanel;
    }
}