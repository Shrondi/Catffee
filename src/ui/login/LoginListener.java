package ui.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controller.user.LoginController;

/**
 * Listener para eventos de la pantalla de login en Catffee.
 * Gestiona acciones de usuario en el formulario de inicio de sesión.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
public class LoginListener extends MouseAdapter implements ActionListener {

    private final LoginController controller;

    public LoginListener(LoginController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.intentarLogin();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        controller.irARegistro();
    }
}
