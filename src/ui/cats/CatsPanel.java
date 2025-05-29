package ui.cats;

import components.panel.RoundedPanel;
import components.button.RoundedButton;
import components.panel.GradientPanel;
import utils.I18n;

import javax.swing.*;
import java.awt.*;

/**
 * Panel de visualización de gatos disponibles para adopción en Catffee.
 * Permite mostrar tarjetas de gatos y gestionar la adopción.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
public class CatsPanel extends JPanel {

    /** Listener para eventos de adopción de gato. */
    private CatAdoptListener adoptListener;
    /**
     * Establece el listener para adopción de gatos.
     * @param listener Listener a registrar
     */
    public void setCatAdoptListener(CatAdoptListener listener) {
        this.adoptListener = listener;
    }

    /**
     * Crea el panel de gatos.
     */
    public CatsPanel() {
        setLayout(new BorderLayout());
        GradientPanel background = new GradientPanel(new Color(0xBD6E36), new Color(0xCDCDCD), true);
        background.setLayout(new BorderLayout());
        add(background, BorderLayout.CENTER);
        background.add(buildTopBar(), BorderLayout.NORTH);
        background.add(buildCatsScrollPanel(), BorderLayout.CENTER);
    }

    /**
     * Construye la barra superior con el título.
     * @return JPanel de la barra superior
     */
    private JPanel buildTopBar() {
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.decode("#252424"));
        topPanel.setPreferredSize(new Dimension(413, 85));
        topPanel.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel(I18n.t("cats_title"));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 30));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(23, 30, 23, 288));
        topPanel.add(titleLabel, BorderLayout.WEST);
        return topPanel;
    }

    /**
     * Construye el panel con la galería de gatos y la descripción.
     * @return JScrollPane con el contenido
     */
    private JScrollPane buildCatsScrollPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel description = new JLabel(I18n.t("cats_desc"));
        description.setFont(new Font("Fredoka Regular", Font.PLAIN, 20));
        RoundedPanel descriptionPanel = new RoundedPanel(15, Color.WHITE);
        descriptionPanel.setMaximumSize(new Dimension(378, 119));
        descriptionPanel.add(description, BorderLayout.CENTER);
        descriptionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        descriptionPanel.setBorder(Color.BLACK, 1);
        contentPanel.add(descriptionPanel);
        String[] cats = { "Alfil", "Cherry", "Abi", "Flush", "Gulliver", "Membrillo", "Mazinger", "Sylvestre" };
        String[] images = {
            "resources/images/cats/alfil/alfil1.png",
            "resources/images/cats/cherry/cherry1.png",
            "resources/images/cats/abi/abi1.png",
            "resources/images/cats/flush/flush1.png",
            "resources/images/cats/gulliver/gulliver1.png",
            "resources/images/cats/membrillo/membrillo1.png",
            "resources/images/cats/mazinger/mazinger1.png",
            "resources/images/cats/sylvestre/sylvestre1.png"
        };
        JPanel gridPanel = new JPanel(new GridBagLayout());
        gridPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        for (int i = 0; i < cats.length; i++) {
            JPanel card = buildCatCard(images[i], cats[i]);
            gbc.gridx = i % 2;
            gbc.gridy = i / 2;
            if (gbc.gridx == 0) {
                gbc.insets = (gbc.gridy == 0)
                        ? new Insets(15, 0, 15, 15)
                        : new Insets(-65, 0, 15, 15);
            } else {
                gbc.insets = (gbc.gridy == 0)
                        ? new Insets(80, 15, 15, 0)
                        : new Insets(0, 15, 15, 0);
            }
            gridPanel.add(card, gbc);
        }
        contentPanel.add(gridPanel);
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.getVerticalScrollBar().setOpaque(false);
        return scrollPane;
    }

    /**
     * Construye una tarjeta visual para un gato.
     * @param imagePath Ruta de la imagen del gato
     * @param name Nombre del gato
     * @return JPanel con la tarjeta del gato
     */
    private JPanel buildCatCard(String imagePath, String name) {
        RoundedPanel card = new RoundedPanel(16);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(180, 240));
        card.setMaximumSize(new Dimension(180, 240));
        card.add(Box.createVerticalStrut(10));
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaled = icon.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(scaled));
        imgLabel.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 22));
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 24));
        nameLabel.setForeground(Color.decode("#000000"));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 22));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        RoundedButton adoptButton = new RoundedButton(I18n.t("adopt"), 20);
        adoptButton.setBackground(Color.decode("#C67C4E"));
        adoptButton.setForeground(Color.WHITE);
        adoptButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        adoptButton.setOpaque(false);
        adoptButton.setBorderPainted(false);
        adoptButton.setPreferredSize(new Dimension(113, 22));
        adoptButton.setMaximumSize(new Dimension(113, 22));
        adoptButton.setFont(new Font("Sora Semibold", Font.PLAIN, 12));
        adoptButton.addActionListener(_ -> {
            if (adoptListener != null) {
                adoptListener.onAdoptCat(name);
            }
        });
        card.add(imgLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(nameLabel);
        card.add(Box.createVerticalStrut(7));
        card.add(adoptButton);
        return card;
    }

}
