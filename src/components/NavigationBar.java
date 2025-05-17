package components;

import javax.swing.*;
import java.awt.*;

public class NavigationBar extends JPanel {
    private final String selected;

    public NavigationBar(String selectedItem) {
        this.selected = selectedItem;
        setOpaque(false);
        setLayout(new GridLayout(1, 5));
        setPreferredSize(new Dimension(0, 60));

        add(createNavItem("Inicio", "home.png"));
        add(createNavItem("Carta", "carta.png"));
        add(createNavItem("Pedido", "pedido.png"));
        add(createNavItem("Gatos", "gatos.png"));
        add(createNavItem("Perfil", "perfil.png"));
    }

    private JPanel createNavItem(String label, String iconFilename) {
        boolean isSelected = label.equals(selected);

        // Panel para cada ítem
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);

        // Panel interno apilado verticalmente (icono + texto)
        JPanel inner = new JPanel();
        inner.setOpaque(false);
        inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));
        inner.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Cargar imagen
        ImageIcon icon = loadIcon(iconFilename, 22, 22);

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel textLabel = new JLabel(label);
        textLabel.setFont(new Font("Poppins Medium", Font.PLAIN, 12));
        textLabel.setForeground(isSelected ? new Color(255, 150, 50) : Color.WHITE);
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        inner.add(iconLabel);
        inner.add(Box.createVerticalStrut(4)); // pequeño espacio entre icono y texto
        inner.add(textLabel);

        panel.add(inner); // centrado con GridBagLayout
        return panel;
    }

    private ImageIcon loadIcon(String fileName, int width, int height) {
        ImageIcon original = new ImageIcon("resources/icons/" + fileName);
        Image scaled = original.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar fondo redondeado oscuro
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.decode("#2C2C2C"));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
    }
}
