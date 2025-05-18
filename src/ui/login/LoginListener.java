package ui.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.register.RegisterFrame;
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
        String email = loginFrame.getEmailField().getText().trim();
        String password = new String(loginFrame.getPasswordField().getPassword());

        if (userStorage.isValidUser(email, password)) {
            loginFrame.showErrorMessage(false);

            // Avanza al siguiente frame
            RegisterFrame nextFrame = new RegisterFrame("Bienvenido");
            nextFrame.setVisible(true);

            loginFrame.setVisible(false);
            loginFrame.dispose();

            System.out.println("Login exitoso para: " + email);
        } else {
            loginFrame.showErrorMessage(true);

            System.out.println("Login fallido para: " + email);
            // No deshabilitar el botón para permitir más intentos
        }
    }
}
