package ui.menu;

import components.panel.ProductBox;
import model.ProductData;
import model.Products;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class MenuPanel extends JPanel {
    private final Consumer<ProductData> onProductAdded;

    public MenuPanel(Consumer<ProductData> onProductAdded) {
        this.onProductAdded = onProductAdded;
        setLayout(new BorderLayout());
        add(mainPanel(), BorderLayout.CENTER);
    }

    private JPanel mainPanel() {
        JPanel backgroundPanel = new JPanel(new BorderLayout());

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(412, 917));

        // Fondo con imagen
        JLabel backgroundLabel = background("resources/images/menu_bg.jpeg", 412, 917);
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

    private JLabel background(String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(scaled));
        label.setLayout(new BorderLayout());
        label.setBounds(0, 0, width, height);
        return label;
    }

    private JPanel topBar() {
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(Color.decode("#252424"));
        topBar.setPreferredSize(new Dimension(413, 85));
        topBar.setMaximumSize(new Dimension(413, 85));

        JLabel titleLabel = new JLabel("Carta");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 30));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(23, 31, 23, 213));
        topBar.add(titleLabel, BorderLayout.CENTER);

        return topBar;
    }

    private JScrollPane scrollContent() {
        JPanel container = new JPanel();
        container.setOpaque(false);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(Box.createVerticalStrut(10));
        container.add(categorySection("Cafés", Products.getCoffeeProducts()));
        container.add(Box.createVerticalStrut(20));
        container.add(categorySection("Bebidas frías", Products.getColdDrinks()));
        container.add(Box.createVerticalStrut(20));
        container.add(categorySection("Dulces", Products.getDesserts()));
        container.add(Box.createVerticalStrut(20));
        container.add(categorySection("Salados", Products.getSaltyFood()));
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

    private JPanel categorySection(String title, List<ProductData> products) {
        JPanel section = new JPanel();
        section.setOpaque(false);
        section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));

        JLabel sectionTitle = new JLabel(title);
        sectionTitle.setFont(new Font("Poppins SemiBold", Font.PLAIN, 25));
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
            grid.add(new ProductBox(p, () -> fireProductAdded(p)));
        }

        section.add(sectionTitle);
        section.add(Box.createVerticalStrut(5));
        section.add(separator);
        section.add(Box.createVerticalStrut(10));
        section.add(grid);

        return section;
    }

    private void fireProductAdded(ProductData product) {
        if (onProductAdded != null) {
            onProductAdded.accept(product);
        }
    }
}
