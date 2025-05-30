package controller.user;

import ui.welcome.WelcomeFrame;
import controller.navigation.NavigationHost;
import utils.I18n;
import java.util.Locale;
import controller.common.LanguageController;

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
     * @param langCode Código de idioma a establecer
     */
    public void cambiarIdioma(String langCode) {
        LanguageController.cambiarIdioma(langCode, () -> {
            welcomeFrame.dispose();
            if (navigationHost instanceof controller.navigation.AppNavigationManager manager) {
                manager.start();
            }
        });
    }
} 