package ui.cats;

import components.GradientPanel;
import components.RoundedPanel;

import javax.swing.*;
import java.awt.*;

public class AlfilFrame extends JFrame {

    public AlfilFrame() {
        setTitle("Perfil de Alfil");
        setSize(412, 917);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        GradientPanel background = new GradientPanel(new Color(0xBD6E36), new Color(0xCDCDCD), true);
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
        setContentPane(background);

        background.add(buildImagePanel());
        background.add(Box.createVerticalStrut(20));
        background.add(buildInfoPanel("ALFIL", "4 años", "Macho", "Dormilón profesional y fan de las mantas suaves. Le encanta hacerse ovillo y ronronear a volumen máximo.", "Casas tranquilas con sofá libre."));
    }

    private JPanel buildImagePanel() {
        JPanel imgPanel = new JPanel();
        imgPanel.setOpaque(false);
        imgPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));

        ImageIcon originalIcon = new ImageIcon("resources/images/alfil1.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(330, 330, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));

        RoundedPanel imgRoundedPanel = new RoundedPanel(30, Color.WHITE);
        imgRoundedPanel.setLayout(new BorderLayout());
        imgRoundedPanel.add(imageLabel, BorderLayout.CENTER);
        imgRoundedPanel.setPreferredSize(new Dimension(330, 330));

        imgPanel.add(imgRoundedPanel);

        return imgPanel;
    }

    private JPanel buildInfoPanel(String name, String age, String gender, String about, String ideal) {
        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(false);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        // Panel con nombre, edad y género
        RoundedPanel topInfo = new RoundedPanel(20, Color.WHITE);
        topInfo.setLayout(new BoxLayout(topInfo, BoxLayout.Y_AXIS));
        topInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        topInfo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 28));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel ageLabel = new JLabel("🎂 " + age);
        ageLabel.setFont(new Font("Fredoka Regular", Font.PLAIN, 18));
        ageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel genderLabel = new JLabel("♂ " + gender);
        genderLabel.setFont(new Font("Fredoka Regular", Font.PLAIN, 18));
        genderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        topInfo.add(nameLabel);
        topInfo.add(Box.createVerticalStrut(8));
        topInfo.add(ageLabel);
        topInfo.add(Box.createVerticalStrut(4));
        topInfo.add(genderLabel);

        // Panel descripción
        RoundedPanel aboutPanel = new RoundedPanel(20, Color.WHITE);
        aboutPanel.setLayout(new BorderLayout());
        aboutPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel aboutLabel = new JLabel("<html><b>Sobre mí</b><br>" + about + "</html>");
        aboutLabel.setFont(new Font("Fredoka Regular", Font.PLAIN, 16));

        aboutPanel.add(aboutLabel, BorderLayout.CENTER);

        // Panel ideal
        RoundedPanel idealPanel = new RoundedPanel(20, Color.WHITE);
        idealPanel.setLayout(new BorderLayout());
        idealPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel idealLabel = new JLabel("<html><b>Ideal para:</b><br>" + ideal + "</html>");
        idealLabel.setFont(new Font("Fredoka Regular", Font.PLAIN, 16));

        idealPanel.add(idealLabel, BorderLayout.CENTER);

        // Agregar todos al infoPanel
        infoPanel.add(topInfo);
        infoPanel.add(Box.createVerticalStrut(20));
        infoPanel.add(aboutPanel);
        infoPanel.add(Box.createVerticalStrut(20));
        infoPanel.add(idealPanel);

        return infoPanel;
    }
}