package controller.user;

import ui.register.RegisterFrame;
import utils.UserStorage;
import controller.navigation.NavigationHost;
import javax.swing.*;
import java.io.File;

/**
 * Controlador para la lógica de registro de usuario en Catffee.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
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

    public boolean emailExists(String email) {
        return storage.emailExists(email);
    }

    public boolean userExists(String user) {
        return storage.userExists(user);
    }

    public boolean addUser(String email, String password, String nombreCompleto, String user, String avatarPath) {
        return storage.addUser(email, password, nombreCompleto, user, avatarPath);
    }

    public void registrarUsuario() {
        try {
            // Limpiar errores
            frame.setUsuarioError("");
            frame.setNombreCompletoError("");
            frame.setCorreoError("");
            frame.setPasswordError("");
            frame.setRepeatPasswordError("");

            String email = frame.getCorreo();
            String password = frame.getPassword();
            String confirmPassword = frame.getRepeatPassword();
            String usuario = frame.getUsuario();
            String nombreCompleto = frame.getNombreCompleto();

            boolean hasError = false;

            // Validación de usuario: solo letras y números
            if (usuario.isEmpty()) {
                frame.setUsuarioError("El nombre de usuario es obligatorio.");
                hasError = true;
            } else if (!usuario.matches("^[a-zA-Z0-9]+$")) {
                frame.setUsuarioError("El usuario solo puede contener letras y números.");
                hasError = true;
            }

            if (nombreCompleto.isEmpty()) {
                frame.setNombreCompletoError("El nombre completo es obligatorio.");
                hasError = true;
            }

            // Validación de email
            if (email.isEmpty()) {
                frame.setCorreoError("El email es obligatorio.");
                hasError = true;
            } else if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                frame.setCorreoError("El email no es válido.");
                hasError = true;
            }

            // Validación de contraseña
            if (password.isEmpty()) {
                frame.setPasswordError("La contraseña es obligatoria.");
                hasError = true;
            } else if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
                frame.setPasswordError("La contraseña debe tener al menos 8 caracteres, una letra y un número.");
                hasError = true;
            }

            if (confirmPassword.isEmpty()) {
                frame.setRepeatPasswordError("Repite la contraseña.");
                hasError = true;
            }
            if (hasError) return;

            if (!password.equals(confirmPassword)) {
                frame.setRepeatPasswordError("Las contraseñas no coinciden.");
                return;
            }

            if (emailExists(email)) {
                frame.setCorreoError("El email ya está registrado.");
                return;
            }

            if (userExists(usuario)){
                frame.setUsuarioError("El nombre de usuario ya está en uso.");
                return;
            }

            String avatarPath = avatarFile != null ? avatarFile.getAbsolutePath() : "";

            boolean added = addUser(email, password, nombreCompleto, usuario, avatarPath);
            if (added) {
                mostrarDialogoRegistroExitoso();
            } else {
                frame.setCorreoError("Error al registrar usuario.");
            }
        } catch (Exception e) {
            utils.Error.mostrarErrorCritico("Error crítico al registrar usuario");
            e.printStackTrace();
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

    private void mostrarDialogoRegistroExitoso() {
        // Crear un CustomInfoDialog reutilizable
        Icon checkIcon = null;
        java.net.URL checkUrl = getClass().getClassLoader().getResource("images/ui/check_success.gif");
        if (checkUrl != null) {
            ImageIcon icon = new ImageIcon(checkUrl);
            checkIcon = icon;
        }
        components.dialog.InfoDialog dialog = new components.dialog.InfoDialog(
            frame,
            utils.I18n.getTranslation("register_success_title"),
            utils.I18n.getTranslation("register_success_msg"),
            utils.I18n.getTranslation("register_success_secondary"),
            checkIcon,
            3000
        );
        dialog.setVisible(true);
        navigationHost.showLoginFrame();
    }
} 