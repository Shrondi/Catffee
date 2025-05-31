package components.popup;

import javax.swing.*;
import java.awt.*;

public class PopupMessage extends JWindow {
    private PopupMessage(Window parent, String message, int durationMillis) {
        super(parent);
        JLabel label = new JLabel(message);
        label.setOpaque(true);
        label.setBackground(Color.decode("#947257"));
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.WHITE, 3),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        // Panel personalizado para fondo redondeado
        JPanel roundedPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.decode("#947257"));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 32, 32);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        roundedPanel.setOpaque(false);
        roundedPanel.setLayout(new BorderLayout());
        label.setOpaque(false);
        label.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        roundedPanel.add(label, BorderLayout.CENTER);
        setBackground(new Color(0,0,0,0));
        setContentPane(roundedPanel);
        pack();

        // Centrar respecto al padre o pantalla, pero abajo
        if (parent != null) {
            Point location = parent.getLocationOnScreen();
            int x = location.x + (parent.getWidth() - getWidth()) / 2;
            int y = location.y + parent.getHeight() - getHeight() - 70; // 50px de margen inferior
            setLocation(x, y);
        }

        setAlwaysOnTop(true);
        setVisible(true);
        toFront();
        requestFocus();

        new Timer(durationMillis, _ -> dispose()).start();
    }

    public static void show(Window parent, String message, int durationMillis) {
        new PopupMessage(parent, message, durationMillis);
    }
} 