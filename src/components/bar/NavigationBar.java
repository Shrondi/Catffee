package components.bar;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NavigationBar extends JPanel {
    private String selected;
    private final List<JLabel> textLabels = new ArrayList<>();
    private static final String[] SECTIONS = {"Inicio", "Carta", "Pedido", "Gatos", "Perfil"};
    private static final String[] ICONS = {"home.png", "carta.png", "pedido.png", "gatos.png", "perfil.png"};
    private static final Color SELECTED_COLOR = new Color(255, 150, 50);
    private static final Color UNSELECTED_COLOR = Color.WHITE;

    public NavigationBar(String selectedItem) {
        this.selected = selectedItem;
        textLabels.clear();
        setOpaque(false);
        setLayout(new GridLayout(1, SECTIONS.length));
        setPreferredSize(new Dimension(0, 60));
        for (int i = 0; i < SECTIONS.length; i++) {
            add(createNavItem(SECTIONS[i], ICONS[i]));
        }
    }

    public void setSelectedSection(String selectedItem) {
        this.selected = selectedItem;
        updateSelectedVisual();
    }

    private void updateSelectedVisual() {
        for (JLabel label : textLabels) {
            label.setForeground(selected.equals(label.getText()) ? SELECTED_COLOR : UNSELECTED_COLOR);
            label.repaint();
        }
        repaint();
    }

    private JPanel createNavItem(String label, String iconFilename) {
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
        textLabel.setForeground(selected.equals(label) ? SELECTED_COLOR : UNSELECTED_COLOR);
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textLabels.add(textLabel);
        
        inner.add(iconLabel);
        inner.add(Box.createVerticalStrut(4));
        inner.add(textLabel);
        panel.add(inner);
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
        g2.setColor(Color.decode("#2C2C2C"));
        g2.fillRoundRect(0, 0, width, height, 0, 0);
        int shadowThickness = 8;
        for (int i = 0; i < shadowThickness; i++) {
            float opacity = 0.10f * (1f - (float) i / shadowThickness);
            g2.setColor(new Color(0, 0, 0, (int) (opacity * 255)));
            g2.drawRoundRect(i, i, width - 2 * i - 1, height - 2 * i - 1, 0, 0);
        }
        g2.dispose();
    }
}