package ui.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controller.LoginController;

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
