package ui.register;

import controller.user.RegisterController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;

public class RegisterListener extends MouseAdapter implements ActionListener {

    private final RegisterController controller;
    private final RegisterFrame frame;

    public RegisterListener(RegisterController controller, RegisterFrame frame) {
        this.controller = controller;
        this.frame = frame;
    }

    public void addTextListeners(JTextField usuarioField, JLabel usuarioLabelHeader,
                                 JTextField nombreCompletoField, JLabel nombreLabelHeader) {
        usuarioField.getDocument().addDocumentListener(new UsuarioListener(usuarioField, usuarioLabelHeader));
        nombreCompletoField.getDocument().addDocumentListener(new NombreListener(nombreCompletoField, nombreLabelHeader));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == frame.avatarLabel) {
            controller.seleccionarAvatar();
        } else if (e.getSource() == frame.backButton) {
            controller.volverALogin();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frame.registerButton) {
            controller.registrarUsuario();
        } else if (e.getSource() == frame.backButton) {
            controller.volverALogin();
        }
    }

    private static class UsuarioListener implements DocumentListener {
        private final JTextField field;
        private final JLabel label;

        public UsuarioListener(JTextField field, JLabel label) {
            this.field = field;
            this.label = label;
        }

        private void update() {
            String text = field.getText().trim();
            label.setText(text.isEmpty() ? "@Usuario" : "@" + text);
        }

        public void changedUpdate(DocumentEvent e) { update(); }
        public void removeUpdate(DocumentEvent e) { update(); }
        public void insertUpdate(DocumentEvent e) { update(); }
    }

    private static class NombreListener implements DocumentListener {
        private final JTextField field;
        private final JLabel label;

        public NombreListener(JTextField field, JLabel label) {
            this.field = field;
            this.label = label;
        }

        private void update() {
            String text = field.getText().trim();
            label.setText(text.isEmpty() ? "Nombre completo" : text);
        }

        public void changedUpdate(DocumentEvent e) { update(); }
        public void removeUpdate(DocumentEvent e) { update(); }
        public void insertUpdate(DocumentEvent e) { update(); }
    }
}
