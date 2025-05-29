package model;

/**
 * Representa el perfil de un gato en Catffee.
 * Incluye nombre, edad, género, descripción, ideal y rutas de imágenes.
 *
 * @author Pablo Estepa Alcaide - i22esalp@uco.es
 * @author Carlos Lucena Robles - f92luroc@uco.es
 * @date 2024-05-30
 */
public class CatProfile {
    private final String name;
    private final String age;
    private final String gender;
    private final String about;
    private final String ideal;
    private final String[] imagePaths;

    private CatProfile(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.gender = builder.gender;
        this.about = builder.about;
        this.ideal = builder.ideal;
        this.imagePaths = builder.imagePaths;
    }

    public String getName() { return name; }
    public String getAge() { return age; }
    public String getGender() { return gender; }
    public String getAbout() { return about; }
    public String getIdeal() { return ideal; }
    public String[] getImagePaths() { return imagePaths; }

    /**
     * Builder para crear instancias de CatProfile.
     */
    public static class Builder {
        private String name;
        private String age;
        private String gender;
        private String about;
        private String ideal;
        private String[] imagePaths;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(String age) {
            this.age = age;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder about(String about) {
            this.about = about;
            return this;
        }

        public Builder ideal(String ideal) {
            this.ideal = ideal;
            return this;
        }

        public Builder imagePaths(String... imagePaths) {
            this.imagePaths = imagePaths;
            return this;
        }

        /**
         * Construye el CatProfile.
         * @return CatProfile creado
         */
        public CatProfile build() {
            return new CatProfile(this);
        }
    }
}
