package model;

public class ProductData {
    private final String name;
    private final String description;
    private final double price;
    private final String imagePath;

    private ProductData(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
        this.imagePath = builder.imagePath;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public String getImagePath() { return imagePath; }

    public static class Builder {
        private String name;
        private String description;
        private double price;
        private String imagePath;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setImagePath(String imagePath) {
            this.imagePath = imagePath;
            return this;
        }

        public ProductData build() {
            // Aquí puedes validar que los campos obligatorios estén completos
            if (name == null || price == 0.0 || imagePath == null) {
                throw new IllegalStateException("name, price y imagePath son obligatorios");
            }
            return new ProductData(this);
        }
    }
}
