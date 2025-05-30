package controller.common;

import utils.I18n;
import java.util.Locale;

public class LanguageController {
    public static void cambiarIdioma(String langCode, Runnable reloadView) {
        Locale locale;
        switch (langCode) {
            case "es_ES" -> locale = new Locale.Builder().setLanguage("es").setRegion("ES").build();
            case "en_GB" -> locale = Locale.UK;
            default -> locale = Locale.getDefault();
        }
        I18n.setLocale(locale);
        reloadView.run();
    }
} 