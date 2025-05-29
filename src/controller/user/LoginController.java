package controller.user;

import ui.login.LoginFrame;
import utils.UserStorage;
import controller.navigation.NavigationHost;

/**
 * Controlador para la lógica de inicio de sesión en Catffee.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
public class LoginController {
    private final LoginFrame loginFrame;
    private final NavigationHost navigationHost;
    private final UserStorage userStorage;

    public LoginController(LoginFrame loginFrame, NavigationHost navigationHost) {
        this.loginFrame = loginFrame;
        this.navigationHost = navigationHost;
        this.userStorage = UserStorage.getInstance();
    }

    public void intentarLogin() {
        String email = loginFrame.emailField.getText().trim();
        String password = new String(loginFrame.passwordField.getPassword());

        if (userStorage.isValidUser(email, password)) {
            loginFrame.errorLabel.setVisible(false);
            navigationHost.showMainFrame();
        } else {
            loginFrame.errorLabel.setText("El correo y/o la contraseña son incorrectos");
            loginFrame.errorLabel.setVisible(true);
        }
    }

    public void irARegistro() {
        navigationHost.showRegisterFrame();
    }
} 