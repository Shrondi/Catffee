package controller.user;

import ui.welcome.WelcomeFrame;
import controller.navigation.NavigationHost;

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
} 