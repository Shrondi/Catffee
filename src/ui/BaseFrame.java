package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Frame base para todas las ventanas de Catffee. Configura tamaño, cierre y apariencia general.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
/**
 * Frame base para la aplicación.
 */
public class BaseFrame extends JFrame {
    /**
     * Crea el frame base.
     * @param title Título de la ventana
     */
    public BaseFrame(String title) {
        super(title);
        setSize(412, 917);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); // Deshabilitar el cambio de tamaño
        setBackground(Color.decode("#F9F9F9"));
    }
}
