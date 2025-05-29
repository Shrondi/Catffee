package utils;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Utilidad para cargar fuentes personalizadas en Catffee desde la carpeta resources/fonts.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
/**
 * Clase utilitaria para cargar fuentes TTF en la aplicación.
 */
public class FontsLoader {

    /**
     * Inicializa y registra todas las fuentes TTF de la carpeta resources/fonts.
     */
    public static void inicializar() {
        File folder = new File("resources/fonts");
        File[] files = folder.listFiles((_, name) -> name.toLowerCase().endsWith(".ttf"));
        if (files == null) return;

        for (File file : files) {
            try {
                Font fuente = Font.createFont(Font.TRUETYPE_FONT, file);
                GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(fuente);
            } catch (FontFormatException | IOException e) {
                System.err.println("Error al cargar la fuente: " + file.getName());
                e.printStackTrace();
            }
        }
    }
}
