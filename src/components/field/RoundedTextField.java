package components.field;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Campo de texto con bordes redondeados y estilos personalizados para Catffee.
 * Permite personalizar el radio, color de borde y placeholder.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
public class RoundedTextField extends JTextField {
    private int radius;
    private Color borderColor = Color.GRAY;
    private float borderWidth = 1.0f;
    private String placeholder = "";
    private Color placeholderColor = new Color(160, 160, 160);
    private Color textColor = Color.BLACK;
    private boolean showingPlaceholder = true;

    /**
     * Campo de texto personalizado con esquinas redondeadas.
     */
    public RoundedTextField(int radius) {
        this.radius = radius;
        setOpaque(false);
        setBorder(new EmptyBorder(5, 10, 5, 10));
        setForeground(placeholderColor); // inicial placeholder color

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (showingPlaceholder) {
                    setText("");
                    setForeground(textColor);
                    showingPlaceholder = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(placeholder);
                    setForeground(placeholderColor);
                    showingPlaceholder = true;
                }
            }
        });
    }

    /**
     * Establece el texto del placeholder y actualiza el campo si está vacío o mostrando el placeholder anterior.
     */
    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;

        if (!hasFocus()) {
            if (getText().isEmpty() || showingPlaceholder) {
                showingPlaceholder = true;
                setText(placeholder);
                setForeground(placeholderColor);
            }
        }
    }

    /**
     * Devuelve el texto real sin el placeholder.
     */
    @Override
    public String getText() {
        return showingPlaceholder ? "" : super.getText();
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }

    public void setBorderWidth(float width) {
        this.borderWidth = width;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getBackground());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        super.paintComponent(g2);
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
