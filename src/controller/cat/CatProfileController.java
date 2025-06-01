package controller.cat;

import ui.cats.CatAdoptListener;
import model.cat.Cat;
import model.cat.CatFactory;
import ui.catProfile.CatProfilePanel;
import ui.main.MainFrame;

/**
 * Controlador para la lógica de visualización y adopción de perfiles de gatos en Catffee.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
public class CatProfileController implements CatAdoptListener {
    private final MainFrame mainFrame;

    public CatProfileController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void onAdoptCat(String catName) {
        Cat profile = CatFactory.getByName(catName);
        if (profile != null) {
            CatProfilePanel panel = new CatProfilePanel(profile);
            mainFrame.showPanel("CAT_PROFILE", panel);
        }
    }
} 