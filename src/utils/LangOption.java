package utils;

public class LangOption {
    public final String code;
    public final String label;

    public LangOption(String code, String label) {
        this.code = code;
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    public static LangOption[] getAvailableLanguages() {
        return new LangOption[] {
            new LangOption("es_ES", I18n.t("profile_spanish")),
            new LangOption("en_GB", I18n.t("profile_english"))
        };
    }
} 