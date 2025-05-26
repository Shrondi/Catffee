package controller;

import javax.swing.*;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import ui.MainFrame;

public class NavigationManager {
    private final MainFrame mainFrame;
    private final JPanel navBar;

    public NavigationManager(MainFrame mainFrame, JPanel navBar) {
        this.mainFrame = mainFrame;
        this.navBar = navBar;
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
        switch (label) {
            case "Inicio" -> mainFrame.showPanel(MainFrame.HOME);
            case "Carta" -> mainFrame.showPanel(MainFrame.MENU);
            case "Pedido" -> mainFrame.showPanel(MainFrame.ORDER);
            case "Gatos" -> mainFrame.showPanel(MainFrame.CATS);
            default -> System.err.println("Unknown navigation label: " + label);
        }
    }
}
