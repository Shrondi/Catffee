package components.dialog;

import javax.swing.*;
import java.awt.*;

public class InfoDialog extends JDialog {
    public InfoDialog(Window parent, String title, String mainMessage, String secondaryMessage, Icon icon, int durationMs) {
        super(parent, title, ModalityType.APPLICATION_MODAL);
        setUndecorated(true);
        int width = parent != null ? parent.getWidth() : 400;
        int height = parent != null ? parent.getHeight() : 300;
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(width, height));
        panel.setMinimumSize(new Dimension(width, height));
        panel.setMaximumSize(new Dimension(width, height));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0xC67C4E), 2, true),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 30, 0);
        gbc.anchor = GridBagConstraints.NORTH;
        // Icono
        if (icon != null) {
            JLabel iconLabel = new JLabel(icon);
            iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(iconLabel, gbc);
            gbc.gridy++;
        }
        // Título
        JLabel titleLabel = new JLabel("<html><div style='width: "+(width-80)+"px; text-align:center; font-size:26px; color:#C67C4E;'><b>" + title + "</b></div></html>", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 26));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, gbc);
        // Mensaje principal
        gbc.gridy++;
        JLabel msg = new JLabel("<html><div style='width: "+(width-80)+"px; text-align:center; font-size:17px; color:#313131;'>" + mainMessage + "</div></html>", SwingConstants.CENTER);
        msg.setFont(new Font("Sora Regular", Font.PLAIN, 17));
        msg.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(msg, gbc);
        // Mensaje secundario
        if (secondaryMessage != null && !secondaryMessage.isEmpty()) {
            gbc.gridy++;
            gbc.insets = new Insets(10, 0, 0, 0);
            JLabel secondary = new JLabel("<html><div style='width: "+(width-60)+"px; text-align:center; font-size:14px; color:#888;'>" + secondaryMessage + "</div></html>", SwingConstants.CENTER);
            secondary.setFont(new Font("Sora Regular", Font.PLAIN, 14));
            secondary.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(secondary, gbc);
        }
        setContentPane(panel);
        pack();
        setSize(width, height);
        // Centrar dentro del parent
        if (parent != null) {
            Point parentLoc = parent.getLocationOnScreen();
            setLocation(parentLoc.x, parentLoc.y);
        } else {
            setLocationRelativeTo(null);
        }
        if (durationMs > 0) {
            new javax.swing.Timer(durationMs, _ -> dispose()) {{ setRepeats(false); }}.start();
        }
    }
} 