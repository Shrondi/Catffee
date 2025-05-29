package ui.register;

import components.button.RoundedButton;
import components.field.RoundedPasswordField;
import components.field.RoundedTextField;
import javax.swing.*;
import java.awt.*;
import ui.BaseFrame;
import controller.navigation.NavigationHost;
import controller.user.RegisterController;

public class RegisterFrame extends BaseFrame {

    // Campos package-private
    public RoundedTextField usuarioField;
    public JLabel usuarioLabelHeader;

    public RoundedTextField nombreCompletoField;
    public JLabel nombreLabelHeader;

    public RoundedTextField correoField;
    public RoundedPasswordField passwordField;
    public RoundedPasswordField repeatPasswordField;

    RoundedButton registerButton;
    JButton backButton;
    JLabel avatarLabel;

    private final NavigationHost navigationHost;

    public RegisterFrame(String title, NavigationHost navigationHost) {
        super(title);
        this.navigationHost = navigationHost;

        // Inicializar campos
        usuarioField = new RoundedTextField(20);
        nombreCompletoField = new RoundedTextField(20);
        correoField = new RoundedTextField(20);
        passwordField = new RoundedPasswordField(20);
        repeatPasswordField = new RoundedPasswordField(20);

        // Configurar labels de encabezado
        usuarioLabelHeader = new JLabel("@Usuario");
        nombreLabelHeader = new JLabel("Nombre completo");

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(topTitle());
        container.add(backButton());
        container.add(formPanel());

        add(container);

        // Listener para actualizar etiquetas en tiempo real
        RegisterController controller = new RegisterController(this, navigationHost);
        RegisterListener listeners = new RegisterListener(controller, this);
        listeners.addTextListeners(usuarioField, usuarioLabelHeader, nombreCompletoField, nombreLabelHeader);

        // Listener para click en avatar
        avatarLabel.addMouseListener(listeners);

        // Listener para botón registrar
        registerButton.addActionListener(listeners);

        // Listener para botón de volver hacia atrás
        backButton.addActionListener(listeners);
    }

    private JPanel topTitle() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.decode("#252424"));
        topPanel.setPreferredSize(new Dimension(413, 85));
        topPanel.setMaximumSize(new Dimension(413, 85));

        JLabel titleLabel = new JLabel("Registro");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 30));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(23, 31, 23, 213));

        topPanel.add(titleLabel, BorderLayout.CENTER);
        return topPanel;
    }

    private JPanel backButton() {
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setBorder(BorderFactory.createEmptyBorder(17, 14, 0, 363));
        backPanel.setMaximumSize(new Dimension(412, 50));

        ImageIcon icon = new ImageIcon("resources/images/ui/back_icon.png");
        Image scaled = icon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        backButton = new JButton(new ImageIcon(scaled));
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setPreferredSize(new Dimension(35, 35));
        backButton.setMaximumSize(new Dimension(35, 35));

        backPanel.add(backButton);
        return backPanel;
    }

    private JPanel formPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(headerInfo());
        panel.add(Box.createVerticalStrut(40));

        panel.add(createLabeledField("Usuario", usuarioField));
        panel.add(Box.createVerticalStrut(10));

        panel.add(createLabeledField("Nombre completo", nombreCompletoField));
        panel.add(Box.createVerticalStrut(10));

        panel.add(createLabeledField("Correo", correoField));
        panel.add(Box.createVerticalStrut(10));

        panel.add(createLabeledField("Contraseña", passwordField));
        panel.add(Box.createVerticalStrut(10));

        panel.add(createLabeledField("Repetir contraseña", repeatPasswordField));
        panel.add(Box.createVerticalStrut(20));

        panel.add(registerButton());
        panel.add(Box.createVerticalStrut(30));

        return panel;
    }

    private JPanel headerInfo() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        ImageIcon icon = new ImageIcon("resources/images/ui/profile_placeholder.png");
        Image scaled = icon.getImage().getScaledInstance(155, 151, Image.SCALE_SMOOTH);
        avatarLabel = new JLabel(new ImageIcon(scaled));
        avatarLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(avatarLabel);
        header.add(Box.createVerticalStrut(20));

        nombreLabelHeader.setFont(new Font("Fredoka SemiBold", Font.PLAIN, 20));
        nombreLabelHeader.setAlignmentX(Component.CENTER_ALIGNMENT);

        usuarioLabelHeader.setFont(new Font("Fredoka Regular", Font.PLAIN, 16));
        usuarioLabelHeader.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(nombreLabelHeader);
        header.add(Box.createVerticalStrut(10));
        header.add(usuarioLabelHeader);

        return header;
    }

    private JPanel createLabeledField(String labelText, JComponent field) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
        fieldPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldPanel.setMaximumSize(new Dimension(342, 70));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Roboto Regular", Font.PLAIN, 14));
        label.setForeground(Color.BLACK);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Configurar campo visualmente
        if (field instanceof RoundedTextField) {
            ((RoundedTextField) field).setBorderColor(Color.decode("#D4D7E3"));
            ((RoundedTextField) field).setBorderWidth(2.0f);
            ((RoundedTextField) field).setPlaceholder(labelText);
        } else if (field instanceof RoundedPasswordField) {
            ((RoundedPasswordField) field).setBorderColor(Color.decode("#D4D7E3"));
            ((RoundedPasswordField) field).setBorderWidth(2.0f);
            ((RoundedPasswordField) field).setPlaceholder(labelText);
        }

        field.setBackground(Color.decode("#F3F7FB"));
        field.setPreferredSize(new Dimension(342, 42));
        field.setMaximumSize(new Dimension(342, 42));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);

        fieldPanel.add(label);
        fieldPanel.add(field);

        return fieldPanel;
    }

    private RoundedButton registerButton() {
        registerButton = new RoundedButton("Registrarse", 16);
        registerButton.setFont(new Font("Roboto Regular", Font.PLAIN, 14));
        registerButton.setBackground(Color.decode("#313131"));
        registerButton.setForeground(Color.WHITE);
        registerButton.setMaximumSize(new Dimension(342, 44));
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        return registerButton;
    }

    // Método para actualizar avatar (se puede usar desde listener)
    public void updateAvatarImage(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaled = icon.getImage().getScaledInstance(155, 151, Image.SCALE_SMOOTH);
        avatarLabel.setIcon(new ImageIcon(scaled));
    }
}
