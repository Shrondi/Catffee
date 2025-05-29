package model;

import java.util.List;

/**
 * Clase utilitaria para obtener los productos disponibles en Catffee.
 * Proporciona métodos para obtener productos por categoría.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
public class Products {

    /**
     * Devuelve la lista de productos de café.
     * @return Lista de productos de café
     */
    public static List<ProductData> getCoffeeProducts() {
        return List.of(
            new ProductData.Builder()
                .setName("Meowcha")
                .setDescription("Chocolate & Espresso")
                .setPrice(3.50)
                .setImagePath("resources/images/products/cafe/meowcha.png")
                .build(),
            new ProductData.Builder()
                .setName("Catpuccino")
                .setDescription("Espuma Suave & Canela")
                .setPrice(3.20)
                .setImagePath("resources/images/products/cafe/catpuccino.png")
                .build(),
            new ProductData.Builder()
                .setName("Purrlate")
                .setDescription("Vainilla & Leche Cream")
                .setPrice(3.40)
                .setImagePath("resources/images/products/cafe/purrlate.png")
                .build(),
            new ProductData.Builder()
                .setName("Whisker Beans")
                .setDescription("Blend intenso")
                .setPrice(2.80)
                .setImagePath("resources/images/products/cafe/whisker_beans.png")
                .build(),
            new ProductData.Builder()
                .setName("Nap & Sip")
                .setDescription("Infusión de Tarde")
                .setPrice(2.60)
                .setImagePath("resources/images/products/cafe/nap_sip.png")
                .build(),
            new ProductData.Builder()
                .setName("Feline Roast")
                .setDescription("Café de Filtro Potente")
                .setPrice(2.50)
                .setImagePath("resources/images/products/cafe/feline_roast.png")
                .build(),
            new ProductData.Builder()
                .setName("Chai-miau")
                .setDescription("Chai Latte Especiado")
                .setPrice(3.60)
                .setImagePath("resources/images/products/cafe/chai_miau.png")
                .build(),
            new ProductData.Builder()
                .setName("Ronromiel")
                .setDescription("Manzanilla & Miel")
                .setPrice(2.80)
                .setImagePath("resources/images/products/cafe/ronromiel.png")
                .build(),
            new ProductData.Builder()
                .setName("Napresso")
                .setDescription("Espresso Doble")
                .setPrice(2.40)
                .setImagePath("resources/images/products/cafe/napresso.png")
                .build(),
            new ProductData.Builder()
                .setName("Macatchiato")
                .setDescription("Toqye de Caramelo")
                .setPrice(3.30)
                .setImagePath("resources/images/products/cafe/macatchiato.png")
                .build()
        );
    }

    /**
     * Devuelve la lista de bebidas frías.
     * @return Lista de bebidas frías
     */
    public static List<ProductData> getColdDrinks() {
        return List.of(
            new ProductData.Builder()
                .setName("Iced Whisker")
                .setDescription("Espresso frío")
                .setPrice(3.60)
                .setImagePath("resources/images/products/frio/iced_whisker.png")
                .build(),
            new ProductData.Builder()
                .setName("Gato Tonic")
                .setDescription("Espresso con tónica")
                .setPrice(3.80)
                .setImagePath("resources/images/products/frio/gato_tonic.png")
                .build(),
            new ProductData.Builder()
                .setName("Cat-a-cola")
                .setDescription("Refresco cola")
                .setPrice(3.00)
                .setImagePath("resources/images/products/frio/cat_a_cola.png")
                .build(),
            new ProductData.Builder()
                .setName("MiauMilk Shake")
                .setDescription("Batido oreo")
                .setPrice(4.20)
                .setImagePath("resources/images/products/frio/miaumilk_shake.png")
                .build(),
            new ProductData.Builder()
                .setName("Pink Paw")
                .setDescription("Limonada frutos")
                .setPrice(3.20)
                .setImagePath("resources/images/products/frio/pink_paw.png")
                .build()
        );
    }

    /**
     * Devuelve la lista de postres.
     * @return Lista de postres
     */
    public static List<ProductData> getDesserts() {
        return List.of(
            new ProductData.Builder()
                .setName("Pawffins")
                .setDescription("Muffin choco")
                .setPrice(2.80)
                .setImagePath("resources/images/products/dulce/pawffins.png")
                .build(),
            new ProductData.Builder()
                .setName("Tarta Ronroneo")
                .setDescription("Cheesecake")
                .setPrice(4.20)
                .setImagePath("resources/images/products/dulce/tarta_ronroneo.png")
                .build(),
            new ProductData.Builder()
                .setName("Gatogalletas")
                .setDescription("Crumble-cookies")
                .setPrice(2.50)
                .setImagePath("resources/images/products/dulce/gatogalletas.png")
                .build(),
            new ProductData.Builder()
                .setName("Churros Miau")
                .setDescription("Churros con toppings")
                .setPrice(3.50)
                .setImagePath("resources/images/products/dulce/churros_miau.png")
                .build(),
            new ProductData.Builder()
                .setName("Brownie Bigotes")
                .setDescription("Nueces & helado")
                .setPrice(4.80)
                .setImagePath("resources/images/products/dulce/brownie_bigotes.png")
                .build()
        );
    }

    /**
     * Devuelve la lista de productos salados.
     * @return Lista de productos salados
     */
    public static List<ProductData> getSaltyFood() {
        return List.of(
            new ProductData.Builder()
                .setName("Empanacat")
                .setDescription("Carne o verdura")
                .setPrice(2.80)
                .setImagePath("resources/images/products/salado/empanacat.png")
                .build(),
            new ProductData.Builder()
                .setName("Toasty Cat")
                .setDescription("Aguacate, huevos...")
                .setPrice(4.80)
                .setImagePath("resources/images/products/salado/toasty_cat.png")
                .build(),
            new ProductData.Builder()
                .setName("Croissandwich")
                .setDescription("Jamón, queso...")
                .setPrice(4.20)
                .setImagePath("resources/images/products/salado/croissandwich.png")
                .build(),
            new ProductData.Builder()
                .setName("Tabla Gatera")
                .setDescription("Variedad quesos")
                .setPrice(5.50)
                .setImagePath("resources/images/products/salado/tabla_gatera.png")
                .build(),
            new ProductData.Builder()
                .setName("Wrap Whisker")
                .setDescription("Pollo, verduras...")
                .setPrice(4.90)
                .setImagePath("resources/images/products/salado/wrap_whisker.png")
                .build()
        );
    }
}
