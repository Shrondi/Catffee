package ui;

import components.NavigationBar;
import components.GradientPanel;
import components.RoundedButton;
import components.RoundedPanel;

import javax.swing.*;
import java.awt.*;

public class CatsFrame extends BaseFrame {

    public CatsFrame(String title) {
        super(title);

        GradientPanel background = new GradientPanel(new Color(0xBD6E36), new Color(0xCDCDCD), true);
        background.setLayout(new BorderLayout());
        setContentPane(background);

        background.add(buildTopBar(), BorderLayout.NORTH);
        background.add(buildCatsScrollPanel(), BorderLayout.CENTER);

        add(new NavigationBar("Gatos"), BorderLayout.SOUTH);
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

        // Descripción
        JLabel description = new JLabel("<html>Estos gatetes son más que expertos<br>" +
                "en siestas y miradas adorables.<br>" +
                "¡Desliza, conócelos y quizás uno se<br>" +
                "robe tu corazón (y tu café)!</html>");

        description.setFont(new Font("Fredoka Regular", Font.PLAIN, 20));

        RoundedPanel descriptionPanel = new RoundedPanel(15, Color.WHITE);
        descriptionPanel.setMaximumSize(new Dimension(378, 119));
        descriptionPanel.add(description, BorderLayout.CENTER);
        descriptionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        descriptionPanel.setBorder(Color.BLACK, 1);

        contentPanel.add(descriptionPanel);

        String[] cats = { "Alfil", "Cherry", "Abi", "Flush", "Gulliver", "Membrillo", "Mazinger", "Sylvestre" };
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

        // GridBagLayout con desplazamiento en columna derecha
        JPanel gridPanel = new JPanel(new GridBagLayout());
        gridPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;

        for (int i = 0; i < cats.length; i++) {
            JPanel card = buildCatCard(images[i], cats[i]);

            gbc.gridx = i % 2; // Columna 0 o 1
            gbc.gridy = i / 2;

            if (gbc.gridx == 0) {
                // Columna izquierda: 15 arriba respecto a descripción y 15 entre cards
                gbc.insets = (gbc.gridy == 0)
                        ? new Insets(15, 0, 15, 15) // Primer card izquierda: 15 arriba
                        : new Insets(-65, 0, 15, 15); // Siguientes: 0 arriba
            } else {
                // Columna derecha: 80 arriba respecto a descripción, 15 entre cards
                gbc.insets = (gbc.gridy == 0)
                        ? new Insets(80, 15, 15, 0) // Primer card derecha: 80 arriba
                        : new Insets(0, 15, 15, 0); // Siguientes: 0 arriba
            }

            gridPanel.add(card, gbc);
        }

        contentPanel.add(gridPanel);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.getVerticalScrollBar().setOpaque(false);

        return scrollPane;
    }

    private JPanel buildCatCard(String imagePath, String name) {
        RoundedPanel card = new RoundedPanel(16);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(180, 240));
        card.setMaximumSize(new Dimension(180, 240));
        card.add(Box.createVerticalStrut(10));

        ImageIcon icon = new ImageIcon(imagePath);
        Image scaled = icon.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(scaled));
        imgLabel.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 22));
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 24));
        nameLabel.setForeground(Color.decode("#000000"));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 22));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        RoundedButton adoptButton = new RoundedButton("Adóptame", 20);
        adoptButton.setBackground(Color.decode("#C67C4E"));
        adoptButton.setForeground(Color.WHITE);
        adoptButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        adoptButton.setPreferredSize(new Dimension(113, 22));
        adoptButton.setMaximumSize(new Dimension(113, 22));
        adoptButton.setFont(new Font("Sora Semibold", Font.PLAIN, 12));

        card.add(imgLabel);

        card.add(nameLabel);
        card.add(adoptButton);

        return card;
    }
}