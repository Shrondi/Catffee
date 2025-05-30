package model;

import utils.I18n;

/**
 * Clase utilitaria para obtener los perfiles de gatos disponibles en Catffee.
 * Proporciona métodos para cada gato.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
public class CatProfiles {
    /**
     * Perfil de Abi.
     * @return CatProfile de Abi
     */
    public static CatProfile abi() {
        return new CatProfile.Builder()
            .name(I18n.t("catprofile_abi_name"))
            .age(I18n.t("catprofile_abi_age"))
            .gender(I18n.t("catprofile_abi_gender"))
            .about(I18n.t("catprofile_abi_about"))
            .ideal(I18n.t("catprofile_abi_ideal"))
            .imagePaths(
                "resources/images/cats/abi/abi1.png",
                "resources/images/cats/abi/abi2.png",
                "resources/images/cats/abi/abi3.png"
            )
            .build();
    }

    /**
     * Perfil de Alfil.
     * @return CatProfile de Alfil
     */
    public static CatProfile alfil() {
        return new CatProfile.Builder()
            .name(I18n.t("catprofile_alfil_name"))
            .age(I18n.t("catprofile_alfil_age"))
            .gender(I18n.t("catprofile_alfil_gender"))
            .about(I18n.t("catprofile_alfil_about"))
            .ideal(I18n.t("catprofile_alfil_ideal"))
            .imagePaths(
                "resources/images/cats/alfil/alfil1.png",
                "resources/images/cats/alfil/alfil2.png",
                "resources/images/cats/alfil/alfil3.png"
            )
            .build();
    }

    /**
     * Perfil de Cherry.
     * @return CatProfile de Cherry
     */
    public static CatProfile cherry() {
        return new CatProfile.Builder()
            .name(I18n.t("catprofile_cherry_name"))
            .age(I18n.t("catprofile_cherry_age"))
            .gender(I18n.t("catprofile_cherry_gender"))
            .about(I18n.t("catprofile_cherry_about"))
            .ideal(I18n.t("catprofile_cherry_ideal"))
            .imagePaths(
                "resources/images/cats/cherry/cherry1.png",
                "resources/images/cats/cherry/cherry2.png",
                "resources/images/cats/cherry/cherry3.png"
            )
            .build();
    }

    /**
     * Perfil de Flush.
     * @return CatProfile de Flush
     */
    public static CatProfile flush() {
        return new CatProfile.Builder()
            .name(I18n.t("catprofile_flush_name"))
            .age(I18n.t("catprofile_flush_age"))
            .gender(I18n.t("catprofile_flush_gender"))
            .about(I18n.t("catprofile_flush_about"))
            .ideal(I18n.t("catprofile_flush_ideal"))
            .imagePaths(
                "resources/images/cats/flush/flush1.png",
                "resources/images/cats/flush/flush2.png",
                "resources/images/cats/flush/flush3.png"
            )
            .build();
    }

    /**
     * Perfil de Gulliver.
     * @return CatProfile de Gulliver
     */
    public static CatProfile gulliver() {
        return new CatProfile.Builder()
            .name(I18n.t("catprofile_gulliver_name"))
            .age(I18n.t("catprofile_gulliver_age"))
            .gender(I18n.t("catprofile_gulliver_gender"))
            .about(I18n.t("catprofile_gulliver_about"))
            .ideal(I18n.t("catprofile_gulliver_ideal"))
            .imagePaths(
                "resources/images/cats/gulliver/gulliver1.png",
                "resources/images/cats/gulliver/gulliver2.png",
                "resources/images/cats/gulliver/gulliver3.png"
            )
            .build();
    }

    /**
     * Perfil de Mazinger.
     * @return CatProfile de Mazinger
     */
    public static CatProfile mazinger() {
        return new CatProfile.Builder()
            .name(I18n.t("catprofile_mazinger_name"))
            .age(I18n.t("catprofile_mazinger_age"))
            .gender(I18n.t("catprofile_mazinger_gender"))
            .about(I18n.t("catprofile_mazinger_about"))
            .ideal(I18n.t("catprofile_mazinger_ideal"))
            .imagePaths(
                "resources/images/cats/mazinger/mazinger1.png",
                "resources/images/cats/mazinger/mazinger2.png",
                "resources/images/cats/mazinger/mazinger3.png"
            )
            .build();
    }

    /**
     * Perfil de Membrillo.
     * @return CatProfile de Membrillo
     */
    public static CatProfile membrillo() {
        return new CatProfile.Builder()
            .name(I18n.t("catprofile_membrillo_name"))
            .age(I18n.t("catprofile_membrillo_age"))
            .gender(I18n.t("catprofile_membrillo_gender"))
            .about(I18n.t("catprofile_membrillo_about"))
            .ideal(I18n.t("catprofile_membrillo_ideal"))
            .imagePaths(
                "resources/images/cats/membrillo/membrillo1.png",
                "resources/images/cats/membrillo/membrillo2.png",
                "resources/images/cats/membrillo/membrillo3.png"
            )
            .build();
    }

    /**
     * Perfil de Sylvestre.
     * @return CatProfile de Sylvestre
     */
    public static CatProfile sylvestre() {
        return new CatProfile.Builder()
            .name(I18n.t("catprofile_sylvestre_name"))
            .age(I18n.t("catprofile_sylvestre_age"))
            .gender(I18n.t("catprofile_sylvestre_gender"))
            .about(I18n.t("catprofile_sylvestre_about"))
            .ideal(I18n.t("catprofile_sylvestre_ideal"))
            .imagePaths(
                "resources/images/cats/sylvestre/sylvestre1.png",
                "resources/images/cats/sylvestre/sylvestre2.png",
                "resources/images/cats/sylvestre/sylvestre3.png"
            )
            .build();
    }
}
