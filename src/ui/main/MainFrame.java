package ui.main;

import ui.menu.MenuPanel;
import ui.home.HomePanel;
import ui.cats.CatsPanel;
import ui.order.OrderPanel;
import ui.profile.ProfilePanel;
import model.ProductData;
import components.bar.NavigationBar;
import controller.order.ProductOrderController;
import controller.cat.CatProfileController;
import controller.navigation.NavigationHost;
import controller.navigation.PanelNavigationManager;
import utils.UserStorage;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Ventana principal de Catffee. Gestiona la navegación entre secciones (Inicio, Carta, Gatos, Pedido, Perfil de Gato).
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
/**
 * Frame principal de la aplicación Catffee.
 */
public class MainFrame extends ui.BaseFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private OrderPanel orderPanel;
    private String currentSection = "Inicio";
    private NavigationBar navBar;
    private final NavigationHost navigationHost;
    private final UserStorage.User currentUser;

    public static final String HOME = "HOME";
    public static final String MENU = "MENU";
    public static final String CATS = "CATS";
    public static final String ORDER = "ORDER";
    public static final String CAT_PROFILE = "CAT_PROFILE";
    public static final String PROFILE = "PROFILE";

    public MainFrame(NavigationHost navigationHost, UserStorage.User currentUser) {
        super("Catffee");
        this.navigationHost = navigationHost;
        this.currentUser = currentUser;
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        setBackground(Color.decode("#F9F9F9"));

        initHomePanel();
        initOrderPanel();
        initMenuPanel();
        initCatsPanel();
        initProfilePanel();
        
        add(cardPanel, BorderLayout.CENTER);

        initNavigationBar();
    }

    private void initHomePanel() {
        HomePanel homePanel = new HomePanel(this::handleProductAdded, currentUser.getNombreCompleto());
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

    private void initProfilePanel() {
        ProfilePanel profilePanel = new ProfilePanel(currentUser, navigationHost);
        cardPanel.add(profilePanel, PROFILE);
    }

    private void initNavigationBar() {
        navBar = new NavigationBar(HOME);
        Map<String, String> labelToPanelName = Map.of(
            "HOME", HOME,
            "MENU", MENU,
            "ORDER", ORDER,
            "CATS", CATS,
            "CAT_PROFILE", CAT_PROFILE,
            "PROFILE", PROFILE
        );
        new PanelNavigationManager(this, navBar, labelToPanelName);
        add(navBar, BorderLayout.SOUTH);
    }

    private void updateNavBar(String panelName) {
        navBar.setSelectedSection(panelName);
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
