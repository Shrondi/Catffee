package ui.error;

import ui.BaseFrame;
import javax.swing.*;
import java.awt.*;

import components.button.RoundedButton;
import utils.I18n;

/**
 * Ventana de error para Catffee. Muestra un mensaje y permite reintentar la acción.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
/**
 * Frame de error que muestra un mensaje y un botón para reintentar.
 */
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
        JLabel titleLabel = new JLabel(I18n.getTranslation("error_title"));
        titleLabel.setFont(new Font("Poppins SemiBold", Font.PLAIN, 30));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        mainPanel.add(titleLabel);
    }

    private void addMessageLabel(JPanel mainPanel) {
        JLabel messageLabel = new JLabel("<html><div style='text-align: center;'>" + I18n.getTranslation("error_msg") + "</div></html>");
        messageLabel.setFont(new Font("Poppins Regular", Font.PLAIN, 22));
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        Box messageBox = Box.createHorizontalBox(); // Usar un Box
        messageBox.add(messageLabel);
        messageBox.setMaximumSize(new Dimension(333, 154)); // Establecer el tamaño máximo

        mainPanel.add(messageBox);
    }

    private void addCatLabel(JPanel mainPanel) {
        ImageIcon catIcon = new ImageIcon(getClass().getClassLoader().getResource("images/ui/error.png"));
        Image catImage = catIcon.getImage().getScaledInstance(389, 389, Image.SCALE_SMOOTH);
        catIcon = new ImageIcon(catImage);
        JLabel catLabel = new JLabel(catIcon);
        catLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(catLabel);
    }

    private void addRetryButton() {
        RoundedButton retryButton = new RoundedButton(I18n.getTranslation("error_retry"), 50);
        retryButton.setBackground(Color.decode("#C67C4E"));
        retryButton.setForeground(Color.WHITE);
        retryButton.setPreferredSize(new Dimension(363, 58));
        retryButton.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        retryButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonPanel = new JPanel(); // Panel para contener el botón
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(retryButton);
        buttonPanel.setBackground(null);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0)); // Margen inferior

        add(buttonPanel, BorderLayout.SOUTH); //Añadir boton a la ventana
    }
}