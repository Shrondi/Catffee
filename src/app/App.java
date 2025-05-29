package app;

import utils.FontsLoader;
import utils.UserStorage;
import controller.navigation.AppNavigationManager;

/**
 * Clase principal de la aplicación Catffee. Inicializa fuentes, usuarios y lanza la navegación principal.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
/**
 * Punto de entrada de la aplicación Catffee.
 */
public class App {
    /**
     * Método principal. Inicializa fuentes, usuarios y lanza la navegación.
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        FontsLoader.inicializar(); // Inicializar las fuentes
        UserStorage.init("usuarios.txt");

        AppNavigationManager navigationManager = new AppNavigationManager();
        navigationManager.start();
    }
}