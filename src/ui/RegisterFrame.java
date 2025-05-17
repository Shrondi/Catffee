package ui;

import components.RoundedButton;
import components.RoundedPasswordField;
import components.RoundedTextField;
import listeners.RegisterListeners;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends BaseFrame {

    private JLabel usuarioLabelHeader;
    private JLabel nombreLabelHeader;

    private RoundedTextField usuarioField;
    private RoundedTextField nombreCompletoField;

    private JLabel avatarLabel;

    private final RegisterListeners listeners;

    public RegisterFrame(String title) {
        super(title);

        listeners = new RegisterListeners(this);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(topTitle());
        container.add(backButton());
        container.add(formPanel());

        add(container); // Añadimos todo junto

        // Añadir listeners después de construir los campos y labels
        listeners.addTextListeners(usuarioField, usuarioLabelHeader, nombreCompletoField, nombreLabelHeader);

        avatarLabel.addMouseListener(listeners.avatarClickListener());
    }

    private JPanel topTitle() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.decode("#252424"));
        topPanel.setPreferredSize(new Dimension(413, 85));
        topPanel.setMaximumSize(new Dimension(413, 85));

        JLabel titleLabel = new JLabel("Registro");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 30));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(23, 31, 23, 213));

        topPanel.add(titleLabel, BorderLayout.CENTER);
        return topPanel;
    }

    private JPanel backButton() {
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setBorder(BorderFactory.createEmptyBorder(17, 14, 0, 363));
        backPanel.setMaximumSize(new Dimension(412, 50));

        ImageIcon icon = new ImageIcon("resources/images/back_icon.png");
        Image scaled = icon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        JButton backButton = new JButton(new ImageIcon(scaled));
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setPreferredSize(new Dimension(35, 35));
        backButton.setMaximumSize(new Dimension(35, 35));

        backPanel.add(backButton);
        return backPanel;
    }

    private JPanel formPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(headerInfo());
        panel.add(Box.createVerticalStrut(40));

        String[][] fields = {
                { "Usuario", "false" },
                { "Nombre completo", "false" },
                { "Correo", "false" },
                { "Contraseña", "true" },
                { "Repetir contraseña", "true" }
        };

        for (String[] field : fields) {
            panel.add(createLabeledField(field[0], Boolean.parseBoolean(field[1])));
            panel.add(Box.createVerticalStrut(10));
        }

        panel.add(Box.createVerticalStrut(20));
        panel.add(registerButton());
        panel.add(Box.createVerticalStrut(30));

        return panel;
    }

    private JPanel headerInfo() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        ImageIcon icon = new ImageIcon("resources/images/profile_placeholder.png");
        Image scaled = icon.getImage().getScaledInstance(155, 151, Image.SCALE_SMOOTH);
        avatarLabel = new JLabel(new ImageIcon(scaled));
        avatarLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(avatarLabel);
        header.add(Box.createVerticalStrut(20));

        nombreLabelHeader = centeredText("Nombre completo", new Font("Fredoka SemiBold", Font.PLAIN, 20));
        usuarioLabelHeader = centeredText("@Usuario", new Font("Fredoka Regular", Font.PLAIN, 16));

        header.add(nombreLabelHeader);
        header.add(Box.createVerticalStrut(10));
        header.add(usuarioLabelHeader);

        return header;
    }

    private RoundedButton registerButton() {
        RoundedButton button = new RoundedButton("Registrarse", 16);
        button.setFont(new Font("Roboto Regular", Font.PLAIN, 14));
        button.setBackground(Color.decode("#313131"));
        button.setForeground(Color.WHITE);
        button.setMaximumSize(new Dimension(342, 44));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    private JPanel createLabeledField(String labelText, boolean isPassword) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
        fieldPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldPanel.setMaximumSize(new Dimension(342, 70));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Roboto Regular", Font.PLAIN, 14));
        label.setForeground(Color.BLACK);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        JComponent field;
        if ("Usuario".equals(labelText)) {
            usuarioField = new RoundedTextField(20);
            field = usuarioField;
        } else if ("Nombre completo".equals(labelText)) {
            nombreCompletoField = new RoundedTextField(20);
            field = nombreCompletoField;
        } else if (isPassword) {
            field = new RoundedPasswordField(20);
        } else {
            field = new RoundedTextField(20);
        }

        if (field instanceof RoundedTextField) {
            ((RoundedTextField) field).setBorderColor(Color.decode("#D4D7E3"));
            ((RoundedTextField) field).setBorderWidth(2.0f);
            ((RoundedTextField) field).setPlaceholder(labelText);

        } else if (field instanceof RoundedPasswordField) {
            ((RoundedPasswordField) field).setBorderColor(Color.decode("#D4D7E3"));
            ((RoundedPasswordField) field).setBorderWidth(2.0f);
            ((RoundedPasswordField) field).setPlaceholder(labelText);
        }

        field.setBackground(Color.decode("#F3F7FB"));
        field.setPreferredSize(new Dimension(342, 42));
        field.setMaximumSize(new Dimension(342, 42));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);

        fieldPanel.add(label);
        fieldPanel.add(field);

        return fieldPanel;
    }

    private JLabel centeredText(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    public void updateAvatarImage(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaled = icon.getImage().getScaledInstance(155, 151, Image.SCALE_SMOOTH);
        avatarLabel.setIcon(new ImageIcon(scaled));
    }
}
