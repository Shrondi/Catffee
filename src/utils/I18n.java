package utils;

import java.util.*;

public class I18n {
    private static Locale currentLocale = Locale.getDefault();
    private static ResourceBundle bundle = ResourceBundle.getBundle("bundles.Bundle", currentLocale);

    public static void setLocale(Locale locale) {
        currentLocale = locale;
        bundle = ResourceBundle.getBundle("bundles.Bundle", currentLocale);
    }

    public static String t(String key) {
        return bundle.getString(key);
    }

    public static Locale getCurrentLocale() {
        return currentLocale;
    }
}