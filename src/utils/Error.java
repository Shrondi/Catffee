package utils;

import javax.swing.SwingUtilities;
import ui.error.ErrorFrame;

public class Error {
    public static void mostrarErrorCritico(String mensaje) {
        SwingUtilities.invokeLater(() -> {
            ErrorFrame errorFrame = new ErrorFrame("Error crítico");
            errorFrame.setVisible(true);
        });
    }
} 