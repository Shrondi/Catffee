package components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RoundedPasswordField extends JPasswordField {
    private int radius;
    private Color borderColor = Color.GRAY;
    private float borderWidth = 1.0f;
    private String placeholder = "";
    private Color placeholderColor = new Color(150, 150, 150);

    public RoundedPasswordField(int radius) {
        this.radius = radius;
        setOpaque(false);
        setBorder(new EmptyBorder(5, 10, 5, 10));
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }

    public void setBorderWidth(float width) {
        this.borderWidth = width;
        repaint();
    }

    public void setPlaceholder(String text) {
        this.placeholder = text;
        repaint();
    }

    public void setPlaceholderColor(Color color) {
        this.placeholderColor = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getBackground());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        super.paintComponent(g2);

        // Placeholder
        if (getPassword().length == 0 && !isFocusOwner() && placeholder != null) {
            g2.setColor(placeholderColor);
            g2.setFont(getFont().deriveFont(Font.ITALIC));
            g2.drawString(placeholder, getInsets().left + 5, getHeight() / 2 + getFont().getSize() / 2 - 2);
        }

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(borderWidth));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        g2.dispose();
    }

    @Override
    public boolean isOpaque() {
        return false;
    }
}
