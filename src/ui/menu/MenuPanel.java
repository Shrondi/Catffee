package ui.menu;

import components.panel.ProductBox;
import model.ProductData;
import model.Products;
import utils.I18n;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;
import components.bar.TopBar;

/**
 * Panel de menú principal de Catffee. Muestra las categorías de productos y permite añadir productos al pedido.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
public class MenuPanel extends JPanel {
    /** Callback para notificar cuando se añade un producto al pedido. */
    private final Consumer<ProductData> onProductAdded;

    /**
     * Crea el panel de menú.
     * @param onProductAdded función a ejecutar cuando se añade un producto
     */
    public MenuPanel(Consumer<ProductData> onProductAdded) {
        this.onProductAdded = onProductAdded;
        setLayout(new BorderLayout());
        add(mainPanel(), BorderLayout.CENTER);
    }

    /**
     * Construye el panel principal con fondo y contenido.
     * @return JPanel principal
     */
    private JPanel mainPanel() {
        JPanel backgroundPanel = new JPanel(new BorderLayout());

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(412, 917));

        // Fondo con imagen
        JLabel backgroundLabel = background(getClass().getClassLoader().getResource("images/ui/menu_bg.jpeg").getPath(), 412, 917);
        layeredPane.add(backgroundLabel, Integer.valueOf(0));

        // Contenido principal transparente sobre el fondo
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        contentPanel.setBounds(0, 0, 412, 917);

        contentPanel.add(topBar(), BorderLayout.NORTH);
        contentPanel.add(scrollContent(), BorderLayout.CENTER);

        layeredPane.add(contentPanel, Integer.valueOf(1));

        backgroundPanel.add(layeredPane, BorderLayout.CENTER);
        return backgroundPanel;
    }

    /**
     * Crea un JLabel con la imagen de fondo escalada.
     * @param imagePath Ruta de la imagen
     * @param width Ancho
     * @param height Alto
     * @return JLabel con la imagen
     */
    private JLabel background(String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(scaled));
        label.setLayout(new BorderLayout());
        label.setBounds(0, 0, width, height);
        return label;
    }

    /**
     * Construye la barra superior con el título.
     * @return JPanel de la barra superior
     */
    private JPanel topBar() {
        return new TopBar(I18n.t("menupanel_title"));
    }

    /**
     * Construye el scroll con las categorías de productos.
     * @return JScrollPane con el contenido
     */
    private JScrollPane scrollContent() {
        JPanel container = new JPanel();
        container.setOpaque(false);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(Box.createVerticalStrut(10));
        container.add(categorySection(I18n.t("menupanel_category_coffee"), Products.getCoffeeProducts()));
        container.add(Box.createVerticalStrut(20));
        container.add(categorySection(I18n.t("menupanel_category_cold_drinks"), Products.getColdDrinks()));
        container.add(Box.createVerticalStrut(20));
        container.add(categorySection(I18n.t("menupanel_category_desserts"), Products.getDesserts()));
        container.add(Box.createVerticalStrut(20));
        container.add(categorySection(I18n.t("menupanel_category_salty"), Products.getSaltyFood()));
        container.add(Box.createVerticalStrut(100));

        JScrollPane scrollPane = new JScrollPane(container);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        // Ocultar completamente scrollbars pero permitir scroll
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Permitir scroll con rueda del ratón
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Elimina el espacio reservado por la scrollbar
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));

        return scrollPane;
    }

    /**
     * Construye una sección de categoría de productos.
     * @param title Título de la categoría
     * @param products Lista de productos
     * @return JPanel de la categoría
     */
    private JPanel categorySection(String title, List<ProductData> products) {
        JPanel section = new JPanel();
        section.setOpaque(false);
        section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));

        JLabel sectionTitle = new JLabel(title);
        sectionTitle.setFont(new Font("Poppins Bold", Font.PLAIN, 25));
        sectionTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        sectionTitle.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));

        JSeparator separator = new JSeparator();
        separator.setForeground(Color.BLACK);
        separator.setMaximumSize(new Dimension(312, 2));
        separator.setAlignmentX(Component.CENTER_ALIGNMENT);
        separator.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        JPanel grid = new JPanel(new GridLayout(0, 2, 10, 15));
        grid.setOpaque(false);
        grid.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        for (ProductData p : products) {
            ProductBox box = new ProductBox(p, () -> fireProductAdded(p));
            grid.add(box);
        }

        section.add(sectionTitle);
        section.add(Box.createVerticalStrut(5));
        section.add(separator);
        section.add(Box.createVerticalStrut(10));
        section.add(grid);

        return section;
    }

    /**
     * Llama al callback cuando se añade un producto.
     * @param product producto añadido
     */
    private void fireProductAdded(ProductData product) {
        if (onProductAdded != null) {
            onProductAdded.accept(product);
        }
    }
}
