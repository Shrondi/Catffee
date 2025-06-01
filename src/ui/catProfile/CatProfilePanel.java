package ui.catProfile;

import components.panel.*;
import utils.I18n;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import model.CatProfile;

/**
 * Panel para mostrar el perfil detallado de un gato en Catffee.
 * Incluye galería de imágenes y datos del gato.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
public class CatProfilePanel extends JPanel {

    /**
     * Crea el panel de perfil de gato.
     * @param profile Perfil del gato a mostrar
     */
    public CatProfilePanel(CatProfile profile) {

        GradientPanel background = new GradientPanel(new Color(0xBD6E36), new Color(0xCDCDCD), true);
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
        add(background, BorderLayout.CENTER);

        background.add(buildImagePanel(profile.getImagePaths()));
        background.add(Box.createVerticalStrut(-10));
        background.add(buildInfoPanel(profile));
    }

    /**
     * Crea y muestra el panel de perfil de gato en un hilo de Swing.
     * @param profile Perfil del gato
     */
    public static void create(CatProfile profile) {
        SwingUtilities.invokeLater(() -> {
            new CatProfilePanel(profile).setVisible(true);
        });
    }

    private JPanel buildImagePanel(URL[] imagePaths) {
        JPanel container = new JPanel(new BorderLayout());
        container.setOpaque(false);
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));

        JPanel imageRow = new JPanel();
        imageRow.setLayout(new BoxLayout(imageRow, BoxLayout.X_AXIS));
        imageRow.setOpaque(false);

        for (URL path : imagePaths) {
            ImageIcon icon = new ImageIcon(path);
            Image scaled = icon.getImage().getScaledInstance(334, 334, Image.SCALE_SMOOTH);
            JLabel label = new JLabel(new ImageIcon(scaled));

            RoundedPanel rounded = new RoundedPanel(20, new Color(255, 255, 255, 0));
            rounded.setLayout(new BorderLayout());
            rounded.add(label, BorderLayout.CENTER);
            rounded.setPreferredSize(new Dimension(334, 334));
            rounded.setMaximumSize(new Dimension(334, 334));
            rounded.setOpaque(false);

            imageRow.add(rounded);
            imageRow.add(Box.createHorizontalStrut(10));
        }

        JScrollPane scrollPane = new JScrollPane(imageRow, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setPreferredSize(new Dimension(374, 354));
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.setWheelScrollingEnabled(true);

        container.add(scrollPane, BorderLayout.CENTER);
        return container;
    }

    private JPanel buildInfoPanel(CatProfile profile) {
        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(false);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        // Panel superior con nombre, edad, género
        RoundedPanel topInfo = new RoundedPanel(10, Color.WHITE);
        topInfo.setLayout(new BoxLayout(topInfo, BoxLayout.Y_AXIS));
        topInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        topInfo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        topInfo.setBorder(Color.BLACK, 1);
        topInfo.setMinimumSize(new Dimension(347, 150));
        topInfo.setPreferredSize(new Dimension(347, 150));
        topInfo.setMaximumSize(new Dimension(347, 150));

        RoundedPanel namePanel = new RoundedPanel(10, new Color(255, 255, 255, 150)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                int width = getWidth();
                int height = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, new Color(0x947257), width, 0, new Color(0xDF7622));
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, width, height, 20, 20);
                g2d.dispose();
            }
        };
        namePanel.setLayout(new BorderLayout());
        namePanel.setPreferredSize(new Dimension(275, 58));
        namePanel.setMaximumSize(new Dimension(275, 58));

        JLabel nameLabel = new JLabel(profile.getName());
        nameLabel.setFont(new Font("Fredoka Medium", Font.PLAIN, 36));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        namePanel.add(nameLabel, BorderLayout.CENTER);
        namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel ageLabel = new JLabel(String.format("%s %s", profile.getAge(), I18n.t("catprofile_age")));
        ageLabel.setFont(new Font("Poppins Medium", Font.PLAIN, 20));
        ageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        String genderKey = profile.getGender().equalsIgnoreCase("Hembra") ? "catprofile_gender_female" : "catprofile_gender_male";
        JLabel genderLabel = new JLabel(I18n.t(genderKey));
        genderLabel.setFont(new Font("Poppins Medium", Font.PLAIN, 20));
        genderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        topInfo.add(namePanel);
        topInfo.add(Box.createVerticalStrut(8));
        topInfo.add(ageLabel);
        topInfo.add(Box.createVerticalStrut(4));
        topInfo.add(genderLabel);

        // Panel "Sobre mí"
        RoundedPanel aboutPanel = new RoundedPanel(10, Color.WHITE);
        aboutPanel.setLayout(new BorderLayout());
        aboutPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        aboutPanel.setBorder(Color.BLACK, 1);
        aboutPanel.setPreferredSize(new Dimension(347, 158));
        aboutPanel.setMaximumSize(new Dimension(347, 158));

        JLabel aboutLabel = new JLabel("<html><b>" + I18n.t("catprofile_about") + "</b><br>" + profile.getAbout() + "</html>");
        aboutLabel.setFont(new Font("Fredoka Regular", Font.PLAIN, 20));
        aboutPanel.add(aboutLabel, BorderLayout.CENTER);

        // Panel "Ideal para"
        RoundedPanel idealPanel = new RoundedPanel(10, Color.WHITE);
        idealPanel.setLayout(new BorderLayout());
        idealPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        idealPanel.setPreferredSize(new Dimension(347, 100));
        idealPanel.setBorder(Color.BLACK, 1);
        idealPanel.setMaximumSize(new Dimension(347, 100));

        JLabel idealLabel = new JLabel("<html><b>" + I18n.t("catprofile_ideal") + "</b><br>" + profile.getIdeal() + "</html>");
        idealLabel.setFont(new Font("Fredoka Regular", Font.PLAIN, 20));
        idealPanel.add(idealLabel, BorderLayout.CENTER);

        infoPanel.add(topInfo);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(aboutPanel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(idealPanel);

        return infoPanel;
    }
}
