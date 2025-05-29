package controller;

import javax.swing.*;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import ui.MainFrame;

public class NavigationManager {
    private final MainFrame mainFrame;
    private final JPanel navBar;
    private final Map<String, String> labelToPanelName;

    public NavigationManager(MainFrame mainFrame, JPanel navBar, Map<String, String> labelToPanelName) {
        this.mainFrame = mainFrame;
        this.navBar = navBar;
        this.labelToPanelName = labelToPanelName;
        attachListeners();
    }

    private void attachListeners() {
        for (Component component : navBar.getComponents()) {
            if (component instanceof JPanel itemPanel) {
                itemPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String label = (String) itemPanel.getClientProperty("navLabel");
                        if (label == null) {
                            System.err.println("Nav item label is null!");
                            return;
                        }
                        navigateTo(label);
                    }
                });
            }
        }
    }

    private void navigateTo(String label) {
        String panelName = labelToPanelName.getOrDefault(label, label);
        mainFrame.showPanel(panelName);
    }
}
