package ui.login;

import javax.swing.*;

import java.awt.*;

import components.field.*;
import components.button.RoundedButton;
import ui.BaseFrame;

public class LoginFrame extends BaseFrame {

    private final Box contentBox;
    private final JLabel errorLabel;

    private RoundedTextField emailField;
    private RoundedPasswordField passwordField;
    private RoundedButton loginButton;

    public LoginFrame(String title) {
        super(title);

        contentBox = Box.createVerticalBox();

        addTitle();
        addSpacing(16);

        addSubtitle();
        addSpacing(33);

        addEmailField();
        addSpacing(25);

        addPasswordField();
        addSpacing(9);

        errorLabel = createErrorMessageLabel("El correo y/o la contraseña son incorrectos");
        errorLabel.setVisible(false);
        contentBox.add(errorLabel);
        addSpacing(25);

        addLoginButton();
        addSpacing(31);

        addRegisterLink();

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

    public void showErrorMessage(boolean show) {
        errorLabel.setVisible(show);
    }

    public RoundedTextField getEmailField() {
        return emailField;
    }

    public RoundedPasswordField getPasswordField() {
        return passwordField;
    }

    public RoundedButton getLoginButton() {
        return loginButton;
    }

    private void addTitle() {
        JLabel titleLabel = new JLabel("Bienvenido de nuevo 🐾");
        titleLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 24));
        titleLabel.setForeground(Color.decode("#0C1421"));
        contentBox.add(titleLabel);
    }

    private void addSubtitle() {
        JLabel subtitle = new JLabel("<html><div>Introduce tus credenciales para iniciar sesión o regístrate si aún no tienes cuenta</div></html>");
        subtitle.setFont(new Font("Poppins Regular", Font.PLAIN, 15));
        subtitle.setForeground(Color.decode("#313957"));
        subtitle.setPreferredSize(new Dimension(342, 48));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentBox.add(subtitle);
    }

    private void addEmailField() {
        JLabel emailLabel = new JLabel("Correo");
        emailLabel.setFont(new Font("Roboto Regular", Font.PLAIN, 14));
        emailLabel.setForeground(Color.decode("#0C1421"));
        emailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        emailField = new RoundedTextField(18);
        emailField.setPlaceholder("correo@email.com");
        emailField.setBackground(Color.decode("#F3F7FB"));
        emailField.setPreferredSize(new Dimension(342, 42));
        emailField.setBorderColor(Color.decode("#D4D7E3"));
        emailField.setBorderWidth(2.0f);

        contentBox.add(emailLabel);
        contentBox.add(emailField);
    }

    private void addPasswordField() {
        JLabel passLabel = new JLabel("Contraseña");
        passLabel.setFont(new Font("Roboto Regular", Font.PLAIN, 14));
        passLabel.setForeground(Color.decode("#0C1421"));
        passLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        passwordField = new RoundedPasswordField(18);
        passwordField.setPlaceholder("Contraseña");
        passwordField.setBackground(Color.decode("#F3F7FB"));
        passwordField.setPreferredSize(new Dimension(342, 42));
        passwordField.setBorderColor(Color.decode("#D4D7E3"));
        passwordField.setBorderWidth(2.0f);

        contentBox.add(passLabel);
        contentBox.add(passwordField);
    }

    private void addLoginButton() {
        loginButton = new RoundedButton("Iniciar sesión", 20);
        loginButton.setBackground(Color.decode("#313131"));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Roboto Regular", Font.PLAIN, 14));
        loginButton.setPreferredSize(new Dimension(350, 44));
        loginButton.setMaximumSize(new Dimension(342, 44));

        contentBox.add(loginButton);
    }

    private JLabel createErrorMessageLabel(String message) {
        JLabel label = new JLabel(message);
        label.setFont(new Font("Roboto Regular", Font.PLAIN, 13));
        label.setForeground(Color.RED);
        label.setPreferredSize(new Dimension(254, 21));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private void addRegisterLink() {
        JLabel registerLabel = new JLabel("<html>¿No tienes ninguna cuenta? <a href='#'>Registrarse</a></html>");
        registerLabel.setFont(new Font("Roboto Regular", Font.PLAIN, 16));
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        contentBox.add(registerLabel);
    }

    private void addSpacing(int height) {
        contentBox.add(Box.createRigidArea(new Dimension(0, height)));
    }
}
