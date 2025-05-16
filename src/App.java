import ui.CatsFrame;
import ui.ErrorFrame;
import ui.MainMenuFrame;
import ui.WelcomeFrame;
import utils.FontsLoader;

public class App {
    public static void main(String[] args) {

        FontsLoader.inicializar(); // Inicializar las fuentes

        // TODO
        // Ahora mismo se van añadiendo las ventanas a la vez para ver como quedan
        // Añadir mas tarde la navegacion entre ventanas
        
        // WelcomeFrame welcomeFrame = new WelcomeFrame("Catffee");
        // welcomeFrame.initialize();

        // ErrorFrame errorFrame = new ErrorFrame("Error");
        // errorFrame.setVisible(true);

        // CatsFrame catsFrame = new CatsFrame();
        // catsFrame.setVisible(true);

        MainMenuFrame mainMenuFrame = new MainMenuFrame();
        mainMenuFrame.setVisible(true);
    }
}