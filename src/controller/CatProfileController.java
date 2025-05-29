package controller;

import ui.cats.CatAdoptListener;
import model.CatProfile;
import model.CatProfileFactory;
import ui.catProfile.CatProfilePanel;
import ui.MainFrame;

public class CatProfileController implements CatAdoptListener {
    private final MainFrame mainFrame;

    public CatProfileController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void onAdoptCat(String catName) {
        CatProfile profile = CatProfileFactory.getByName(catName);
        if (profile != null) {
            CatProfilePanel panel = new CatProfilePanel(profile);
            mainFrame.showPanel("CAT_PROFILE", panel);
        }
    }
} 