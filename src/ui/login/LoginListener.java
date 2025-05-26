package ui.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.MainFrame;
import ui.register.RegisterFrame;
import utils.UserStorage;

public class LoginListener extends MouseAdapter implements ActionListener {

    private final LoginFrame loginFrame;
    private final UserStorage userStorage;

    public LoginListener(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
        this.userStorage = UserStorage.getInstance();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String email = loginFrame.emailField.getText().trim();
        String password = new String(loginFrame.passwordField.getPassword());

        if (userStorage.isValidUser(email, password)) {
            loginFrame.errorLabel.setVisible(false);

            // Crear y mostrar MainFrame (nuevo sistema)
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);

            loginFrame.setVisible(false);
            loginFrame.dispose();

            System.out.println("Login exitoso para: " + email);
        } else {
            loginFrame.errorLabel.setText("El correo y/o la contraseña son incorrectos");
            loginFrame.errorLabel.setVisible(true);

            System.out.println("Login fallido para: " + email);
            // No deshabilitar el botón para permitir más intentos
        }
    }

     // Se ejecuta al hacer clic sobre el JLabel
    @Override
    public void mouseClicked(MouseEvent e) {
        RegisterFrame register = new RegisterFrame("Registrarse");
        register.setVisible(true);

        loginFrame.setVisible(false);
        loginFrame.dispose();
    }
}
