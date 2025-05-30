package controller.navigation;

import utils.UserStorage;

/**
 * Interfaz para la navegación entre pantallas en Catffee.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
/**
 * Interfaz para gestionar la navegación entre frames principales.
 */
public interface NavigationHost {
    /** Muestra la ventana principal. */
    void showMainFrame(UserStorage.User currentUser);
    /** Muestra la ventana de login. */
    void showLoginFrame();
    /** Muestra la ventana de registro. */
    void showRegisterFrame();
    /** Cierra la ventana actual. */
    void closeCurrentFrame();
} 