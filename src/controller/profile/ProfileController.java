package controller.profile;

import controller.navigation.NavigationHost;
import utils.UserStorage;
import controller.common.LanguageController;

public class ProfileController {
    private final NavigationHost navigationHost;
    private final UserStorage.User currentUser;

    public ProfileController(NavigationHost navigationHost, UserStorage.User currentUser) {
        this.navigationHost = navigationHost;
        this.currentUser = currentUser;
    }

    public void cambiarIdioma(String langCode) {
        LanguageController.cambiarIdioma(langCode, () -> {
            navigationHost.closeCurrentFrame();
            if (navigationHost instanceof controller.navigation.AppNavigationManager manager) {
                manager.showMainFrame(currentUser);
            }
        });
    }
} 