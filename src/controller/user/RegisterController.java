package controller.user;

import ui.register.RegisterFrame;
import utils.UserStorage;
import controller.navigation.NavigationHost;
import javax.swing.*;
import java.io.File;
import java.awt.*;

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

            if (usuario.isEmpty()) {
                frame.setUsuarioError("El nombre de usuario es obligatorio.");
                hasError = true;
            }
            if (nombreCompleto.isEmpty()) {
                frame.setNombreCompletoError("El nombre completo es obligatorio.");
                hasError = true;
            }
            if (email.isEmpty()) {
                frame.setCorreoError("El email es obligatorio.");
                hasError = true;
            }
            if (password.isEmpty()) {
                frame.setPasswordError("La contraseña es obligatoria.");
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
            utils.ErrorUtil.mostrarErrorCritico("Error crítico al registrar usuario");
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
        // Crear un JDialog moderno y visualmente atractivo
        JDialog dialog = new JDialog(frame, utils.I18n.t("register_success_title"), true);
        int width = frame.getWidth();
        int height = frame.getHeight();
        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Degradado suave de fondo
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(255, 245, 230);
                Color color2 = new Color(255, 220, 180);
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
            }
        };
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(width, height));
        panel.setMinimumSize(new Dimension(width, height));
        panel.setMaximumSize(new Dimension(width, height));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0xC67C4E), 2, true),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 30, 0);
        gbc.anchor = GridBagConstraints.NORTH;
        // Icono de check animado
        ImageIcon checkIcon = new ImageIcon(getClass().getClassLoader().getResource("images/ui/check_success.gif")); // Debes añadir este gif animado
        JLabel checkLabel = new JLabel(checkIcon);
        checkLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(checkLabel, gbc);
        // Título
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel title = new JLabel("<html><div style='width: "+(width-80)+"px; text-align:center; font-size:26px; color:#C67C4E;'><b>" + utils.I18n.t("register_success_title") + "</b></div></html>", SwingConstants.CENTER);
        title.setFont(new Font("Sora SemiBold", Font.PLAIN, 26));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(title, gbc);
        // Mensaje principal
        gbc.gridy = 2;
        JLabel msg = new JLabel("<html><div style='width: "+(width-80)+"px; text-align:center; font-size:17px; color:#313131;'>" + utils.I18n.t("register_success_msg") + "</div></html>", SwingConstants.CENTER);
        msg.setFont(new Font("Sora Regular", Font.PLAIN, 17));
        msg.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(msg, gbc);
        // Mensaje secundario
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 0, 0, 0);
        JLabel secondary = new JLabel("<html><div style='width: "+(width-60)+"px; text-align:center; font-size:14px; color:#888;'>" + utils.I18n.t("register_success_secondary") + "</div></html>", SwingConstants.CENTER);
        secondary.setFont(new Font("Sora Regular", Font.PLAIN, 14));
        secondary.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(secondary, gbc);
        dialog.setUndecorated(true);
        dialog.setContentPane(panel);
        dialog.pack();
        dialog.setSize(width, height);
        // Centrar dentro del frame principal
        Point parentLoc = frame.getLocationOnScreen();
        int x = parentLoc.x;
        int y = parentLoc.y;
        dialog.setLocation(x, y);
        new javax.swing.Timer(3000, _ -> {
            dialog.dispose();
            navigationHost.showLoginFrame();
        }) {{ setRepeats(false); }}.start();
        dialog.setVisible(true);
    }
} 