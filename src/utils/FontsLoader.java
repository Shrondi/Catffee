package utils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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
        try {
            String[] fuentes = {
                "seguiemj.ttf",
                "Sora-Bold.ttf",
                "Sora-Light.ttf",
                "Sora-Regular.ttf",
                "Sora-SemiBold.ttf",
                "Poppins-SemiBold.ttf",
                "Roboto-Bold.ttf",
                "Roboto-Italic.ttf",
                "Roboto-Regular.ttf",
                "Fredoka-SemiBold.ttf",
                "Poppins-Bold.ttf",
                "Poppins-Medium.ttf",
                "Poppins-Regular.ttf",
                "Fredoka-Medium.ttf",
                "Fredoka-Regular.ttf"
            };
            for (String fuente : fuentes) {
                try (InputStream is = FontsLoader.class.getResourceAsStream("/fonts/" + fuente)) {
                    if (is == null) {
                        System.err.println("No se encontró la fuente: " + fuente);
                        continue;
                    }
                    Font font = Font.createFont(Font.TRUETYPE_FONT, is);
                    GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
                } catch (Exception e) {
                    System.err.println("Error al cargar la fuente: " + fuente);
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.err.println("Error al acceder a la carpeta de fuentes");
            e.printStackTrace();
        }
    }
}
