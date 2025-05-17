package model;

public class CatProfiles {
    public static CatProfile abi() {
        return new CatProfile.Builder()
            .name("ABI")
            .age("1 años")
            .gender("Hembra")
            .about("Un torbellino adorable. Abi es juguetona y sociable, y siempre está lista para una aventura. ¡O para robarte la silla!")
            .ideal("Familias activas o personas que pasen mucho tiempo en casa.")
            .imagePaths(
                "resources/images/abi1.png",
                "resources/images/abi2.png",
                "resources/images/abi3.png"
            )
            .build();
    }

    public static CatProfile alfil() {
        return new CatProfile.Builder()
            .name("ALFIL")
            .age("4 años")
            .gender("Macho")
            .about("Dormilón profesional y fan de las mantas suaves. Le encanta hacerse ovillo y ronronear a volumen máximo.")
            .ideal("Casas tranquilas con sofá libre.")
            .imagePaths(
                "resources/images/alfil1.png",
                "resources/images/alfil2.png",
                "resources/images/alfil3.png"
            )
            .build();
    }

    public static CatProfile cherry() {
        return new CatProfile.Builder()
            .name("CHERRY")
            .age("2 años")
            .gender("Hembra")
            .about("Curiosa y coqueta. Le gusta observar desde lo alto y acercarse con cariño a pedir mimos.")
            .ideal("Una compañera elegante y observadora.")
            .imagePaths(
                "resources/images/cherry1.png",
                "resources/images/cherry2.png",
                "resources/images/cherry3.png"
            )
            .build();
    }

    public static CatProfile flush() {
        return new CatProfile.Builder()
            .name("FLUSH")
            .age("5 años")
            .gender("Macho")
            .about("Dormilón, pacífico y dulce. \n" + "A Flush le encanta dormir en posiciones imposibles y siempre está buscando una superficie cómoda para echarse la siesta.")
            .ideal("Un hogar sereno, con espacio para relajarse.")
            .imagePaths(
                "resources/images/flush1.png",
                "resources/images/flush2.png",
                "resources/images/flush3.png"
            )
            .build();
    }

    public static CatProfile gulliver() {
        return new CatProfile.Builder()
            .name("GULLIVER")
            .age("3 años")
            .gender("Macho")
            .about("Gulliver es un explorador. Discreto pero cariñoso, se deja querer cuando ya te ha observado lo suficiente.")
            .ideal("Gente paciente que adore ganar la confianza de un gato.")
            .imagePaths(
                "resources/images/gulliver1.png",
                "resources/images/gulliver2.png",
                "resources/images/gulliver3.png"
            )
            .build();
    }

    public static CatProfile mazinger() {
        return new CatProfile.Builder()
            .name("MAZINGER")
            .age("4 años")
            .gender("Macho")
            .about("El gato con más actitud. Mazinger parece serio, pero en realidad es un trozo de pan. Adora las cajas.")
            .ideal("Una casa con ritmo medio y mucho cariño.")
            .imagePaths(
                "resources/images/mazinger1.png",
                "resources/images/mazinger2.png",
                "resources/images/mazinger3.png"
            )
            .build();
    }

    public static CatProfile membrillo() {
        return new CatProfile.Builder()
            .name("MEMBRILLO")
            .age("6 años")
            .gender("Macho")
            .about("Mimoso y melancólico. Membrillo parece un poeta en forma de gato. Le encanta tumbarse a mirar el vacío.")
            .ideal("Alguien sensible y con ganas de un amigo tranquilo.")
            .imagePaths(
                "resources/images/membrillo1.png",
                "resources/images/membrillo2.png",
                "resources/images/membrillo3.png"
            )
            .build();
    }

    public static CatProfile sylvestre() {
        return new CatProfile.Builder()
            .name("SYLVESTRE")
            .age("7 años")
            .gender("Hembra")
            .about("El más elegante y relajado del grupo. Sylvestre se toma todo con calma y transmite mucha paz.")
            .ideal("Personas mayores o muy caseras que busquen paz.")
            .imagePaths(
                "resources/images/sylvestre1.png",
                "resources/images/sylvestre2.png",
                "resources/images/sylvestre3.png"
            )
            .build();
    }

    
}
