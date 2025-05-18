package ui.register;

import utils.UserStorage;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;
import java.io.File;

public class RegisterListener extends MouseAdapter implements ActionListener {

    private final RegisterFrame frame;
    private File avatarFile;
    private final UserStorage storage;

    public RegisterListener(RegisterFrame frame) {
        this.frame = frame;
        this.storage = UserStorage.getInstance();
    }

    public void addTextListeners(JTextField usuarioField, JLabel usuarioLabelHeader,
                                 JTextField nombreCompletoField, JLabel nombreLabelHeader) {
        usuarioField.getDocument().addDocumentListener(new UsuarioListener(usuarioField, usuarioLabelHeader));
        nombreCompletoField.getDocument().addDocumentListener(new NombreListener(nombreCompletoField, nombreLabelHeader));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Seleccionar imagen de perfil");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = chooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            avatarFile = chooser.getSelectedFile();
            frame.updateAvatarImage(avatarFile.getAbsolutePath());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String email = frame.correoField.getText().trim();
        String password = new String(frame.passwordField.getPassword());
        String confirmPassword = new String(frame.repeatPasswordField.getPassword());
        String usuario = frame.usuarioField.getText().trim();
        String nombreCompleto = frame.nombreCompletoField.getText().trim();

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
                || usuario.isEmpty() || nombreCompleto.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Por favor, completa todos los campos.", "Campos incompletos", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(frame, "Las contraseñas no coinciden.", "Error de contraseña", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (storage.emailExists(email)) {
            JOptionPane.showMessageDialog(frame, "El email ya está registrado.", "Usuario existente", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String avatarPath = avatarFile != null ? avatarFile.getAbsolutePath() : "";

        boolean added = storage.addUser(email, password, nombreCompleto, avatarPath);
        if (added) {
            JOptionPane.showMessageDialog(frame, "Registro exitoso. Ahora puedes iniciar sesión.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose(); // Cierra la ventana de registro
        } else {
            JOptionPane.showMessageDialog(frame, "Error al registrar usuario.", "Error", JOptionPane.ERROR_MESSAGE);
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
