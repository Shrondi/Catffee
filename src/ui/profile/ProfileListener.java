package ui.profile;

import controller.profile.ProfileController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileListener implements ActionListener {
    private final ProfileController controller;
    private final String langCode;

    public ProfileListener(ProfileController controller, String langCode) {
        this.controller = controller;
        this.langCode = langCode;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.cambiarIdioma(langCode);
    }
}
