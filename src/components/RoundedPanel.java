package components;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
    private int cornerRadius = 20;
    private Color backgroundColor = Color.WHITE;
    private Color shadowColor = new Color(0, 0, 0, 30);
    private int shadowOffset = 4;

    // Borde opcional
    private Color borderColor = null;
    private int borderThickness = 1;

    public RoundedPanel(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        setOpaque(false);
    }

    public RoundedPanel(int cornerRadius, Color backgroundColor) {
        this.cornerRadius = cornerRadius;
        this.backgroundColor = backgroundColor;
        setOpaque(false);
    }

    // Nuevo setter para borde
    public void setBorder(Color color, int thickness) {
        this.borderColor = color;
        this.borderThickness = thickness;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Área ajustada para sombra y borde
        int offset = shadowOffset;
        int borderAdj = (borderColor != null && borderThickness > 0) ? borderThickness : 0;

        int arc = cornerRadius;

        // Sombra
        g2.setColor(shadowColor);
        g2.fillRoundRect(offset, offset, width - 2 * offset, height - 2 * offset, arc, arc);

        // Fondo
        g2.setColor(backgroundColor);
        g2.fillRoundRect(borderAdj / 2, borderAdj / 2, width - borderAdj - 2 * offset, height - borderAdj - 2 * offset,
                arc, arc);

        // Borde
        if (borderColor != null && borderThickness > 0) {
            g2.setStroke(new BasicStroke(borderThickness));
            g2.setColor(borderColor);
            g2.drawRoundRect(
                    borderThickness / 2,
                    borderThickness / 2,
                    width - borderThickness - 2 * offset,
                    height - borderThickness - 2 * offset,
                    arc,
                    arc);
        }

        g2.dispose();
        super.paintComponent(g);
    }
}
