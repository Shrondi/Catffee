package model;

public class CatProfileFactory {

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
