package ui.register;

import components.button.RoundedButton;
import components.field.RoundedPasswordField;
import components.field.RoundedTextField;
import javax.swing.*;
import java.awt.*;
import ui.BaseFrame;
import controller.navigation.NavigationHost;
import controller.user.RegisterController;
import utils.I18n;

/**
 * Ventana de registro de usuario para Catffee.
 * Permite crear una nueva cuenta y seleccionar avatar.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
/**
 * Frame de registro de usuario.
 */
public class RegisterFrame extends BaseFrame {

    
    private RoundedTextField usuarioField;
    private JLabel usuarioLabelHeader;

    private RoundedTextField nombreCompletoField;
    private JLabel nombreLabelHeader;

    private RoundedTextField correoField;
    private RoundedPasswordField passwordField;
    private RoundedPasswordField repeatPasswordField;

    private JLabel usuarioErrorLabel = new JLabel();
    private JLabel nombreCompletoErrorLabel = new JLabel();
    private JLabel correoErrorLabel = new JLabel();
    private JLabel passwordErrorLabel = new JLabel();
    private JLabel repeatPasswordErrorLabel = new JLabel();

    // Campos package-private
    RoundedButton registerButton;
    JButton backButton;
    JLabel avatarLabel;

    private final NavigationHost navigationHost;

    /**
     * Crea la ventana de registro.
     * @param title Título de la ventana
     * @param navigationHost Navegador de pantallas
     */
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
        usuarioLabelHeader = new JLabel(I18n.t("register_placeholder_username"));
        nombreLabelHeader = new JLabel(I18n.t("register_name"));

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

        JLabel titleLabel = new JLabel(I18n.t("register_title"));
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
        panel.add(Box.createVerticalStrut(10));

        panel.add(createLabeledField(I18n.t("register_username"), usuarioField));
        panel.add(Box.createVerticalStrut(10));

        panel.add(createLabeledField(I18n.t("register_name"), nombreCompletoField));
        panel.add(Box.createVerticalStrut(10));

        panel.add(createLabeledField(I18n.t("register_email"), correoField));
        panel.add(Box.createVerticalStrut(10));

        panel.add(createLabeledField(I18n.t("register_password"), passwordField));
        panel.add(Box.createVerticalStrut(10));

        panel.add(createLabeledField(I18n.t("register_repeat"), repeatPasswordField));
        panel.add(Box.createVerticalStrut(20));

        panel.add(registerButton());
        panel.add(Box.createVerticalStrut(20));

        return panel;
    }

    private JPanel headerInfo() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        ImageIcon icon = new ImageIcon("resources/images/ui/profile_placeholder.png");
        Image scaled = icon.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
        avatarLabel = new JLabel(new ImageIcon(scaled));
        avatarLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(avatarLabel);
        header.add(Box.createVerticalStrut(10));

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
        field.setMinimumSize(new Dimension(342, 42));
        field.setPreferredSize(new Dimension(342, 42));
        field.setMaximumSize(new Dimension(342, 42));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel errorLabel = new JLabel();
        errorLabel.setFont(new Font("Roboto Regular", Font.PLAIN, 12));
        errorLabel.setForeground(Color.RED);
        errorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        errorLabel.setVisible(false);

        // Asignar el errorLabel privado correspondiente
        if (field == usuarioField) usuarioErrorLabel = errorLabel;
        if (field == nombreCompletoField) nombreCompletoErrorLabel = errorLabel;
        if (field == correoField) correoErrorLabel = errorLabel;
        if (field == passwordField) passwordErrorLabel = errorLabel;
        if (field == repeatPasswordField) repeatPasswordErrorLabel = errorLabel;

        fieldPanel.add(label);
        fieldPanel.add(field);
        fieldPanel.add(errorLabel);

        return fieldPanel;
    }

    private RoundedButton registerButton() {
        registerButton = new RoundedButton(I18n.t("register_register"), 16);
        registerButton.setFont(new Font("Roboto Regular", Font.PLAIN, 14));
        registerButton.setBackground(Color.decode("#313131"));
        registerButton.setForeground(Color.WHITE);
        registerButton.setMinimumSize(new Dimension(342, 44));
        registerButton.setPreferredSize(new Dimension(342, 44));
        registerButton.setMaximumSize(new Dimension(342, 44));
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        return registerButton;
    }

    /**
     * Actualiza la imagen del avatar.
     * @param imagePath Ruta de la imagen
     */
    public void updateAvatarImage(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaled = icon.getImage().getScaledInstance(155, 151, Image.SCALE_SMOOTH);
        avatarLabel.setIcon(new ImageIcon(scaled));
    }

    public void setUsuarioError(String mensaje) {
        usuarioErrorLabel.setText(mensaje);
        usuarioErrorLabel.setVisible(mensaje != null && !mensaje.isEmpty());
    }
    public void setNombreCompletoError(String mensaje) {
        nombreCompletoErrorLabel.setText(mensaje);
        nombreCompletoErrorLabel.setVisible(mensaje != null && !mensaje.isEmpty());
    }
    public void setCorreoError(String mensaje) {
        correoErrorLabel.setText(mensaje);
        correoErrorLabel.setVisible(mensaje != null && !mensaje.isEmpty());
    }
    public void setPasswordError(String mensaje) {
        passwordErrorLabel.setText(mensaje);
        passwordErrorLabel.setVisible(mensaje != null && !mensaje.isEmpty());
    }
    public void setRepeatPasswordError(String mensaje) {
        repeatPasswordErrorLabel.setText(mensaje);
        repeatPasswordErrorLabel.setVisible(mensaje != null && !mensaje.isEmpty());
    }

    public String getUsuario() {
        return usuarioField.getText().trim();
    }
    public String getNombreCompleto() {
        return nombreCompletoField.getText().trim();
    }
    public String getCorreo() {
        return correoField.getText().trim();
    }
    public String getPassword() {
        return new String(passwordField.getPassword());
    }
    public String getRepeatPassword() {
        return new String(repeatPasswordField.getPassword());
    }
}
