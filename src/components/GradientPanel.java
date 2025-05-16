package components;

import javax.swing.*;
import java.awt.*;

public class GradientPanel extends JPanel {
    private Color startColor = new Color(0xBD6E36);
    private Color endColor = new Color(0xCDCDCD);
    private Direction direction = Direction.VERTICAL;

    public enum Direction {
        VERTICAL, HORIZONTAL, DIAGONAL_TL_BR, DIAGONAL_TR_BL
    }

    public GradientPanel() {
        setOpaque(false);
    }

    public GradientPanel(Color startColor, Color endColor, Direction direction) {
        this.startColor = startColor;
        this.endColor = endColor;
        this.direction = direction;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();

        GradientPaint gp;

        switch (direction) {
            case HORIZONTAL:
                gp = new GradientPaint(0, 0, startColor, width, 0, endColor);
                break;
            case DIAGONAL_TL_BR:
                gp = new GradientPaint(0, 0, startColor, width, height, endColor);
                break;
            case DIAGONAL_TR_BL:
                gp = new GradientPaint(width, 0, startColor, 0, height, endColor);
                break;
            case VERTICAL:
            default:
                gp = new GradientPaint(0, 0, startColor, 0, height, endColor);
                break;
        }

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);
        g2d.dispose();
    }
}