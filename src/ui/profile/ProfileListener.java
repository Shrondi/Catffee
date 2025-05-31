package ui.profile;

import controller.profile.ProfileController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class ProfileListener implements ActionListener, MouseListener {
    private final ProfileController controller;
    private final String langCode;
    private final String actionType; // "idioma" o "logout"
    private final Runnable onIdioma;

    public ProfileListener(ProfileController controller, String langCode, String actionType, Runnable onIdioma) {
        this.controller = controller;
        this.langCode = langCode;
        this.actionType = actionType;
        this.onIdioma = onIdioma;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.cambiarIdioma(langCode);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if ("logout".equals(actionType)) {
            controller.logout();
        } else if (onIdioma != null) {
            onIdioma.run();
        }
    }
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
