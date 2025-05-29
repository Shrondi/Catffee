package ui.home;

import components.panel.ProductBox;
import model.ProductData;
import model.Products;
import utils.UserStorage;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class HomePanel extends JPanel {
    private final Consumer<ProductData> onProductAdded;

    public HomePanel(Consumer<ProductData> onProductAdded) {
        this.onProductAdded = onProductAdded;
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(0xF7F7F7));
        mainPanel.add(topSection());
        mainPanel.add(productScroll());
        add(mainPanel, BorderLayout.CENTER);
    }

    private void fireProductAdded(ProductData product) {
        if (onProductAdded != null) {
            onProductAdded.accept(product);
        }
    }

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

    private JLabel headerImage() {
        ImageIcon icon = new ImageIcon("resources/images/ui/cabecera_bienvenida.png");
        Image scaled = icon.getImage().getScaledInstance(412, 330, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(scaled));
        label.setAlignmentX(0.5f);
        label.setAlignmentY(0.5f);
        return label;
    }

    private JPanel headerText() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setAlignmentX(0.5f);
        panel.setAlignmentY(0.5f);
        String nombre = UserStorage.getInstance().getCurrentUser().getNombreCompleto();
        JLabel welcomeText = new JLabel(
            String.format("<html><div style='text-align: left;'>Bienvenido,<br><b>%s ☕</b></div></html>", nombre));
        welcomeText.setForeground(Color.WHITE);
        welcomeText.setFont(new Font("Segoe UI Emoji", Font.BOLD, 32));
        welcomeText.setHorizontalAlignment(SwingConstants.LEFT);
        welcomeText.setVerticalAlignment(SwingConstants.BOTTOM);
        welcomeText.setBorder(BorderFactory.createEmptyBorder(0, 40, 40, 0));
        panel.add(welcomeText, BorderLayout.CENTER);
        return panel;
    }

    private JScrollPane productScroll() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(0xF7F7F7));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel destacadosLabel = new JLabel("PRODUCTOS DESTACADOS");
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

    private JPanel productGrid() {
        JPanel grid = new JPanel(new GridLayout(0, 2, 12, 12));
        grid.setBackground(new Color(0xF7F7F7));
        grid.setAlignmentX(Component.CENTER_ALIGNMENT);

        ProductData[] destacados = new ProductData[] {
            Products.getCoffeeProducts().get(0),
            Products.getCoffeeProducts().get(1),
            Products.getDesserts().get(2),
            Products.getDesserts().get(0),
            Products.getSaltyFood().get(0),
            Products.getColdDrinks().get(4)
        };
        for (ProductData product : destacados) {
            grid.add(new ProductBox(product, () -> fireProductAdded(product)));
        }
        return grid;
    }
}
