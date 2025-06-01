package ui.profile;

import components.panel.RoundedPanel;
import utils.I18n;
import utils.UserStorage;
import controller.navigation.NavigationHost;
import controller.profile.ProfileController;
import utils.LangOption;
import components.bar.TopBar;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel{

    private final UserStorage.User currentUser;
    private final NavigationHost navigationHost;
    private final ProfileController profileController;

    public ProfilePanel(UserStorage.User currentUser, NavigationHost navigationHost) {
        this.currentUser = currentUser;
        this.navigationHost = navigationHost;
        this.profileController = new ProfileController(navigationHost, currentUser);
        
        setLayout(new BorderLayout());

        add(createTopBar(), BorderLayout.NORTH);
        add(createContentPanel(), BorderLayout.CENTER);
    }

    /**
     * Crea la barra superior con el título.
     */
    private JPanel createTopBar() {
        return new TopBar(I18n.getTranslation("profile_title"));
    }

    /**
     * Crea el panel principal de contenido del perfil.
     */
    private JPanel createContentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.decode("#F9F9F9"));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        // Tarjeta de perfil
        panel.add(Box.createVerticalStrut(50));
        panel.add(createProfileCard());
        panel.add(Box.createVerticalStrut(100));

        // Opciones
        panel.add(createOption(getClass().getClassLoader().getResource("images/ui/translate.png").getPath(), I18n.getTranslation("profile_language")));
        panel.add(Box.createVerticalStrut(20));
        panel.add(createOption(getClass().getClassLoader().getResource("images/ui/heart.png").getPath(), I18n.getTranslation("profile_rate")));
        panel.add(Box.createVerticalStrut(20));
        panel.add(createOption(getClass().getClassLoader().getResource("images/ui/log_out.png").getPath(), I18n.getTranslation("profile_logout")));

        return panel;
    }

    /**
     * Crea la tarjeta de perfil con avatar y nombre.
     */
    private RoundedPanel createProfileCard() {
        RoundedPanel profileCard = new RoundedPanel(30);
        profileCard.setBackground(Color.WHITE);
        profileCard.setLayout(new BoxLayout(profileCard, BoxLayout.X_AXIS));
        profileCard.setAlignmentX(Component.CENTER_ALIGNMENT);
        profileCard.setPreferredSize(new Dimension(420, 160));
        profileCard.setMaximumSize(new Dimension(420, 160));
        profileCard.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String avatarPath = currentUser.getAvatarPath();
        ImageIcon profileIcon = new ImageIcon(getClass().getClassLoader().getResource("images/ui/profile_placeholder.png"));
        if (!avatarPath.isEmpty()) {
            profileIcon = new ImageIcon(avatarPath);
        }
        Image profileImg = profileIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        JLabel profilePic = new JLabel(new ImageIcon(profileImg));

        String nombre = currentUser.getNombreCompleto();
        String user = currentUser.getUser();
        JLabel nameBlock = new JLabel(
            String.format("<html><div style='font-size:18px; font-family:Sora SemiBold; font-weight:plain;'> %s </div><div style='color:gray;'> @%s </div></html>", nombre, user));
        nameBlock.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        profileCard.add(profilePic);
        profileCard.add(nameBlock);
        return profileCard;
    }

    /**
     * Crea una opción de menú en el perfil (idioma, valorar, cerrar sesión).
     */
    private RoundedPanel createOption(String iconPath, String labelText) {
        RoundedPanel option = new RoundedPanel(30);
        option.setBackground(Color.WHITE);
        option.setLayout(new BorderLayout());
        option.setPreferredSize(new Dimension(340, 70));
        option.setMaximumSize(new Dimension(340, 70));
        option.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        // Icono
        ImageIcon icon = new ImageIcon(iconPath);
        Image scaledIcon = icon.getImage().getScaledInstance(22, 22, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(scaledIcon));

        // Texto
        JLabel textLabel = new JLabel(labelText);
        textLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 18));

        // Componente derecho (flecha)
        ImageIcon arrowIcon = new ImageIcon(getClass().getClassLoader().getResource("images/ui/next_icon.png"));
        Image scaledArrow = arrowIcon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        JLabel arrowLabel = new JLabel(new ImageIcon(scaledArrow));
        arrowLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JComponent rightComponent = arrowLabel;

        // Panel izquierdo (icono + texto)
        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 20));
        left.setOpaque(false);
        left.add(iconLabel);
        left.add(Box.createHorizontalStrut(10));
        left.add(textLabel);

        // Panel derecho (flecha)
        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 20));
        right.setOpaque(false);
        right.add(rightComponent);

        option.add(left, BorderLayout.WEST);
        option.add(right, BorderLayout.EAST);

        // Si es la opción de idioma, toda la tarjeta es clicable mediante MouseListener
        if (labelText.equals(I18n.getTranslation("profile_language"))) {
            option.setCursor(new Cursor(Cursor.HAND_CURSOR));
            option.addMouseListener(new ProfileListener(profileController, null, "idioma", option));
        }
        // Si es la opción de cerrar sesión, toda la tarjeta es clicable mediante MouseListener
        else if (labelText.equals(I18n.getTranslation("profile_logout"))) {
            option.setCursor(new Cursor(Cursor.HAND_CURSOR));
            option.addMouseListener(new ProfileListener(profileController, null, "logout", null));
        }
        // Si es la opción de valorar
        else if (labelText.equals(I18n.getTranslation("profile_rate"))) {
            option.setCursor(new Cursor(Cursor.HAND_CURSOR));
            option.addMouseListener(new ProfileListener(profileController, null, "rate", null));
        }

        return option;
    }

    public void mostrarDialogoIdioma(JComponent parentOption) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(parentOption), I18n.getTranslation("profile_select_lang"), true);
        dialog.setUndecorated(true);
        dialog.setSize(260, 160);
        Point location = parentOption.getLocationOnScreen();
        int x = location.x + (parentOption.getWidth() - dialog.getWidth()) / 2;
        int y = location.y + (parentOption.getHeight() - dialog.getHeight()) / 2 - 30;
        dialog.setLocation(x, y);

        JPanel content = new JPanel();
        content.setBackground(Color.WHITE);
        content.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 2, true));
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(I18n.getTranslation("profile_select_lang"));
        title.setFont(new Font("Sora SemiBold", Font.PLAIN, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(16, 0, 12, 0));
        content.add(title);

        LangOption[] languages = LangOption.getAvailableLanguages();
        String[] icons = {
            getClass().getClassLoader().getResource("images/ui/flag_es.png").getPath(),
            getClass().getClassLoader().getResource("images/ui/flag_en.png").getPath()
        };
        for (int i = 0; i < languages.length; i++) {
            JButton button = crearBotonIdioma(icons[i], languages[i], dialog);
            content.add(button);
            content.add(Box.createVerticalStrut(10));
        }

        dialog.setContentPane(content);
        dialog.setVisible(true);
    }

    private JButton crearBotonIdioma(String iconPath, LangOption lang, JDialog dialog) {
        JButton button = new JButton(lang.label, new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(28, 20, Image.SCALE_SMOOTH)));
        button.setFont(new Font("Sora Regular", Font.PLAIN, 16));
        button.setFocusPainted(false);
        button.setBackground(new Color(245, 245, 245));
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(6, 12, 6, 12)));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setMaximumSize(new Dimension(160, 36));
        button.setMinimumSize(new Dimension(160, 36));
        button.setPreferredSize(new Dimension(160, 36));
        button.addActionListener(_ -> dialog.dispose());
        button.addActionListener(new ProfileListener(profileController, lang.code, "idioma", null));
        return button;
    }
}
