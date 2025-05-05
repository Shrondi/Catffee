package utils;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontsLoader {

    public static void inicializar() {
        try {
            File folder = new File("resources/fonts");
            File[] files = folder.listFiles((_, name) -> name.toLowerCase().endsWith(".ttf"));
            if (files != null) {
                for (File file : files) {
                    Font fuente = Font.createFont(Font.TRUETYPE_FONT, file);
                    GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(fuente);
                }
            }
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}
