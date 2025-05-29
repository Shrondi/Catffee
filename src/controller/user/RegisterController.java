package controller.user;

import ui.register.RegisterFrame;
import utils.UserStorage;
import controller.navigation.NavigationHost;
import javax.swing.*;
import java.io.File;

public class RegisterController {
    private final RegisterFrame frame;
    private final NavigationHost navigationHost;
    private final UserStorage storage;
    private File avatarFile;

    public RegisterController(RegisterFrame frame, NavigationHost navigationHost) {
        this.frame = frame;
        this.navigationHost = navigationHost;
        this.storage = UserStorage.getInstance();
    }

    public void setAvatarFile(File file) {
        this.avatarFile = file;
    }

    public void registrarUsuario() {
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
            navigationHost.showLoginFrame();
        } else {
            JOptionPane.showMessageDialog(frame, "Error al registrar usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void volverALogin() {
        navigationHost.showLoginFrame();
    }

    public void seleccionarAvatar() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Seleccionar imagen de perfil");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = chooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File avatarFile = chooser.getSelectedFile();
            frame.updateAvatarImage(avatarFile.getAbsolutePath());
            setAvatarFile(avatarFile);
        }
    }
} 