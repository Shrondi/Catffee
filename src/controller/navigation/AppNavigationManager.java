package controller.navigation;

import ui.main.MainFrame;
import ui.login.LoginFrame;
import ui.register.RegisterFrame;
import ui.welcome.WelcomeFrame;
import utils.UserStorage;
import javax.swing.*;

/**
 * Gestor de navegación principal para Catffee. Controla el cambio entre frames principales.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
/**
 * Implementación de NavigationHost para gestionar la navegación entre pantallas principales.
 */
public class AppNavigationManager implements NavigationHost {
    private JFrame currentFrame;

    public void start() {
        showWelcomeFrame();
    }

    private void setCurrentFrame(JFrame frame) {
        if (currentFrame != null) {
            currentFrame.setVisible(false);
            currentFrame.dispose();
        }
        currentFrame = frame;
    }

    public void showWelcomeFrame() {
        WelcomeFrame welcomeFrame = new WelcomeFrame("Catffee", this);
        welcomeFrame.setVisible(true);
        setCurrentFrame(welcomeFrame);
    }

    @Override
    public void showMainFrame(UserStorage.User currentUser) {
        MainFrame mainFrame = new MainFrame(this, currentUser);
        mainFrame.setVisible(true);
        setCurrentFrame(mainFrame);
    }

    @Override
    public void showLoginFrame() {
        LoginFrame loginFrame = new LoginFrame("Iniciar sesión", this);
        loginFrame.setVisible(true);
        setCurrentFrame(loginFrame);
    }

    @Override
    public void showRegisterFrame() {
        RegisterFrame registerFrame = new RegisterFrame("Registrarse", this);
        registerFrame.setVisible(true);
        setCurrentFrame(registerFrame);
    }

    @Override
    public void closeCurrentFrame() {
        if (currentFrame != null) {
            currentFrame.setVisible(false);
            currentFrame.dispose();
        }
    }
} 