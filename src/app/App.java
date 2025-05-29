import ui.welcome.WelcomeFrame;
import utils.FontsLoader;
import utils.UserStorage;
import controller.AppNavigationManager;

public class App {
    public static void main(String[] args) {
        FontsLoader.inicializar(); // Inicializar las fuentes
        UserStorage.init("usuarios.txt");

        AppNavigationManager navigationManager = new AppNavigationManager();
        navigationManager.start();
    }
}