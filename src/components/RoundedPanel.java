package components;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
    private int cornerRadius = 20;
    private Color backgroundColor = Color.WHITE;
    private Color shadowColor = new Color(0, 0, 0, 30);
    private int shadowOffset = 4;

    public RoundedPanel(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        setOpaque(false);
    }

    public RoundedPanel(int cornerRadius, Color backgroundColor) {
        this.cornerRadius = cornerRadius;
        this.backgroundColor = backgroundColor;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        Graphics2D g2 = (Graphics2D) g.create();

        // Habilitar antialiasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar sombra
        g2.setColor(shadowColor);
        g2.fillRoundRect(shadowOffset, shadowOffset, width - shadowOffset * 2, height - shadowOffset * 2, cornerRadius, cornerRadius);

        // Dibujar fondo del panel
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, width - shadowOffset * 2, height - shadowOffset * 2, cornerRadius, cornerRadius);

        g2.dispose();

        super.paintComponent(g);
    }
}
