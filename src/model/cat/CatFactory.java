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
public class CatFactory {
    /**
     * Devuelve el perfil de gato según el nombre.
     * @param name Nombre del gato
     * @return CatProfile correspondiente o null si no existe
     */
    public static Cat getByName(String name) {
        switch (name.toUpperCase()) {
            case "ABI" -> {
                return CatList.abi();
            }
            case "ALFIL" -> {
                return CatList.alfil();
            }
            case "CHERRY" -> {
                return CatList.cherry();
            }
            case "FLUSH" -> {
                return CatList.flush();
            }
            case "GULLIVER" -> {
                return CatList.gulliver();
            }
            case "MAZINGER" -> {
                return CatList.mazinger();
            }
            case "MEMBRILLO" -> {
                return CatList.membrillo();
            }
            case "SYLVESTRE" -> {
                return CatList.sylvestre();
            }
            default -> {
                return null;
            }
        }
    }
}
