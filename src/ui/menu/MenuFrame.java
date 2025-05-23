package ui.menu;

import components.bar.NavigationBar;
import components.button.RoundedButton;
import components.panel.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MenuFrame extends JFrame {

    public MenuFrame() {
        setTitle("Carta");
        setSize(412, 917);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setContentPane(buildMainPanel());

        add(new NavigationBar("Carta"), BorderLayout.SOUTH);
    }

    private JPanel buildMainPanel() {
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setLayout(new BorderLayout());

        // Imagen de fondo
        ImageIcon backgroundIcon = new ImageIcon("resources/images/menu_bg.jpeg");
        Image scaledBackground = backgroundIcon.getImage().getScaledInstance(650, 917, Image.SCALE_SMOOTH);
        backgroundIcon = new ImageIcon(scaledBackground);
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setLayout(new BorderLayout());

        // Panel principal encima del fondo
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(buildCategorySection("Cafés", getCoffeeProducts()));
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(buildCategorySection("Bebidas frías", getColdDrinks()));
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(buildCategorySection("Dulces", getDesserts()));
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(buildCategorySection("Salados", getSalad()));
        contentPanel.add(Box.createVerticalStrut(100));

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //Mostrar la barra de desplazamiento vertical con ancho estándar
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.getVerticalScrollBar().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Usar JLayeredPane para superponer el contenido sobre la imagen de fondo
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(412, 917));

        backgroundLabel.setBounds(0, 0, 412, 917);
        layeredPane.add(backgroundLabel, Integer.valueOf(0));

        JPanel layeredContent = new JPanel(new BorderLayout());
        layeredContent.setOpaque(false);
        layeredContent.setBounds(0, 0, 412, 917);
        layeredContent.add(buildTopBar(), BorderLayout.NORTH);
        layeredContent.add(scrollPane, BorderLayout.CENTER);

        layeredPane.add(layeredContent, Integer.valueOf(1));

        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(layeredPane, BorderLayout.CENTER);

        contentPanel.setMaximumSize(new Dimension(412, Integer.MAX_VALUE));

        return backgroundPanel;
    }

    private JPanel buildTopBar() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.decode("#252424"));
        topPanel.setPreferredSize(new Dimension(413, 85));
        topPanel.setMaximumSize(new Dimension(413, 85));

        JLabel titleLabel = new JLabel("Carta");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 30));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(23, 31, 23, 213));

        topPanel.add(titleLabel, BorderLayout.CENTER);
        return topPanel;
    }

    private JPanel buildCategorySection(String title, List<JPanel> products) {
        JPanel sectionPanel = new JPanel();
        sectionPanel.setOpaque(false);
        sectionPanel.setLayout(new BoxLayout(sectionPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Poppins SemiBold", Font.PLAIN, 25));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));

        JSeparator separator = new JSeparator();
        separator.setForeground(Color.BLACK);
        separator.setMaximumSize(new Dimension(320, 2));
        separator.setAlignmentX(Component.CENTER_ALIGNMENT);
        separator.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        JPanel productGrid = new JPanel(new GridLayout(0, 2, 10, 15));
        productGrid.setOpaque(false);
        productGrid.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        for (JPanel productBox : products) {
            productGrid.add(productBox);
        }

        sectionPanel.add(titleLabel);
        sectionPanel.add(Box.createVerticalStrut(5));
        sectionPanel.add(separator);
        sectionPanel.add(Box.createVerticalStrut(10));
        sectionPanel.add(productGrid);

        return sectionPanel;
    }

    private List<JPanel> getCoffeeProducts() {
        List<JPanel> list = new ArrayList<>();
        list.add(createProductBox("Meowcha", "Chocolate & Espresso", "3.50€", "resources/images/meowcha.png"));
        list.add(createProductBox("Catpuccino", "Espuma Suave & Canela", "3.20€", "resources/images/catpuccino.png"));
        list.add(createProductBox("Purrlate", "Vainilla & Leche Cream", "3.40€", "resources/images/purrlate.png"));
        list.add(createProductBox("Whisker Beans", "Blend intenso", "2.80€", "resources/images/whisker_beans.png"));
        list.add(createProductBox("Nap & Sip", "Infusión de Tarde", "2.60€", "resources/images/nap_sip.png"));
        list.add(createProductBox("Feline Roast", "Café de Filtro Potente", "2.50€", "resources/images/feline_roast.png"));
        list.add(createProductBox("Chai-miau", "Chai Latte Especiado", "3.60€", "resources/images/chai_miau.png"));
        list.add(createProductBox("Ronromiel", "Manzanilla & Miel", "2.80€", "resources/images/ronromiel.png"));
        list.add(createProductBox("Napresso", "Espresso Doble", "2.40€", "resources/images/napresso.png"));
        list.add(createProductBox("Macatchiato", "Toqye de Caramelo", "3.30€", "resources/images/macatchiato.png"));
        return list;
    }

    private List<JPanel> getColdDrinks() {
        List<JPanel> list = new ArrayList<>();
        list.add(createProductBox("Iced Whisker", "Espresso frío", "3.60€", "resources/images/iced_whisker.png"));
        list.add(createProductBox("Gato Tonic", "Espresso con tónica", "3.80€", "resources/images/gato_tonic.png"));
        list.add(createProductBox("Cat-a-cola", "Refresco cola", "3.00€", "resources/images/cat_a_cola.png"));
        list.add(createProductBox("MiauMilk Shake", "Batido oreo", "4.20€", "resources/images/miaumilk_shake.png"));
        list.add(createProductBox("Pink Paw", "Limonada frutos", "3.20€", "resources/images/pink_paw.png"));
        return list;
    }

    private List<JPanel> getDesserts() {
        List<JPanel> list = new ArrayList<>();
        list.add(createProductBox("Pawffins", "Muffin choco", "2.80€", "resources/images/pawffins.png"));
        list.add(createProductBox("Tarta Ronroneo", "Cheesecake", "4.20€", "resources/images/tarta_ronroneo.png"));
        list.add(createProductBox("Gatogalletas", "Crumble-cookies", "2.50€", "resources/images/gatogalletas.png"));
        list.add(createProductBox("Churros Miau", "Churros con toppings", "3.50€", "resources/images/churros_miau.png"));
        list.add(createProductBox("Brownie Bigotes", "Nueces & helado", "4.80€", "resources/images/brownie_bigotes.png"));
        return list;
    }

    private List<JPanel> getSalad() {
        List<JPanel> list = new ArrayList<>();
        list.add(createProductBox("Empanacat", "Carne o verdura", "2.80€", "resources/images/empanacat.png"));
        list.add(createProductBox("Toasty Cat", "Aguacate, huevos...", "4.80€", "resources/images/toasty_cat.png"));
        list.add(createProductBox("Croissandwich", "Jamón, queso...", "4.20€", "resources/images/croissandwich.png"));
        list.add(createProductBox("Tabla Gatera", "Variedad quesos", "5.50€", "resources/images/tabla_gatera.png"));
        list.add(createProductBox("Wrap Whisker", "Pollo, verduras...", "4.90€", "resources/images/wrap_whisker.png"));
        return list;
    }

    private JPanel createProductBox(String title, String subtitle, String price, String imagePath) {
        RoundedPanel box = new RoundedPanel(16);
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setPreferredSize(new Dimension(160, 240));
        box.setMaximumSize(new Dimension(160, 240));
        box.setBackground(Color.WHITE);
        box.add(Box.createVerticalStrut(10));

        ImageIcon icon = new ImageIcon(imagePath);
        Image scaled = icon.getImage().getScaledInstance(140, 130, Image.SCALE_SMOOTH);
        JLabel img = new JLabel(new ImageIcon(scaled));
        img.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 22));
        img.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 16));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 22));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setFont(new Font("Sora Regular", Font.PLAIN, 12));
        subtitleLabel.setForeground(Color.GRAY);
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 22));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel priceLabel = new JLabel(price);
        priceLabel.setFont(new Font("Poppins", Font.BOLD, 18));

        RoundedButton plusButton = new RoundedButton("+", 8);
        plusButton.setFont(new Font("Poppins", Font.BOLD, 15));
        plusButton.setBackground(new Color(0xC67C4E));
        plusButton.setForeground(Color.WHITE);
        plusButton.setContentAreaFilled(false);
        plusButton.setOpaque(false);
        plusButton.setBorderPainted(false);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 0));
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        bottomPanel.add(priceLabel);
        bottomPanel.add(plusButton);

        box.add(img);
        box.add(Box.createVerticalStrut(5));
        box.add(titleLabel);
        box.add(Box.createVerticalStrut(5));
        box.add(subtitleLabel);
        box.add(Box.createVerticalStrut(5));
        box.add(bottomPanel);

        return box;
    }
}