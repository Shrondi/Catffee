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
                return CatProfileList.abi();
            }
            case "ALFIL" -> {
                return CatProfileList.alfil();
            }
            case "CHERRY" -> {
                return CatProfileList.cherry();
            }
            case "FLUSH" -> {
                return CatProfileList.flush();
            }
            case "GULLIVER" -> {
                return CatProfileList.gulliver();
            }
            case "MAZINGER" -> {
                return CatProfileList.mazinger();
            }
            case "MEMBRILLO" -> {
                return CatProfileList.membrillo();
            }
            case "SYLVESTRE" -> {
                return CatProfileList.sylvestre();
            }
            default -> {
                return null;
            }
        }
    }
}
