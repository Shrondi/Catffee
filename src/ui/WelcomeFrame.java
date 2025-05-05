package ui;

import javax.swing.*;
import java.awt.*;

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
        mainPanel.setBackground(null);

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
        ImageIcon logo = new ImageIcon("resources/images/logo.png");
        logo = new ImageIcon(logo.getImage().getScaledInstance(228, 228, Image.SCALE_SMOOTH));

        JLabel catImage = new JLabel(logo);
        catImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(catImage);

        // Espaciado entre la imagen y el texto
        mainPanel.add(Box.createVerticalStrut(20));

        // Texto de bienvenida
        JLabel welcomeText = new JLabel("<html><div style='text-align: center'>¡Te damos la bienvenida a Catffee!</div></html>");
        
        welcomeText.setFont(new Font("Fredoka SemiBold", Font.PLAIN, 40));
        welcomeText.setPreferredSize(new Dimension(380, 145));
        mainPanel.add(welcomeText);

        // Espaciado entre la imagen y el texto
        mainPanel.add(Box.createVerticalStrut(25));

        // Línea horizontal
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setForeground(Color.BLACK); // Establecer el color de la línea
        separator.setPreferredSize(new Dimension(319, 2)); // Establecer un tamaño visible
        mainPanel.add(separator);

        // Espaciado entre el texto de bienvenida y el texto descriptivo
        mainPanel.add(Box.createVerticalStrut(49));

        // Texto descriptivo
        JLabel descriptionText = new JLabel("<html><div style='text-align: center'>Relájate, disfruta de un buen café y pasa un rato con nuestros adorables gatos. ¡Haz clic para empezar!</div></html>");

        descriptionText.setFont(new Font("Fredoka Regular", Font.PLAIN, 24));
        descriptionText.setPreferredSize(new Dimension(300, 146));
        descriptionText.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(descriptionText);

        // Espaciado entre el texto descriptivo y el botón
        mainPanel.add(Box.createVerticalStrut(268));

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