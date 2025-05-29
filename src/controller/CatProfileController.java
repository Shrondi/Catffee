package controller;

import ui.cats.CatAdoptListener;
import model.CatProfileFactory;
import ui.catProfile.CatProfilePanel;
import javax.swing.JPanel;
import java.util.function.BiConsumer;

public class CatProfileController implements CatAdoptListener {
    private final BiConsumer<String, JPanel> showPanelCallback;

    public CatProfileController(BiConsumer<String, JPanel> showPanelCallback) {
        this.showPanelCallback = showPanelCallback;
    }

    @Override
    public void onAdoptCat(String catName) {
        var profile = CatProfileFactory.getByName(catName);
        if (profile != null) {
            CatProfilePanel panel = new CatProfilePanel(profile);
            showPanelCallback.accept("CAT_PROFILE", panel);
        }
    }
} 