package ui;

import ui.menu.MenuPanel;
import ui.home.HomePanel;
import ui.cats.CatsPanel;
import ui.order.OrderPanel;
import model.ProductData;
import components.bar.NavigationBar;
import controller.ProductOrderController;
import controller.CatProfileController;
import controller.NavigationHost;
import controller.NavigationManager;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MainFrame extends BaseFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private OrderPanel orderPanel;
    private String currentSection = "Inicio";
    private NavigationBar navBar;
    private final NavigationHost navigationHost;

    public static final String HOME = "HOME";
    public static final String MENU = "MENU";
    public static final String CATS = "CATS";
    public static final String ORDER = "ORDER";
    public static final String CAT_PROFILE = "CAT_PROFILE";

    public MainFrame(NavigationHost navigationHost) {
        super("Catffee");
        this.navigationHost = navigationHost;
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        setBackground(Color.decode("#F9F9F9"));

        initHomePanel();
        initOrderPanel();
        initMenuPanel();
        initCatsPanel();
        add(cardPanel, BorderLayout.CENTER);

        initNavigationBar();
    }

    private void initHomePanel() {
        HomePanel homePanel = new HomePanel(this::handleProductAdded);
        cardPanel.add(homePanel, HOME);
    }

    private void initOrderPanel() {
        ProductOrderController orderController = new ProductOrderController();
        orderPanel = new OrderPanel(orderController);
        cardPanel.add(orderPanel, ORDER);
    }

    private void initMenuPanel() {
        MenuPanel menuPanel = new MenuPanel(this::handleProductAdded);
        cardPanel.add(menuPanel, MENU);
    }

    private void initCatsPanel() {
        CatsPanel catsPanel = new CatsPanel();
        CatProfileController catProfileController = new CatProfileController(this);
        catsPanel.setCatAdoptListener(catProfileController);
        cardPanel.add(catsPanel, CATS);
    }

    private void initNavigationBar() {
        navBar = new NavigationBar(currentSection);
        Map<String, String> labelToPanelName = Map.of(
            "Inicio", HOME,
            "Carta", MENU,
            "Pedido", ORDER,
            "Gatos", CATS
        );
        new NavigationManager(this, navBar, labelToPanelName);
        add(navBar, BorderLayout.SOUTH);
    }

    private void updateNavBar(String panelName) {
        switch (panelName) {
            case HOME -> currentSection = "Inicio";
            case MENU -> currentSection = "Carta";
            case ORDER -> currentSection = "Pedido";
            case CATS -> currentSection = "Gatos";
            case CAT_PROFILE -> currentSection = "Gatos";
            default -> currentSection = "";
        }
        navBar.setSelectedSection(currentSection);
    }

    public void showPanel(String panelName) {
        cardLayout.show(cardPanel, panelName);
        updateNavBar(panelName);
    }

    private void handleProductAdded(ProductData product) {
        orderPanel.addProductCard(product);
    }

    public void showPanel(String panelName, JPanel panel) {
        // Elimina el panel anterior con el mismo nombre si existe
        Component oldPanel = null;
        for (Component comp : cardPanel.getComponents()) {
            if (panelName.equals(comp.getName())) {
                oldPanel = comp;
                break;
            }
        }
        if (oldPanel != null) {
            cardPanel.remove(oldPanel);
        }
        panel.setName(panelName);
        cardPanel.add(panel, panelName);
        cardLayout.show(cardPanel, panelName);
    }
}
