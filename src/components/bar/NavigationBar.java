package components.bar;

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

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);

        JPanel inner = new JPanel();
        inner.setOpaque(false);
        inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));
        inner.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon icon = loadIcon(iconFilename, 22, 22);

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel textLabel = new JLabel(label);
        textLabel.setFont(new Font("Poppins Medium", Font.PLAIN, 12));
        textLabel.setForeground(isSelected ? new Color(255, 150, 50) : Color.WHITE);
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        inner.add(iconLabel);
        inner.add(Box.createVerticalStrut(4));
        inner.add(textLabel);

        panel.add(inner);

        // Guardamos el label como propiedad cliente para acceso fácil
        panel.putClientProperty("navLabel", label);

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
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Pintar fondo primero
        g2.setColor(Color.decode("#2C2C2C"));
        g2.fillRoundRect(0, 0, width, height, 0, 0);

        // Sombra interior
        int shadowThickness = 8;
        for (int i = 0; i < shadowThickness; i++) {
            float opacity = 0.10f * (1f - (float) i / shadowThickness); // De 10% a 0%
            g2.setColor(new Color(0, 0, 0, (int) (opacity * 255)));
            g2.drawRoundRect(i, i, width - 2 * i - 1, height - 2 * i - 1, 0, 0);
        }

        g2.dispose();
    }

}