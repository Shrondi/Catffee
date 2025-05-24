package ui.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.home.HomeFrame;
import utils.UserStorage;

public class LoginListener implements ActionListener {

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

            // Avanza al siguiente frame
            HomeFrame nextFrame = new HomeFrame("Bienvenido");
            nextFrame.setVisible(true);

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
}
