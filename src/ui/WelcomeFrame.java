package ui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class WelcomeFrame extends BaseFrame {
    public WelcomeFrame(String title) {
        super(title);
        initializeComponents();
    }

    private void initializeComponents() {
        // Establecer el layout
        setLayout(new BorderLayout());

        // Panel principal para centrar los elementos
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.decode("#F9F9F9"));

        // Espaciado superior
        mainPanel.add(Box.createVerticalStrut(20));

        // Botón de idioma (arriba a la derecha)
        JButton languageButton = new JButton("Idioma >");
        languageButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setBackground(Color.decode("#F9F9F9"));
        topPanel.add(languageButton);
        add(topPanel, BorderLayout.NORTH);

        // Imagen del gato
        JLabel catImage = new JLabel(new ImageIcon("../../resources/images/logo.png"));
        catImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(catImage);

        // Espaciado entre la imagen y el texto
        mainPanel.add(Box.createVerticalStrut(20));

        // Texto de bienvenida
        JLabel welcomeText = new JLabel("<html><div style='text-align: center'>¡Te damos la bienvenida a Catffee!</div></html>");
        
        Font fredokaFont = null;
        try {
            fredokaFont = Font.createFont(Font.TRUETYPE_FONT, new java.io.File("resources/fonts/Fredoka-SemiBold.ttf"));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        } 

        fredokaFont = fredokaFont.deriveFont(40f); // Ajustar el tamaño de la fuente
        
        welcomeText.setFont(fredokaFont);
        welcomeText.setPreferredSize(new Dimension(380, 145));
        mainPanel.add(welcomeText);

        // Espaciado entre el texto de bienvenida y el texto descriptivo
        mainPanel.add(Box.createVerticalStrut(10));

        // Texto descriptivo
        JLabel descriptionText = new JLabel("<html><div style='text-align: center'>Relájate, disfruta de un buen café y pasa un rato con nuestros adorables gatos. ¡Haz clic para empezar!</div></html>");
        descriptionText.setFont(new Font("Arial", Font.PLAIN, 14));
        descriptionText.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(descriptionText);

        // Espaciado entre el texto descriptivo y el botón
        mainPanel.add(Box.createVerticalStrut(20));

        // Botón de comenzar
        JButton startButton = new JButton("¡Comenzar!");
        startButton.setBackground(Color.decode("#C67C4E")); // Color marrón
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(startButton);

        // Agregar el panel principal al centro de la ventana
        add(mainPanel, BorderLayout.CENTER);
    }
}