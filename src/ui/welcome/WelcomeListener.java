package ui.welcome;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.login.LoginFrame;

class WelcomeListener implements ActionListener {

    private final WelcomeFrame welcomeFrame;

    WelcomeListener(WelcomeFrame welcomeFrame) {
        this.welcomeFrame = welcomeFrame;
    }

    // Este método se llama automáticamente cuando se pulsa el botón
    @Override
    public void actionPerformed(ActionEvent e) {
        LoginFrame login = new LoginFrame("Iniciar sesión");
        login.setVisible(true);

        welcomeFrame.setVisible(false);
        welcomeFrame.dispose(); // Liberar recursos
    }
}
