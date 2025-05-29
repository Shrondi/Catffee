package ui.error;

import ui.BaseFrame;
import javax.swing.*;
import java.awt.*;

import components.button.RoundedButton;

public class ErrorFrame extends BaseFrame {
    private JPanel mainPanel;

    public ErrorFrame(String title) {
        super(title);

        setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(null);
        mainPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        addTitleLabel(mainPanel);
        addMessageLabel(mainPanel);
        addCatLabel(mainPanel);

        addRetryButton();

        add(mainPanel, BorderLayout.CENTER);
    }

    private void addTitleLabel(JPanel mainPanel) {
        JLabel titleLabel = new JLabel("Oops!");
        titleLabel.setFont(new Font("Poppins SemiBold", Font.PLAIN, 30));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        mainPanel.add(titleLabel);
    }

    private void addMessageLabel(JPanel mainPanel) {
        JLabel messageLabel = new JLabel("<html><div style='text-align: center;'>Parece que hubo un error o tal vez un gato se tumbó sobre el servidor...</div></html>");
        messageLabel.setFont(new Font("Poppins Regular", Font.PLAIN, 22));
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        Box messageBox = Box.createHorizontalBox(); // Usar un Box
        messageBox.add(messageLabel);
        messageBox.setMaximumSize(new Dimension(333, 154)); // Establecer el tamaño máximo

        mainPanel.add(messageBox);
    }

    private void addCatLabel(JPanel mainPanel) {
        ImageIcon catIcon = new ImageIcon("resources/images/ui/error.png");
        Image catImage = catIcon.getImage().getScaledInstance(389, 389, Image.SCALE_SMOOTH);
        catIcon = new ImageIcon(catImage);
        JLabel catLabel = new JLabel(catIcon);
        catLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(catLabel);
    }

    private void addRetryButton() {
        RoundedButton retryButton = new RoundedButton("Intentar de Nuevo", 50);
        retryButton.setBackground(Color.decode("#C67C4E"));
        retryButton.setForeground(Color.WHITE);
        retryButton.setPreferredSize(new Dimension(363, 58));
        retryButton.setFont(new Font("Sora Semibold", Font.PLAIN, 16));
        retryButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonPanel = new JPanel(); // Panel para contener el botón
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(retryButton);
        buttonPanel.setBackground(null);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0)); // Margen inferior

        add(buttonPanel, BorderLayout.SOUTH); //Añadir boton a la ventana
    }
}