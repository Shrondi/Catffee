package ui;

import components.RoundedButton;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends BaseFrame {

    public RegisterFrame(String title) {
        super(title);
        setLayout(new BorderLayout());

        addTopBar();
        addFormPanel();
    }

    private void addTopBar() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.decode("#1E1E1E"));

        JButton backButton = new JButton("<");
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(null);
        backButton.setBorderPainted(false);
        backButton.setFont(new Font("Sora SemiBold", Font.PLAIN, 30));

        JLabel titleLabel = new JLabel("Registro");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 30));

        topPanel.add(backButton);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(titleLabel);
        topPanel.setPreferredSize(new Dimension(400, 60));

        add(topPanel, BorderLayout.NORTH);
    }

    private void addFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.decode("#F9F9F9"));

        panel.add(Box.createVerticalStrut(20));
        panel.add(centeredLabel(new ImageIcon("resources/images/profile_placeholder.png"), 155, 151));

        panel.add(centeredText("Nombre completo", new Font("Fredoka SemiBold", Font.PLAIN, 20)));
        panel.add(centeredText("@Usuario", new Font("Fredoka Regular", Font.PLAIN, 16), new Color(105, 105, 105)));


        panel.add(Box.createVerticalStrut(20));
        panel.add(createTextField("Usuario", "@"));
        panel.add(createTextField("Nombre completo", ""));
        panel.add(createTextField("Correo", "correo@email.com"));
        panel.add(createPasswordField("Contraseña"));
        panel.add(createPasswordField("Repetir contraseña"));

        panel.add(Box.createVerticalStrut(30));

        RoundedButton registerButton = new RoundedButton("Registrarse", 16);
        registerButton.setBackground(Color.decode("#2C2C2C"));
        registerButton.setForeground(Color.WHITE);
        registerButton.setPreferredSize(new Dimension(300, 50));
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(registerButton);
        panel.add(Box.createVerticalStrut(30));

        add(panel, BorderLayout.CENTER);
    }

    private JLabel centeredLabel(ImageIcon icon, int width, int height) {
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(img));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JLabel centeredText(String text, Font font) {
        return centeredText(text, font, Color.BLACK);
    }

    private JLabel centeredText(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JPanel createTextField(String labelText, String placeholder) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Sora", Font.PLAIN, 14));

        JTextField field = new JTextField(placeholder);
        field.setFont(new Font("Sora", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(300, 40));
        field.setMaximumSize(new Dimension(300, 40));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(null);
        panel.add(label);
        panel.add(field);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 40, 5, 40));
        return panel;
    }

    private JPanel createPasswordField(String labelText) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Sora SemiBold", Font.PLAIN, 14));

        JPasswordField field = new JPasswordField();
        field.setFont(new Font("Sora SemiBold", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(300, 40));
        field.setMaximumSize(new Dimension(300, 40));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(null);
        panel.add(label);
        panel.add(field);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 40, 5, 40));
        return panel;
    }
}