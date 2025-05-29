package ui.login;

import javax.swing.*;
import java.awt.*;
import components.field.RoundedPasswordField;
import components.field.RoundedTextField;
import components.button.RoundedButton;
import ui.BaseFrame;
import controller.navigation.NavigationHost;
import controller.user.LoginController;
import utils.I18n;

/**
 * Ventana de inicio de sesión para Catffee.
 * Permite al usuario autenticarse y navegar entre pantallas.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
/**
 * Frame de login de usuario.
 */
public class LoginFrame extends BaseFrame {

    private LoginListener listener;

    private final Box contentBox;

    public JLabel errorLabel;
    public RoundedTextField emailField;
    public RoundedPasswordField passwordField;
    RoundedButton loginButton;
    JLabel registerLabel;

    private final NavigationHost navigationHost;

    /**
     * Crea la ventana de login.
     * @param title Título de la ventana
     * @param navigationHost Navegador de pantallas
     */
    public LoginFrame(String title, NavigationHost navigationHost) {
        super(title);
        this.navigationHost = navigationHost;

        contentBox = Box.createVerticalBox();

        addTitle();
        addSpacing(16);

        addSubtitle();
        addSpacing(33);

        addEmailField();
        addSpacing(25);

        addPasswordField();
        addSpacing(9);

        errorLabel = errorLabel();
        errorLabel.setVisible(false);
        contentBox.add(errorLabel);
        addSpacing(25);

        // Inicializar controlador y listener
        LoginController controller = new LoginController(this, navigationHost);
        listener = new LoginListener(controller);
        addLoginButton();
        loginButton.addActionListener(listener);
        addSpacing(31);

        addRegisterLink();
        registerLabel.addMouseListener(new LoginListener(controller));

        // Centrar Box en medio de la ventana con un JPanel
        JPanel centerPanel = new JPanel();

        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(contentBox);
        centerPanel.add(Box.createVerticalGlue());

        contentBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentBox.setMaximumSize(new Dimension(342, 445));

        add(centerPanel, BorderLayout.CENTER);
    }

    private void addTitle() {
        JLabel titleLabel = new JLabel(I18n.t("login_welcome"));
        titleLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 24));
        titleLabel.setForeground(Color.decode("#0C1421"));
        contentBox.add(titleLabel);
    }

    private void addSubtitle() {
        JLabel subtitle = new JLabel("<html><div>" + I18n.t("login_subtitle") + "</div></html>");
        subtitle.setFont(new Font("Poppins Regular", Font.PLAIN, 15));
        subtitle.setForeground(Color.decode("#313957"));
        subtitle.setPreferredSize(new Dimension(342, 48));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentBox.add(subtitle);
    }

    private void addEmailField() {
        JLabel emailLabel = new JLabel(I18n.t("login_email"));
        emailLabel.setFont(new Font("Roboto Regular", Font.PLAIN, 14));
        emailLabel.setForeground(Color.decode("#0C1421"));
        emailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        emailField = new RoundedTextField(18);
        emailField.setPlaceholder(I18n.t("login_email"));
        emailField.setBackground(Color.decode("#F3F7FB"));
        emailField.setPreferredSize(new Dimension(342, 42));
        emailField.setBorderColor(Color.decode("#D4D7E3"));
        emailField.setBorderWidth(2.0f);

        contentBox.add(emailLabel);
        contentBox.add(emailField);
    }

    private void addPasswordField() {
        JLabel passLabel = new JLabel(I18n.t("login_password"));
        passLabel.setFont(new Font("Roboto Regular", Font.PLAIN, 14));
        passLabel.setForeground(Color.decode("#0C1421"));
        passLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        passwordField = new RoundedPasswordField(18);
        passwordField.setPlaceholder(I18n.t("login_password"));
        passwordField.setBackground(Color.decode("#F3F7FB"));
        passwordField.setPreferredSize(new Dimension(342, 42));
        passwordField.setBorderColor(Color.decode("#D4D7E3"));
        passwordField.setBorderWidth(2.0f);

        contentBox.add(passLabel);
        contentBox.add(passwordField);
    }

    private void addLoginButton() {
        loginButton = new RoundedButton(I18n.t("login_login"), 20);
        loginButton.setBackground(Color.decode("#313131"));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Roboto Regular", Font.PLAIN, 14));
        loginButton.setPreferredSize(new Dimension(350, 44));
        loginButton.setMaximumSize(new Dimension(342, 44));

        contentBox.add(loginButton);
    }

    private JLabel errorLabel() {
        JLabel label = new JLabel();
        label.setFont(new Font("Roboto Regular", Font.PLAIN, 13));
        label.setForeground(Color.RED);
        label.setPreferredSize(new Dimension(254, 21));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private void addRegisterLink() {
        registerLabel = new JLabel("<html>" + I18n.t("login_register") + "</html>");
        registerLabel.setFont(new Font("Roboto Regular", Font.PLAIN, 16));
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        contentBox.add(registerLabel);
    }

    private void addSpacing(int height) {
        contentBox.add(Box.createRigidArea(new Dimension(0, height)));
    }
}
