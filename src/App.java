import ui.WelcomeFrame;
import utils.FontsLoader;

public class App {
    public static void main(String[] args) {

        FontsLoader.inicializar(); // Inicializar las fuentes

        // Crear e inicializar la ventana de bienvenida
        WelcomeFrame welcomeFrame = new WelcomeFrame("Catffee");
        welcomeFrame.initialize();
    }
}