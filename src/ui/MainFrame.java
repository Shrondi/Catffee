package ui;

import ui.menu.MenuPanel;
import ui.home.HomePanel;
import ui.cats.CatsPanel;
import ui.order.OrderPanel;
import ui.catProfile.CatProfilePanel;
import model.ProductData;
import components.bar.NavigationBar;
import controller.ProductOrderController;
import controller.CatProfileController;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends BaseFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private OrderPanel orderPanel;
    private String currentSection = "Inicio";
    private NavigationBar navBar;

    public static final String HOME = "HOME";
    public static final String MENU = "MENU";
    public static final String CATS = "CATS";
    public static final String ORDER = "ORDER";
    public static final String CAT_PROFILE = "CAT_PROFILE";

    public MainFrame() {
        super("Catffee");

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        setBackground(Color.decode("#F9F9F9"));

        // Crear el controlador del pedido (sin argumentos)
        ProductOrderController orderController = new controller.ProductOrderController();

        // Paneles principales
        HomePanel homePanel = new HomePanel(this::handleProductAdded);
        cardPanel.add(homePanel, HOME);

        orderPanel = new OrderPanel(orderController);
        cardPanel.add(orderPanel, ORDER);

        MenuPanel menuPanel = new MenuPanel(this::handleProductAdded);
        cardPanel.add(menuPanel, MENU);

        CatsPanel catsPanel = new CatsPanel();
        CatProfileController catProfileController = new CatProfileController((panelName, panel) -> {
            cardPanel.add(panel, panelName);
            showPanel(panelName);
        });
        catsPanel.setCatAdoptListener(catProfileController);
        cardPanel.add(catsPanel, CATS);

        add(cardPanel, BorderLayout.CENTER);

        navBar = new components.bar.NavigationBar(currentSection);
        new controller.NavigationManager(this, navBar);
        add(navBar, BorderLayout.SOUTH);
    }

    private void handleProductAdded(ProductData product) {
        orderPanel.addProductCard(product);
    }

    public void showPanel(String panelName) {
        cardLayout.show(cardPanel, panelName);
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
}
