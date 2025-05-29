package ui.profile;

import components.bar.NavigationBar;
import components.panel.RoundedPanel;
import ui.BaseFrame;

import javax.swing.*;
import java.awt.*;

public class ProfileFrame extends BaseFrame {

    public ProfileFrame(String title) {
        super(title);
        setLayout(new BorderLayout());

        add(buildTopBar(), BorderLayout.NORTH);
        add(buildContentPanel(), BorderLayout.CENTER);
        add(new NavigationBar("Perfil"), BorderLayout.SOUTH);
    }

    private JPanel buildTopBar() {
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.decode("#252424"));
        topPanel.setPreferredSize(new Dimension(413, 85));
        topPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Perfil");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 30));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(23, 30, 23, 0));

        topPanel.add(titleLabel, BorderLayout.WEST);
        return topPanel;
    }

    private JPanel buildContentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.decode("#F9F9F9"));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        // Tarjeta de perfil
        RoundedPanel profileCard = new RoundedPanel(30);
        profileCard.setBackground(Color.WHITE);
        profileCard.setLayout(new BoxLayout(profileCard, BoxLayout.X_AXIS));
        profileCard.setAlignmentX(Component.CENTER_ALIGNMENT);
        profileCard.setPreferredSize(new Dimension(420, 160));
        profileCard.setMaximumSize(new Dimension(420, 160));
        profileCard.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        ImageIcon profileIcon = new ImageIcon("resources/images/profile_placeholder.png");
        Image profileImg = profileIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        JLabel profilePic = new JLabel(new ImageIcon(profileImg));

        JLabel nameBlock = new JLabel("<html><div style='font-size:18px; font-family:Sora SemiBold; font-weight:plain;'>Nombre completo</div><div style='color:gray;'>@Usuario</div></html>");
        nameBlock.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        profileCard.add(profilePic);
        profileCard.add(nameBlock);

        panel.add(Box.createVerticalStrut(50));
        panel.add(profileCard);
        panel.add(Box.createVerticalStrut(100));

        panel.add(buildOption("resources/images/translate.png", "Idioma"));
        panel.add(Box.createVerticalStrut(20));
        panel.add(buildOption("resources/images/heart.png", "¡Valóranos!"));
        panel.add(Box.createVerticalStrut(20));
        panel.add(buildOption("resources/images/log_out.png", "Cerrar sesión"));

        return panel;
    }

    private RoundedPanel buildOption(String iconPath, String labelText) {
        RoundedPanel option = new RoundedPanel(30);
        option.setBackground(Color.WHITE);
        option.setLayout(new BorderLayout());
        option.setPreferredSize(new Dimension(340, 70));
        option.setMaximumSize(new Dimension(340, 70));
        option.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        ImageIcon icon = new ImageIcon(iconPath);
        Image scaledIcon = icon.getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(scaledIcon));

        JLabel textLabel = new JLabel(labelText);
        textLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 18));

        JComponent rightComponent;
        if (labelText.equals("Idioma")) {
            // Sustituimos el combo por un icono que lanza un diálogo modal personalizado
            ImageIcon arrowIcon = new ImageIcon("resources/images/next_icon.png");
            Image scaledArrow = arrowIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            JLabel arrowLabel = new JLabel(new ImageIcon(scaledArrow));
            arrowLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            arrowLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(option), "Selecciona idioma", true);
                    dialog.setUndecorated(true);
                    dialog.setSize(200, 120);
                    Point location = option.getLocationOnScreen();
                    int x = location.x + (option.getWidth() - dialog.getWidth()) / 2;
                    int y = location.y + (option.getHeight() - dialog.getHeight()) / 2 - 30;
                    dialog.setLocation(x, y);

                    JPanel content = new JPanel();
                    content.setBackground(Color.WHITE);
                    content.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
                    content.setLayout(new GridLayout(2, 1));

                    JButton englishButton = new JButton("Inglés");
                    englishButton.setFont(new Font("Sora", Font.PLAIN, 16));
                    englishButton.setFocusPainted(false);
                    englishButton.addActionListener(evt -> dialog.dispose());

                    JButton spanishButton = new JButton("Español");
                    spanishButton.setFont(new Font("Sora", Font.PLAIN, 16));
                    spanishButton.setFocusPainted(false);
                    spanishButton.addActionListener(evt -> dialog.dispose());

                    content.add(englishButton);
                    content.add(spanishButton);

                    dialog.setContentPane(content);
                    dialog.setVisible(true);
                }
            });
            rightComponent = arrowLabel;
        } else {
            ImageIcon arrowIcon = new ImageIcon("resources/images/next_icon.png");
            Image scaledArrow = arrowIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            rightComponent = new JLabel(new ImageIcon(scaledArrow));
        }

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 20));
        left.setOpaque(false);
        left.add(iconLabel);
        left.add(Box.createHorizontalStrut(10));
        left.add(textLabel);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 20));
        right.setOpaque(false);
        right.add(rightComponent);

        option.add(left, BorderLayout.WEST);
        option.add(right, BorderLayout.EAST);

        return option;
    }
}
