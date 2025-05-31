package ui.profile;

import controller.profile.ProfileController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import ui.valoration.ValorationFrame;

public class ProfileListener implements ActionListener, MouseListener {
    private final ProfileController controller;
    private final String langCode;
    private final String actionType; // "idioma" o "logout"
    private final JComponent parentOption;

    public ProfileListener(ProfileController controller, String langCode, String actionType, JComponent parentOption) {
        this.controller = controller;
        this.langCode = langCode;
        this.actionType = actionType;
        this.parentOption = parentOption;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.cambiarIdioma(langCode);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if ("logout".equals(actionType)) {
            controller.logout();
        } else if ("idioma".equals(actionType) && parentOption != null) {
            ProfilePanel panel = (ProfilePanel) javax.swing.SwingUtilities.getAncestorOfClass(ProfilePanel.class, parentOption);
            if (panel != null) {
                panel.mostrarDialogoIdioma(parentOption);
            }
        } else if ("rate".equals(actionType)) {
            java.awt.Frame parentFrame = null;
            if (parentOption != null) {
                parentFrame = (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(parentOption);
            }
            new ValorationFrame(parentFrame).setVisible(true);
        }
    }
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
