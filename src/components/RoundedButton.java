package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundedButton extends JButton {
    private int radius;
    private Color normalBackground;
    private Color hoverBackground;
    private boolean isHovered = false;

    public RoundedButton(String text, int radius) {
        super(text);
        this.radius = radius;
        setFocusPainted(false);
        setContentAreaFilled(false);
        normalBackground = getBackground(); // Guarda el color de fondo original
        hoverBackground = createHoverColor(normalBackground, Color.WHITE, 0.2f); // Calcula el color de "hover"
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                repaint();
            }
        });
    }

    private Color createHoverColor(Color base, Color overlay, float ratio) {
        int red = (int) (base.getRed() * (1 - ratio) + overlay.getRed() * ratio);
        int green = (int) (base.getGreen() * (1 - ratio) + overlay.getGreen() * ratio);
        int blue = (int) (base.getBlue() * (1 - ratio) + overlay.getBlue() * ratio);
        return new Color(red, green, blue);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (isHovered) {
            g.setColor(hoverBackground);
        } else {
            g.setColor(normalBackground);
        }

        g2.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, radius, radius);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, radius, radius);
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        normalBackground = bg;
        hoverBackground = createHoverColor(normalBackground, Color.WHITE, 0.2f); // Recalcula el color de "hover"
        repaint();
    }
}