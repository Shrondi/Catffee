package controller.user;

import ui.welcome.WelcomeFrame;
import controller.navigation.NavigationHost;
import utils.I18n;
import java.util.Locale;

/**
 * Controlador para la lógica de la pantalla de bienvenida en Catffee.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
public class WelcomeController {
    private final WelcomeFrame welcomeFrame;
    private final NavigationHost navigationHost;

    public WelcomeController(WelcomeFrame welcomeFrame, NavigationHost navigationHost) {
        this.welcomeFrame = welcomeFrame;
        this.navigationHost = navigationHost;
    }

    public void irALogin() {
        navigationHost.showLoginFrame();
    }

    /**
     * Cambia el idioma de la aplicación y recarga la pantalla de bienvenida.
     * @param locale Nuevo locale a establecer
     */
    public void cambiarIdioma(Locale locale) {
        I18n.setLocale(locale);
        welcomeFrame.dispose();
        if (navigationHost instanceof controller.navigation.AppNavigationManager manager) {
            manager.start();
        }
    }
} 