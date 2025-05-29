package controller.navigation;

import ui.main.MainFrame;
import ui.login.LoginFrame;
import ui.register.RegisterFrame;
import ui.welcome.WelcomeFrame;

import javax.swing.*;

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
    public void showMainFrame() {
        MainFrame mainFrame = new MainFrame(this);
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