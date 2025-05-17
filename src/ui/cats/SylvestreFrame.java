package ui.cats;

import components.GradientPanel;
import components.RoundedPanel;

import javax.swing.*;
import java.awt.*;

public class SylvestreFrame extends JFrame {

    public SylvestreFrame() {
        setTitle("Perfil de Sylvestre");
        setSize(412, 917);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        GradientPanel background = new GradientPanel(new Color(0xBD6E36), new Color(0xCDCDCD), true);
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
        setContentPane(background);

        background.add(buildImagePanel());
        background.add(Box.createVerticalStrut(20));
        background.add(buildInfoPanel("SYLVESTRE", "7 años", "Hembra", "El más elegante y relajado del grupo. Sylvestre se toma todo con calma y transmite mucha paz.", "Personas mayores o muy caseras que busquen paz."));
    }

    private JPanel buildImagePanel() {
        JPanel container = new JPanel(new BorderLayout());
        container.setOpaque(false);
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));

        JPanel imageRow = new JPanel();
        imageRow.setLayout(new BoxLayout(imageRow, BoxLayout.X_AXIS));
        imageRow.setOpaque(false);

        String[] paths = {
            "resources/images/sylvestre1.png",
            "resources/images/sylvestre2.png",
            "resources/images/sylvestre3.png"
        };

        for (String path : paths) {
            ImageIcon icon = new ImageIcon(path);
            Image scaled = icon.getImage().getScaledInstance(334, 334, Image.SCALE_SMOOTH);
            JLabel label = new JLabel(new ImageIcon(scaled));

            RoundedPanel rounded = new RoundedPanel(20, new Color(255, 255, 255, 0));
            rounded.setLayout(new BorderLayout());
            rounded.add(label, BorderLayout.CENTER);
            rounded.setPreferredSize(new Dimension(334, 334));
            rounded.setMaximumSize(new Dimension(334, 334));
            rounded.setOpaque(false);

            imageRow.add(rounded);
            imageRow.add(Box.createHorizontalStrut(10));
        }

        JScrollPane scrollPane = new JScrollPane(imageRow, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setPreferredSize(new Dimension(374, 354)); // un poco más alto por margen
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.setWheelScrollingEnabled(true);

        container.add(scrollPane, BorderLayout.CENTER);
        return container;
    }

    private JPanel buildInfoPanel(String name, String age, String gender, String about, String ideal) {
        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(false);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        // Panel con nombre, edad y género
        RoundedPanel topInfo = new RoundedPanel(10, Color.WHITE);
        topInfo.setLayout(new BoxLayout(topInfo, BoxLayout.Y_AXIS));
        topInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        topInfo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        topInfo.setPreferredSize(new Dimension(347, 161));
        topInfo.setMaximumSize(new Dimension(347, 161));

        // Panel para el nombre
        RoundedPanel namePanel = new RoundedPanel(10, new Color(255, 255, 255, 150)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // para dibujar hijos (JLabel, etc.)
                Graphics2D g2d = (Graphics2D) g.create();
                int width = getWidth();
                int height = getHeight();

                // Gradiente horizontal entre 947257 y DF7622
                Color startColor = new Color(0x94, 0x72, 0x57);
                Color endColor = new Color(0xDF, 0x76, 0x22);
                GradientPaint gp = new GradientPaint(0, 0, startColor, width, 0, endColor);

                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, width, height, 20, 20); // esquinas redondeadas

                g2d.dispose();
            }
        };

        namePanel.setLayout(new BorderLayout());
        namePanel.setPreferredSize(new Dimension(275, 58));
        namePanel.setMaximumSize(new Dimension(275, 58));

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Fredoka SemiBold", Font.PLAIN, 36));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        namePanel.add(nameLabel, BorderLayout.CENTER);
        namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel ageLabel = new JLabel("🎂 " + age);
        ageLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
        //ageLabel.setFont(new Font("Poppins Regular", Font.PLAIN, 20));
        ageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel genderLabel = new JLabel("♀ " + gender);
        genderLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
        //genderLabel.setFont(new Font("Poppins Regular", Font.PLAIN, 20));
        genderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        topInfo.add(namePanel);
        topInfo.add(Box.createVerticalStrut(8));
        topInfo.add(ageLabel);
        topInfo.add(Box.createVerticalStrut(4));
        topInfo.add(genderLabel);

        // Panel descripción
        RoundedPanel aboutPanel = new RoundedPanel(10, Color.WHITE);
        aboutPanel.setLayout(new BorderLayout());
        aboutPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        aboutPanel.setPreferredSize(new Dimension(347, 158));
        aboutPanel.setMaximumSize(new Dimension(347, 158));

        JLabel aboutLabel = new JLabel("<html><b>Sobre mí</b><br>" + about + "</html>");
        aboutLabel.setFont(new Font("Fredoka Regular", Font.PLAIN, 20));

        aboutPanel.add(aboutLabel, BorderLayout.CENTER);

        // Panel ideal
        RoundedPanel idealPanel = new RoundedPanel(10, Color.WHITE);
        idealPanel.setLayout(new BorderLayout());
        idealPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        idealPanel.setPreferredSize(new Dimension(347, 100));
        idealPanel.setMaximumSize(new Dimension(347, 100));


        JLabel idealLabel = new JLabel("<html><b>Ideal para:</b><br>" + ideal + "</html>");
        idealLabel.setFont(new Font("Fredoka Regular", Font.PLAIN, 20));

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