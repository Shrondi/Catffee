package ui.welcome;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.login.LoginFrame;
import ui.login.LoginListener;
import utils.UserStorage;

class WelcomeListener implements ActionListener {

    private final WelcomeFrame welcomeFrame;
    private final UserStorage userStorage;

    WelcomeListener(WelcomeFrame welcomeFrame) {
        this.welcomeFrame = welcomeFrame;
        this.userStorage = UserStorage.getInstance();
    }

    // Este método se llama automáticamente cuando se pulsa el botón
    @Override
    public void actionPerformed(ActionEvent e) {
        LoginFrame login = new LoginFrame("Iniciar sesión");
        login.setVisible(true);

        login.getLoginButton().addActionListener(
            new LoginListener(login)
        );

        welcomeFrame.setVisible(false);
        welcomeFrame.dispose(); // Liberar recursos
    }
}
