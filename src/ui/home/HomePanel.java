package ui.home;

import components.panel.ProductBox;
import model.ProductData;
import model.Products;
import utils.I18n;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * Panel principal de la pantalla de inicio (Home) de Catffee.
 * Muestra la cabecera de bienvenida y los productos destacados.
 * Permite añadir productos al pedido actual.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
public class HomePanel extends JPanel {
    /** Callback para notificar cuando se añade un producto al pedido. */
    private final Consumer<ProductData> onProductAdded;
    private final String nombreCompletoUsuario;

    /**
     * Crea el panel de inicio.
     * @param onProductAdded función a ejecutar cuando se añade un producto
     */
    public HomePanel(Consumer<ProductData> onProductAdded, String nombreCompletoUsuario) {
        this.onProductAdded = onProductAdded;
        this.nombreCompletoUsuario = nombreCompletoUsuario;
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(0xF7F7F7));
        mainPanel.add(topSection());
        mainPanel.add(productScroll());
        add(mainPanel, BorderLayout.CENTER);
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

    /**
     * Construye la sección superior con la cabecera y el texto de bienvenida.
     * @return panel con la cabecera
     */
    private JPanel topSection() {
        JPanel container = new JPanel();
        container.setLayout(new OverlayLayout(container));
        Dimension size = new Dimension(412, 330);
        container.setPreferredSize(size);
        container.setMaximumSize(size);
        container.add(headerText());
        container.add(headerImage());
        return container;
    }

    /**
     * Devuelve la imagen de cabecera de bienvenida.
     * @return JLabel con la imagen
     */
    private JLabel headerImage() {
        ImageIcon icon = new ImageIcon("resources/images/ui/cabecera_bienvenida.png");
        Image scaled = icon.getImage().getScaledInstance(412, 330, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(scaled));
        label.setAlignmentX(0.5f);
        label.setAlignmentY(0.5f);
        return label;
    }

    /**
     * Devuelve el texto de bienvenida personalizado para el usuario.
     * @return panel con el texto de bienvenida
     */
    private JPanel headerText() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setAlignmentX(0.5f);
        panel.setAlignmentY(0.5f);
        JLabel welcomeText = new JLabel(
            String.format("<html><div style='text-align: left;'>" + I18n.t("welcome_user") + "<br>%s ☕</div></html>", nombreCompletoUsuario));
        welcomeText.setForeground(Color.WHITE);
        welcomeText.setFont(new Font("Sora SemiBold", Font.PLAIN, 32));
        welcomeText.setHorizontalAlignment(SwingConstants.LEFT);
        welcomeText.setVerticalAlignment(SwingConstants.BOTTOM);
        welcomeText.setBorder(BorderFactory.createEmptyBorder(0, 40, 40, 0));
        panel.add(welcomeText, BorderLayout.CENTER);
        return panel;
    }

    /**
     * Devuelve el scroll con los productos destacados.
     * @return JScrollPane con los productos
     */
    private JScrollPane productScroll() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(0xF7F7F7));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel destacadosLabel = new JLabel(I18n.t("home_featured"));
        destacadosLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 20));
        destacadosLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        destacadosLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 16, 0));
        contentPanel.add(destacadosLabel);
        contentPanel.add(productGrid());
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        return scrollPane;
    }

    /**
     * Construye el grid de productos destacados usando la clase Products.
     * @return JPanel con los productos destacados
     */
    private JPanel productGrid() {
        JPanel grid = new JPanel(new GridLayout(0, 2, 12, 12));
        grid.setBackground(new Color(0xF7F7F7));
        grid.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Selección de productos destacados desde Products.java
        ProductData[] destacados = new ProductData[] {
            Products.getCoffeeProducts().get(0), // Meowcha
            Products.getCoffeeProducts().get(1), // Catpuccino
            Products.getDesserts().get(2),       // Gatogalletas
            Products.getDesserts().get(0),       // Pawffins
            Products.getSaltyFood().get(0),      // Empanacat
            Products.getColdDrinks().get(4)      // Pink Paw
        };
        for (ProductData product : destacados) {
            grid.add(new ProductBox(product, () -> fireProductAdded(product)));
        }
        return grid;
    }
}
