package controller;

import javax.swing.*;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.home.HomeFrame;
import ui.cats.CatsFrame;
import ui.menu.MenuFrame;

public class NavigationManager {

    private final JFrame currentFrame;
    private final JPanel navBar;

    public NavigationManager(JFrame currentFrame, JPanel navBar) {
        this.currentFrame = currentFrame;
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
        currentFrame.dispose();
        switch (label) {
            case "Inicio" -> new HomeFrame("Inicio").setVisible(true);
            case "Carta" -> new MenuFrame("Carta").setVisible(true);
            //case "Pedido" -> new PedidoFrame().setVisible(true);
            case "Gatos" -> new CatsFrame("Gatos").setVisible(true);
            //case "Perfil" -> new PerfilFrame().setVisible(true);
            default -> System.err.println("Unknown navigation label: " + label);
        }
    }
}
