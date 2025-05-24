import ui.welcome.WelcomeFrame;
import utils.FontsLoader;
import utils.UserStorage;

public class App {
    public static void main(String[] args) {

        FontsLoader.inicializar(); // Inicializar las fuentes
        UserStorage.init("usuarios.txt");

        WelcomeFrame welcomeFrame = new WelcomeFrame("Catffee");
        welcomeFrame.setVisible(true);
    }
}