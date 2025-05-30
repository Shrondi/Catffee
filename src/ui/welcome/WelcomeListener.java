package ui.welcome;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.user.WelcomeController;
import javax.swing.JComboBox;
import utils.LangOption;

/**
 * Listener para eventos de la pantalla de bienvenida en Catffee.
 * Gestiona acciones de usuario en la pantalla de inicio.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
class WelcomeListener implements ActionListener {

    private final WelcomeController controller;

    WelcomeListener(WelcomeController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JComboBox<?> combo) {
            Object selectedObj = combo.getSelectedItem();
            if (selectedObj instanceof LangOption langOpt) {
                controller.cambiarIdioma(langOpt.code);
            }
        } else {
            controller.irALogin();
        }
    }

    public WelcomeController getController() {
        return controller;
    }
}
