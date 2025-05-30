package ui.welcome;

import javax.swing.*;
import java.awt.*;
import components.button.RoundedButton;
import ui.BaseFrame;
import utils.I18n;
import controller.navigation.NavigationHost;
import controller.user.WelcomeController;

/**
 * Ventana de bienvenida para Catffee. Permite seleccionar idioma y comenzar la experiencia.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
/**
 * Frame de bienvenida de la aplicación.
 */

public class WelcomeFrame extends BaseFrame {

    RoundedButton startButton;
    private final WelcomeController controller;
    private final NavigationHost navigationHost;
    private JComboBox<LangOption> languageSelector;

    public WelcomeFrame(String title, NavigationHost navigationHost) {
        super(title);
        this.navigationHost = navigationHost;
        this.controller = new WelcomeController(this, navigationHost);

        setLayout(new BorderLayout()); //Layout de la ventana

        JPanel mainPanel = new JPanel(); // Panel principal
        mainPanel.setBackground(null);
        mainPanel.add(Box.createVerticalStrut(20));

        add(addLanguageButton(), BorderLayout.NORTH);

        mainPanel.add(addCatImage(), BorderLayout.CENTER); // Añadir imagen del gato
        mainPanel.add(Box.createVerticalStrut(20)); // Espaciado
        
        
        mainPanel.add(addWelcomeText(), BorderLayout.CENTER); // Añadir texto de bienvenida
        mainPanel.add(Box.createVerticalStrut(25)); // Espaciado

        mainPanel.add(addSeparator(), BorderLayout.CENTER); // Añadir separador
        mainPanel.add(Box.createVerticalStrut(24)); 

        mainPanel.add(addDescriptionText(), BorderLayout.CENTER); // Añadir texto de descripción
        mainPanel.add(Box.createVerticalStrut(134));
        
        add(addStartButton(), BorderLayout.SOUTH);

        WelcomeListener listeners = new WelcomeListener(controller);
        startButton.addActionListener(listeners);

        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel addLanguageButton() {
        LangOption[] languages = {
            new LangOption("es", I18n.t("profile_spanish")),
            new LangOption("en", I18n.t("profile_english"))
        };
        languageSelector = new JComboBox<>(languages);
        String currentLang = I18n.getCurrentLocale().getLanguage();
        languageSelector.setSelectedItem(currentLang.equals("es") ? languages[0] : languages[1]);
        languageSelector.setPreferredSize(new Dimension(100, 25));
        languageSelector.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 12));
        languageSelector.addActionListener(new WelcomeListener(controller));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 10)); // Margen superior
        topPanel.setBackground(Color.decode("#F9F9F9"));
        topPanel.add(languageSelector);

        return topPanel;
    }

    private JLabel addCatImage() {
        ImageIcon logo = new ImageIcon("resources/images/ui/logo.png");
        logo = new ImageIcon(logo.getImage().getScaledInstance(228, 228, Image.SCALE_SMOOTH));
        JLabel catImage = new JLabel(logo);
        catImage.setAlignmentX(Component.CENTER_ALIGNMENT);

        return catImage;
    }

    private JLabel addWelcomeText() {
        JLabel welcomeText = new JLabel("<html><div style='text-align: center'>" + I18n.t("welcome_title") + "</div></html>");
        welcomeText.setFont(new Font("Fredoka SemiBold", Font.PLAIN, 40));
        welcomeText.setPreferredSize(new Dimension(380, 145));
        
        return welcomeText;
    }

    private JSeparator addSeparator() {
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setForeground(Color.BLACK);
        separator.setPreferredSize(new Dimension(319, 2));
        
        return separator;
    }

    private JLabel addDescriptionText() {
        JLabel descriptionText = new JLabel("<html><div style='text-align: center'>" + I18n.t("welcome_desc") + "</div></html>");
        descriptionText.setFont(new Font("Fredoka Regular", Font.PLAIN, 24));
        descriptionText.setPreferredSize(new Dimension(300, 146));
        descriptionText.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        return descriptionText; 
    }

    private JPanel addStartButton() {

        startButton = new RoundedButton(I18n.t("welcome_start"), 16);
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
        
        return buttonPanel;
    }
    
    // Clase interna para opciones de idioma
    public static class LangOption {
        String code;
        String label;
        LangOption(String code, String label) {
            this.code = code;
            this.label = label;
        }
        @Override
        public String toString() {
            return label;
        }
    }
}