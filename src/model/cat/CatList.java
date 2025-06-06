package model.cat;

import utils.I18n;

/**
 * Clase utilitaria para obtener los perfiles de gatos disponibles en Catffee.
 * Proporciona métodos para cada gato.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
public class CatList {
    /**
     * Perfil de Abi.
     * @return CatProfile de Abi
     */
    public static Cat abi() {
        return new Cat.Builder()
            .name(I18n.getTranslation("catprofile_abi_name"))
            .age(I18n.getTranslation("catprofile_abi_age"))
            .gender(I18n.getTranslation("catprofile_abi_gender"))
            .about(I18n.getTranslation("catprofile_abi_about"))
            .ideal(I18n.getTranslation("catprofile_abi_ideal"))
            .imagePaths(
                CatList.class.getClassLoader().getResource("images/cats/abi/abi1.png"),
                CatList.class.getClassLoader().getResource("images/cats/abi/abi2.png"),
                CatList.class.getClassLoader().getResource("images/cats/abi/abi3.png")
            )
            .build();
    }

    /**
     * Perfil de Alfil.
     * @return CatProfile de Alfil
     */
    public static Cat alfil() {
        return new Cat.Builder()
            .name(I18n.getTranslation("catprofile_alfil_name"))
            .age(I18n.getTranslation("catprofile_alfil_age"))
            .gender(I18n.getTranslation("catprofile_alfil_gender"))
            .about(I18n.getTranslation("catprofile_alfil_about"))
            .ideal(I18n.getTranslation("catprofile_alfil_ideal"))
            .imagePaths(
                CatList.class.getClassLoader().getResource("images/cats/alfil/alfil1.png"),
                CatList.class.getClassLoader().getResource("images/cats/alfil/alfil2.png"),
                CatList.class.getClassLoader().getResource("images/cats/alfil/alfil3.png")
            )
            .build();
    }

    /**
     * Perfil de Cherry.
     * @return CatProfile de Cherry
     */
    public static Cat cherry() {
        return new Cat.Builder()
            .name(I18n.getTranslation("catprofile_cherry_name"))
            .age(I18n.getTranslation("catprofile_cherry_age"))
            .gender(I18n.getTranslation("catprofile_cherry_gender"))
            .about(I18n.getTranslation("catprofile_cherry_about"))
            .ideal(I18n.getTranslation("catprofile_cherry_ideal"))
            .imagePaths(
                CatList.class.getClassLoader().getResource("images/cats/cherry/cherry1.png"),
                CatList.class.getClassLoader().getResource("images/cats/cherry/cherry2.png"),
                CatList.class.getClassLoader().getResource("images/cats/cherry/cherry3.png")
            )
            .build();
    }

    /**
     * Perfil de Flush.
     * @return CatProfile de Flush
     */
    public static Cat flush() {
        return new Cat.Builder()
            .name(I18n.getTranslation("catprofile_flush_name"))
            .age(I18n.getTranslation("catprofile_flush_age"))
            .gender(I18n.getTranslation("catprofile_flush_gender"))
            .about(I18n.getTranslation("catprofile_flush_about"))
            .ideal(I18n.getTranslation("catprofile_flush_ideal"))
            .imagePaths(
                CatList.class.getClassLoader().getResource("images/cats/flush/flush1.png"),
                CatList.class.getClassLoader().getResource("images/cats/flush/flush2.png"),
                CatList.class.getClassLoader().getResource("images/cats/flush/flush3.png")
            )
            .build();
    }

    /**
     * Perfil de Gulliver.
     * @return CatProfile de Gulliver
     */
    public static Cat gulliver() {
        return new Cat.Builder()
            .name(I18n.getTranslation("catprofile_gulliver_name"))
            .age(I18n.getTranslation("catprofile_gulliver_age"))
            .gender(I18n.getTranslation("catprofile_gulliver_gender"))
            .about(I18n.getTranslation("catprofile_gulliver_about"))
            .ideal(I18n.getTranslation("catprofile_gulliver_ideal"))
            .imagePaths(
                CatList.class.getClassLoader().getResource("images/cats/gulliver/gulliver1.png"),
                CatList.class.getClassLoader().getResource("images/cats/gulliver/gulliver2.png"),
                CatList.class.getClassLoader().getResource("images/cats/gulliver/gulliver3.png")
            )
            .build();
    }

    /**
     * Perfil de Mazinger.
     * @return CatProfile de Mazinger
     */
    public static Cat mazinger() {
        return new Cat.Builder()
            .name(I18n.getTranslation("catprofile_mazinger_name"))
            .age(I18n.getTranslation("catprofile_mazinger_age"))
            .gender(I18n.getTranslation("catprofile_mazinger_gender"))
            .about(I18n.getTranslation("catprofile_mazinger_about"))
            .ideal(I18n.getTranslation("catprofile_mazinger_ideal"))
            .imagePaths(
                CatList.class.getClassLoader().getResource("images/cats/mazinger/mazinger1.png"),
                CatList.class.getClassLoader().getResource("images/cats/mazinger/mazinger2.png"),
                CatList.class.getClassLoader().getResource("images/cats/mazinger/mazinger3.png")
            )
            .build();
    }

    /**
     * Perfil de Membrillo.
     * @return CatProfile de Membrillo
     */
    public static Cat membrillo() {
        return new Cat.Builder()
            .name(I18n.getTranslation("catprofile_membrillo_name"))
            .age(I18n.getTranslation("catprofile_membrillo_age"))
            .gender(I18n.getTranslation("catprofile_membrillo_gender"))
            .about(I18n.getTranslation("catprofile_membrillo_about"))
            .ideal(I18n.getTranslation("catprofile_membrillo_ideal"))
            .imagePaths(
                CatList.class.getClassLoader().getResource("images/cats/membrillo/membrillo1.png"),
                CatList.class.getClassLoader().getResource("images/cats/membrillo/membrillo2.png"),
                CatList.class.getClassLoader().getResource("images/cats/membrillo/membrillo3.png")
            )
            .build();
    }

    /**
     * Perfil de Sylvestre.
     * @return CatProfile de Sylvestre
     */
    public static Cat sylvestre() {
        return new Cat.Builder()
            .name(I18n.getTranslation("catprofile_sylvestre_name"))
            .age(I18n.getTranslation("catprofile_sylvestre_age"))
            .gender(I18n.getTranslation("catprofile_sylvestre_gender"))
            .about(I18n.getTranslation("catprofile_sylvestre_about"))
            .ideal(I18n.getTranslation("catprofile_sylvestre_ideal"))
            .imagePaths(
                CatList.class.getClassLoader().getResource("images/cats/sylvestre/sylvestre1.png"),
                CatList.class.getClassLoader().getResource("images/cats/sylvestre/sylvestre2.png"),
                CatList.class.getClassLoader().getResource("images/cats/sylvestre/sylvestre3.png")
            )
            .build();
    }
}
