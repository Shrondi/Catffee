package model.product;

import java.util.List;

import model.product.Product.Builder;
import utils.I18n;

/**
 * Clase utilitaria para obtener los productos disponibles en Catffee.
 * Proporciona métodos para obtener productos por categoría.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
public class ProductList {

    /**
     * Devuelve la lista de productos de café.
     * @return Lista de productos de café
     */
    public static List<Product> getCoffeeProducts() {
        return List.of(
            new Product.Builder()
                .setName(I18n.getTranslation("product_meowcha_name"))
                .setDescription(I18n.getTranslation("product_meowcha_desc"))
                .setPrice(3.50)
                .setImagePath(ProductList.class.getClassLoader().getResource("images/products/cafe/meowcha.png"))
                .build(),
            new Product.Builder()
                .setName(I18n.getTranslation("product_catpuccino_name"))
                .setDescription(I18n.getTranslation("product_catpuccino_desc"))
                .setPrice(3.20)
                .setImagePath(ProductList.class.getClassLoader().getResource("images/products/cafe/catpuccino.png"))
                .build(),
            new Product.Builder()
                .setName(I18n.getTranslation("product_purrlate_name"))
                .setDescription(I18n.getTranslation("product_purrlate_desc"))
                .setPrice(3.40)
                .setImagePath(ProductList.class.getClassLoader().getResource("images/products/cafe/purrlate.png"))
                .build(),
            new Product.Builder()
                .setName(I18n.getTranslation("product_whisker_beans_name"))
                .setDescription(I18n.getTranslation("product_whisker_beans_desc"))
                .setPrice(2.80)
                .setImagePath(ProductList.class.getClassLoader().getResource("images/products/cafe/whisker_beans.png"))
                .build(),
            new Product.Builder()
                .setName(I18n.getTranslation("product_nap_sip_name"))
                .setDescription(I18n.getTranslation("product_nap_sip_desc"))
                .setPrice(2.60)
                .setImagePath(ProductList.class.getClassLoader().getResource("images/products/cafe/nap_sip.png"))
                .build(),
            new Product.Builder()
                .setName(I18n.getTranslation("product_feline_roast_name"))
                .setDescription(I18n.getTranslation("product_feline_roast_desc"))
                .setPrice(2.50)
                .setImagePath(ProductList.class.getClassLoader().getResource("images/products/cafe/feline_roast.png"))
                .build(),
            new Product.Builder()
                .setName(I18n.getTranslation("product_chai_miau_name"))
                .setDescription(I18n.getTranslation("product_chai_miau_desc"))
                .setPrice(3.60)
                .setImagePath(ProductList.class.getClassLoader().getResource("images/products/cafe/chai_miau.png"))
                .build(),
            new Product.Builder()
                .setName(I18n.getTranslation("product_ronromiel_name"))
                .setDescription(I18n.getTranslation("product_ronromiel_desc"))
                .setPrice(2.80)
                .setImagePath(ProductList.class.getClassLoader().getResource("images/products/cafe/ronromiel.png"))
                .build(),
            new Product.Builder()
                .setName(I18n.getTranslation("product_napresso_name"))
                .setDescription(I18n.getTranslation("product_napresso_desc"))
                .setPrice(2.40)
                .setImagePath(ProductList.class.getClassLoader().getResource("images/products/cafe/napresso.png"))
                .build(),
            new Product.Builder()
                .setName(I18n.getTranslation("product_macatchiato_name"))
                .setDescription(I18n.getTranslation("product_macatchiato_desc"))
                .setPrice(3.30)
                .setImagePath(ProductList.class.getClassLoader().getResource("images/products/cafe/macatchiato.png"))
                .build()
        );
    }

    /**
     * Devuelve la lista de bebidas frías.
     * @return Lista de bebidas frías
     */
    public static List<Product> getColdDrinks() {
        return List.of(
            new Product.Builder()
                .setName(I18n.getTranslation("product_iced_whisker_name"))
                .setDescription(I18n.getTranslation("product_iced_whisker_desc"))
                .setPrice(3.60)
                .setImagePath(ProductList.class.getClassLoader().getResource("images/products/frio/iced_whisker.png"))
                .build(),
            new Product.Builder()
                .setName(I18n.getTranslation("product_gato_tonic_name"))
                .setDescription(I18n.getTranslation("product_gato_tonic_desc"))
                .setPrice(3.80)
                .setImagePath(ProductList.class.getClassLoader().getResource("images/products/frio/gato_tonic.png"))
                .build(),
            new Product.Builder()
                .setName(I18n.getTranslation("product_cat_a_cola_name"))
                .setDescription(I18n.getTranslation("product_cat_a_cola_desc"))
                .setPrice(3.00)
                .setImagePath(ProductList.class.getClassLoader().getResource("images/products/frio/cat_a_cola.png"))
                .build(),
            new Product.Builder()
                .setName(I18n.getTranslation("product_miaumilk_shake_name"))
                .setDescription(I18n.getTranslation("product_miaumilk_shake_desc"))
                .setPrice(4.20)
                .setImagePath(ProductList.class.getClassLoader().getResource("images/products/frio/miaumilk_shake.png"))
                .build(),
            new Product.Builder()
                .setName(I18n.getTranslation("product_pink_paw_name"))
                .setDescription(I18n.getTranslation("product_pink_paw_desc"))
                .setPrice(3.20)
                .setImagePath(ProductList.class.getClassLoader().getResource("images/products/frio/pink_paw.png"))
                .build()
        );
    }

    /**
     * Devuelve la lista de postres.
     * @return Lista de postres
     */
    public static List<Product> getDesserts() {
        return List.of(
            new Product.Builder()
                .setName(I18n.getTranslation("product_pawffins_name"))
                .setDescription(I18n.getTranslation("product_pawffins_desc"))
                .setPrice(2.80)
                .setImagePath(ProductList.class.getClassLoader().getResource("images/products/dulce/pawffins.png"))
                .build(),
            new Product.Builder()
                .setName(I18n.getTranslation("product_tarta_ronroneo_name"))
                .setDescription(I18n.getTranslation("product_tarta_ronroneo_desc"))
                .setPrice(4.20)
                .setImagePath(ProductList.class.getClassLoader().getResource("images/products/dulce/tarta_ronroneo.png"))
                .build(),
            new Product.Builder()
                .setName(I18n.getTranslation("product_gatogalletas_name"))
                .setDescription(I18n.getTranslation("product_gatogalletas_desc"))
                .setPrice(2.50)
                .setImagePath(ProductList.class.getClassLoader().getResource("images/products/dulce/gatogalletas.png"))
                .build(),
            new Product.Builder()
                .setName(I18n.getTranslation("product_churros_miau_name"))
                .setDescription(I18n.getTranslation("product_churros_miau_desc"))
                .setPrice(3.50)
                .setImagePath(ProductList.class.getClassLoader().getResource("images/products/dulce/churros_miau.png"))
                .build(),
            new Product.Builder()
                .setName(I18n.getTranslation("product_brownie_bigotes_name"))
                .setDescription(I18n.getTranslation("product_brownie_bigotes_desc"))
                .setPrice(4.80)
                .setImagePath(ProductList.class.getClassLoader().getResource("images/products/dulce/brownie_bigotes.png"))
                .build()
        );
    }

    /**
     * Devuelve la lista de productos salados.
     * @return Lista de productos salados
     */
    public static List<Product> getSaltyFood() {
        return List.of(
            new Product.Builder()
                .setName(I18n.getTranslation("product_empanacat_name"))
                .setDescription(I18n.getTranslation("product_empanacat_desc"))
                .setPrice(2.80)
                .setImagePath(ProductList.class.getClassLoader().getResource("images/products/salado/empanacat.png"))
                .build(),
            new Product.Builder()
                .setName(I18n.getTranslation("product_toasty_cat_name"))
                .setDescription(I18n.getTranslation("product_toasty_cat_desc"))
                .setPrice(4.80)
                .setImagePath(ProductList.class.getClassLoader().getResource("images/products/salado/toasty_cat.png"))
                .build(),
            new Product.Builder()
                .setName(I18n.getTranslation("product_croissandwich_name"))
                .setDescription(I18n.getTranslation("product_croissandwich_desc"))
                .setPrice(4.20)
                .setImagePath(ProductList.class.getClassLoader().getResource("images/products/salado/croissandwich.png"))
                .build(),
            new Product.Builder()
                .setName(I18n.getTranslation("product_tabla_gatera_name"))
                .setDescription(I18n.getTranslation("product_tabla_gatera_desc"))
                .setPrice(5.50)
                .setImagePath(ProductList.class.getClassLoader().getResource("images/products/salado/tabla_gatera.png"))
                .build(),
            new Product.Builder()
                .setName(I18n.getTranslation("product_wrap_whisker_name"))
                .setDescription(I18n.getTranslation("product_wrap_whisker_desc"))
                .setPrice(4.90)
                .setImagePath(ProductList.class.getClassLoader().getResource("images/products/salado/wrap_whisker.png"))
                .build()
        );
    }
}
