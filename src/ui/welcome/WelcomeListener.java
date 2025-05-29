package ui.welcome;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.user.WelcomeController;

class WelcomeListener implements ActionListener {

    private final WelcomeController controller;

    WelcomeListener(WelcomeController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.irALogin();
    }
}
