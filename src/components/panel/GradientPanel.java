package components.panel;

import javax.swing.*;
import java.awt.*;

/**
 * Panel con fondo de gradiente personalizable para Catffee.
 * Permite elegir colores y orientación del gradiente.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
public class GradientPanel extends JPanel {
    private Color startColor = new Color(0xBD6E36);  // BD6E36
    private Color endColor = new Color(0xCDCDCD);   // CDCDCD
    private boolean vertical = true; // Vertical por defecto

    /**
     * Panel con fondo de gradiente.
     */
    public GradientPanel() {
        setOpaque(false);
    }

    public GradientPanel(Color startColor, Color endColor, boolean vertical) {
        this.startColor = startColor;
        this.endColor = endColor;
        this.vertical = vertical;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();

        GradientPaint gp;
        if (vertical) {
            gp = new GradientPaint(0, 0, startColor, 0, height, endColor);
        } else {
            gp = new GradientPaint(0, 0, startColor, width, 0, endColor);
        }

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);

        g2d.dispose();
    }
}