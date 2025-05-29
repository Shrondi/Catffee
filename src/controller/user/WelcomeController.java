package controller;

import ui.welcome.WelcomeFrame;
import controller.NavigationHost;

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