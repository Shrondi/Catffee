package components.bar;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import utils.I18n;

/**
 * Barra de navegación inferior para Catffee. Permite cambiar entre secciones principales.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
/**
 * Barra de navegación personalizada para la aplicación.
 */
public class NavigationBar extends JPanel {
    private String selected;
    private final List<JLabel> textLabels = new ArrayList<>();
    private static final String[] SECTION_KEYS = {"HOME", "MENU", "ORDER", "CATS", "PROFILE"};
    private static final String[] ICONS = {"home.png", "carta.png", "pedido.png", "gatos.png", "perfil.png"};
    private static final Color SELECTED_COLOR = new Color(255, 150, 50);
    private static final Color UNSELECTED_COLOR = Color.WHITE;
    private JLabel orderIconLabel;

    public NavigationBar(String selectedItem) {
        this.selected = selectedItem;
        textLabels.clear();
        setOpaque(false);
        setLayout(new GridLayout(1, SECTION_KEYS.length));
        setPreferredSize(new Dimension(0, 60));
        String[] sectionTexts = {
            I18n.t("nav_home"),
            I18n.t("nav_menu"),
            I18n.t("nav_order"),
            I18n.t("nav_cats"),
            I18n.t("nav_profile")
        };
        for (int i = 0; i < SECTION_KEYS.length; i++) {
            add(createNavItem(SECTION_KEYS[i], sectionTexts[i], ICONS[i], i));
        }
    }

    public void setSelectedSection(String selectedItem) {
        this.selected = selectedItem;
        updateSelectedVisual();
    }

    private void updateSelectedVisual() {
        for (int i = 0; i < SECTION_KEYS.length; i++) {
            boolean isSelected = SECTION_KEYS[i].equals(selected);
            textLabels.get(i).setForeground(isSelected ? SELECTED_COLOR : UNSELECTED_COLOR);
            textLabels.get(i).repaint();
        }
        repaint();
    }

    private JPanel createNavItem(String key, String label, String iconFilename, int index) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);

        JPanel inner = new JPanel();
        inner.setOpaque(false);
        inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));
        inner.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon icon = loadIcon(iconFilename, 22, 22);
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconLabel.setForeground(SECTION_KEYS[index].equals(selected) ? SELECTED_COLOR : UNSELECTED_COLOR);

        if ("ORDER".equals(key)) {
            orderIconLabel = iconLabel;
        }

        JLabel textLabel = new JLabel(label);
        textLabel.setFont(new Font("Poppins Medium", Font.PLAIN, 12));
        textLabel.setForeground(SECTION_KEYS[index].equals(selected) ? SELECTED_COLOR : UNSELECTED_COLOR);
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textLabels.add(textLabel);
        
        inner.add(iconLabel);
        inner.add(Box.createVerticalStrut(4));
        inner.add(textLabel);
        panel.add(inner);
        panel.putClientProperty("navKey", key);
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

    public void shakeOrderIcon() {
        if (orderIconLabel == null) return;
        final int shakeDistance = 5;
        final int shakeTimes = 16;
        final int delay = 12;
        Point originalLocation = orderIconLabel.getLocation();
        Timer timer = new Timer(delay, null);
        timer.addActionListener(new java.awt.event.ActionListener() {
            int count = 0;
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                double angle = 2 * Math.PI * count / shakeTimes;
                int offsetX = (int) Math.round(Math.sin(angle) * shakeDistance);
                int offsetY = (int) Math.round(Math.cos(angle) * (shakeDistance / 2.0));
                orderIconLabel.setLocation(originalLocation.x + offsetX, originalLocation.y + offsetY);
                count++;
                if (count > shakeTimes) {
                    orderIconLabel.setLocation(originalLocation);
                    timer.stop();
                }
            }
        });
        timer.start();
    }
}