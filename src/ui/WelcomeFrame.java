package ui;

import javax.swing.*;

import components.RoundedButton;

import java.awt.*;

public class WelcomeFrame extends BaseFrame {
    public WelcomeFrame(String title) {
        super(title);

        setLayout(new BorderLayout()); //Layout de la ventana

        JPanel mainPanel = new JPanel(); // Panel principal
        mainPanel.setBackground(null);
        mainPanel.add(Box.createVerticalStrut(20));

        addLanguageButton();

        addCatImage(mainPanel);
        addWelcomeText(mainPanel);
        addSeparator(mainPanel);
        addDescriptionText(mainPanel);
        
        addStartButton();

        add(mainPanel, BorderLayout.CENTER);
    }

    private void addLanguageButton() {
        RoundedButton languageButton = new RoundedButton("Idioma >", 16);
        languageButton.setBackground(Color.decode("#FFFFFF"));
        languageButton.setForeground(Color.decode("#6B6B6B"));
        languageButton.setBorderPainted(false);
        languageButton.setPreferredSize(new Dimension(99, 20));
        languageButton.setFont(new Font("Sora Regular", Font.PLAIN, 12));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 10)); // Margen superior
        topPanel.setBackground(Color.decode("#F9F9F9"));
        topPanel.add(languageButton);
        add(topPanel, BorderLayout.NORTH);
    }

    private void addCatImage(JPanel mainPanel) {
        ImageIcon logo = new ImageIcon("resources/images/logo.png");
        logo = new ImageIcon(logo.getImage().getScaledInstance(228, 228, Image.SCALE_SMOOTH));
        JLabel catImage = new JLabel(logo);
        catImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(catImage);
        mainPanel.add(Box.createVerticalStrut(20)); // Espaciado
    }

    private void addWelcomeText(JPanel mainPanel) {
        JLabel welcomeText = new JLabel("<html><div style='text-align: center'>¡Te damos la bienvenida a Catffee!</div></html>");
        welcomeText.setFont(new Font("Fredoka SemiBold", Font.PLAIN, 40));
        welcomeText.setPreferredSize(new Dimension(380, 145));
        mainPanel.add(welcomeText);
        mainPanel.add(Box.createVerticalStrut(25)); // Espaciado
    }

    private void addSeparator(JPanel mainPanel) {
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setForeground(Color.BLACK);
        separator.setPreferredSize(new Dimension(319, 2));
        mainPanel.add(separator);
        mainPanel.add(Box.createVerticalStrut(24)); // Espaciado
    }

    private void addDescriptionText(JPanel mainPanel) {
        JLabel descriptionText = new JLabel("<html><div style='text-align: center'>Relájate, disfruta de un buen café y pasa un rato con nuestros adorables gatos. ¡Haz clic para empezar!</div></html>");
        descriptionText.setFont(new Font("Fredoka Regular", Font.PLAIN, 24));
        descriptionText.setPreferredSize(new Dimension(300, 146));
        descriptionText.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(descriptionText);
        mainPanel.add(Box.createVerticalStrut(134)); // Espaciado
    }

    private void addStartButton() {

        RoundedButton startButton = new RoundedButton("¡Comenzar!", 16);
        startButton.setBackground(Color.decode("#C67C4E"));
        startButton.setForeground(Color.WHITE);
        startButton.setBorderPainted(false);
        startButton.setPreferredSize(new Dimension(327, 56));
        startButton.setFont(new Font("Sora Semibold", Font.PLAIN, 16));

        JPanel buttonPanel = new JPanel(); // Panel para contener el botón
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(startButton);
        buttonPanel.setBackground(null);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0)); // Margen inferior

        add(buttonPanel, BorderLayout.SOUTH); //Añadir boton a la ventana
    }
}