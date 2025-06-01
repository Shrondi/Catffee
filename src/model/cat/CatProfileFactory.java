package model.cat;

/**
 * Fábrica para obtener perfiles de gatos por nombre.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
/**
 * Fábrica de perfiles de gatos. Devuelve un perfil según el nombre.
 */
public class CatProfileFactory {
    /**
     * Devuelve el perfil de gato según el nombre.
     * @param name Nombre del gato
     * @return CatProfile correspondiente o null si no existe
     */
    public static CatProfile getByName(String name) {
        switch (name.toUpperCase()) {
            case "ABI" -> {
                return CatProfiles.abi();
            }
            case "ALFIL" -> {
                return CatProfiles.alfil();
            }
            case "CHERRY" -> {
                return CatProfiles.cherry();
            }
            case "FLUSH" -> {
                return CatProfiles.flush();
            }
            case "GULLIVER" -> {
                return CatProfiles.gulliver();
            }
            case "MAZINGER" -> {
                return CatProfiles.mazinger();
            }
            case "MEMBRILLO" -> {
                return CatProfiles.membrillo();
            }
            case "SYLVESTRE" -> {
                return CatProfiles.sylvestre();
            }
            default -> {
                return null;
            }
        }
    }
}
