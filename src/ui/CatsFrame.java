package ui;

import components.GradientPanel;
import components.RoundedButton;
import components.RoundedPanel;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class CatsFrame extends JFrame {

    public CatsFrame() {
        setTitle("Gatos");
        setSize(412, 917);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        GradientPanel background = new GradientPanel(new Color(0xBD6E36), new Color(0xCDCDCD), true);
        background.setLayout(new BorderLayout());
        setContentPane(background);

        background.add(buildTopBar(), BorderLayout.NORTH);
        background.add(buildCatsScrollPanel(), BorderLayout.CENTER);
    }

    private JPanel buildTopBar() {
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.decode("#252424"));
        topPanel.setPreferredSize(new Dimension(413, 85));
        topPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Gatos");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 30));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(23, 30, 23, 288));

        topPanel.add(titleLabel, BorderLayout.WEST);

        return topPanel;
    }

    private JScrollPane buildCatsScrollPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextArea description = new JTextArea("Estos gatetes son más que expertos\n" +
                "en siestas y miradas adorables.\n" +
                "¡Desliza, conócelos y quizás uno se\n" +
                "robe tu corazón (y tu café)!");

        description.setFont(new Font("Fredoka Regular", Font.PLAIN, 20));
        description.setEditable(false);
        description.setOpaque(true);
        description.setBackground(Color.WHITE);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setBorder(BorderFactory.createEmptyBorder(26,17,18,0));
        description.setAlignmentX(Component.CENTER_ALIGNMENT);
        description.setMaximumSize(new Dimension(378, 119));

        contentPanel.add(description);
        contentPanel.add(Box.createVerticalStrut(20));

        String[] cats = {"Alfil", "Cherry", "Abi", "Flush", "Gulliver", "Membrillo", "Mazinger", "Sylvestre"};
        String[] images = {
                "resources/images/alfil.png",
                "resources/images/cherry.png",
                "resources/images/abi.png",
                "resources/images/flush.png",
                "resources/images/gulliver.png",
                "resources/images/membrillo.png",
                "resources/images/mazinger.png",
                "resources/images/sylvestre.png"
        };

        JPanel gridPanel = new JPanel(new GridLayout(0, 2, 15, 15));
        gridPanel.setOpaque(false);

        for (int i = 0; i < cats.length; i++) {
            gridPanel.add(buildCatCard(images[i], cats[i]));
        }

        contentPanel.add(gridPanel);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.getVerticalScrollBar().setOpaque(false);

        return scrollPane;
    }

    private JPanel buildCatCard(String imagePath, String name) {
        RoundedPanel card = new RoundedPanel(16);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(160, 220));
        card.setMaximumSize(new Dimension(160, 220));
        card.add(Box.createVerticalStrut(10)); // Espaciado


        ImageIcon icon = new ImageIcon(imagePath);
        Image scaled = icon.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(scaled));
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imgLabel.add(Box.createVerticalStrut(8)); // Espaciado

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 24));
        nameLabel.setForeground(Color.decode("#000000"));
        //nameLabel.setBorder(BorderFactory.createEmptyBorder(153, 55, 50, 55));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.add(Box.createVerticalStrut(8)); // Espaciado

        RoundedButton adoptButton = new RoundedButton("Adóptame", 16);
        adoptButton.setBackground(Color.decode("#C67C4E"));
        //adoptButton.setBorder(BorderFactory.createEmptyBorder(177, 23, 14, 177));
        adoptButton.setForeground(Color.WHITE);
        adoptButton.setBorderPainted(false);
        adoptButton.setPreferredSize(new Dimension(113, 22));
        adoptButton.setMaximumSize(new Dimension(113, 22));
        adoptButton.setFont(new Font("Sora Semibold", Font.PLAIN, 16));
        adoptButton.add(Box.createVerticalStrut(14)); // Espaciado

        card.add(imgLabel);
        card.add(nameLabel);
        card.add(adoptButton);

        return card;
    }
}