package app;

import utils.FontsLoader;
import utils.UserStorage;
import controller.navigation.AppNavigationManager;

public class App {
    public static void main(String[] args) {
        FontsLoader.inicializar(); // Inicializar las fuentes
        UserStorage.init("usuarios.txt");

        AppNavigationManager navigationManager = new AppNavigationManager();
        navigationManager.start();
    }
}