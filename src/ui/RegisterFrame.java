package ui;

import components.RoundedButton;
import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends BaseFrame {

    public RegisterFrame(String title) {
        super(title);
        setLayout(new BorderLayout());
        add(buildMainPanel(), BorderLayout.CENTER);
    }

    private JPanel buildMainPanel() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        
        container.add(buildTopBar());
        container.add(buildBackButton());
        container.add(buildFormPanel());
    
        return container;
    } 

    private JPanel buildTopBar() {
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.decode("#252424"));
        topPanel.setPreferredSize(new Dimension(413, 85));
        topPanel.setMaximumSize(new Dimension(413, 85));
        topPanel.setLayout(new BorderLayout());
    
        JLabel titleLabel = new JLabel("Registro");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 30));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(23, 31, 23, 213));
    
        topPanel.add(titleLabel, BorderLayout.CENTER);
        return topPanel;
    }

    private JPanel buildBackButton() {
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setBorder(BorderFactory.createEmptyBorder(17, 14, 0, 363));
        backPanel.setMaximumSize(new Dimension(412, 50));
    
        // Cargar y escalar el icono
        ImageIcon originalIcon = new ImageIcon("resources/images/back_icon.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
    
        JButton backButton = new JButton(scaledIcon);
        backButton.setContentAreaFilled(false); // Fondo transparente
        backButton.setBorderPainted(false);     // Sin borde visible
        backButton.setFocusPainted(false);      // Sin borde de selección
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setPreferredSize(new Dimension(35, 35)); // Tamaño máximo
        backButton.setMaximumSize(new Dimension(35, 35));   // Tamaño máximo
    
        backPanel.add(backButton);
        return backPanel;
    }

    private JPanel buildFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    
        panel.add(centeredLabel(new ImageIcon("resources/images/profile_placeholder.png"), 155, 151));
        panel.add(Box.createVerticalStrut(20));
        panel.add(centeredText("Nombre completo", new Font("Fredoka SemiBold", Font.PLAIN, 20)));
        panel.add(Box.createVerticalStrut(10));
        panel.add(centeredText("@Usuario", new Font("Fredoka Regular", Font.PLAIN, 16), new Color(105, 105, 105)));
    
        panel.add(Box.createVerticalStrut(40)); // Espacio antes del primer grupo
    
        panel.add(createLabeledField("Usuario", false));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createLabeledField("Nombre completo", false));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createLabeledField("Correo", false));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createLabeledField("Contraseña", true));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createLabeledField("Repetir contraseña", true));
    
        panel.add(Box.createVerticalStrut(20)); // Espacio antes del botón
    
        //aqui ta el boton
        RoundedButton registerButton = new RoundedButton("Registrarse", 16);
        registerButton.setBackground(Color.decode("#313131"));
        registerButton.setForeground(Color.WHITE);
        registerButton.setPreferredSize(new Dimension(342, 44));
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(registerButton);
    
        panel.add(Box.createVerticalStrut(30));
    
        return panel;
    }

    private JPanel createLabeledField(String labelText, boolean isPassword) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
        fieldPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldPanel.setMaximumSize(new Dimension(342, 70)); // alto total del grupo etiqueta + campo
    
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Fredoka SemiBold", Font.PLAIN, 14));
        label.setForeground(Color.BLACK);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
    
        JComponent field;
        if (isPassword) {
            field = new JPasswordField();
        } else {
            field = new JTextField();
        }
    
        field.setPreferredSize(new Dimension(342, 42));
        field.setMaximumSize(new Dimension(342, 42));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
    
        fieldPanel.add(label);
        fieldPanel.add(Box.createVerticalStrut(5));
        fieldPanel.add(field);
    
        return fieldPanel;
    }

    private JLabel centeredLabel(ImageIcon icon, int width, int height) {
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(img));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JLabel centeredText(String text, Font font) {
        return centeredText(text, font, Color.BLACK);
    }

    private JLabel centeredText(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }
}