package application.model.Product;

public class ProductCategory {


    private int productId;

    private String nameProduct;

    private String image;

    private int priceProduct;

    private String descriptionProduct;

    public ProductCategory(int productId, String nameProduct, String image, int priceProduct, String descriptionProduct) {
        this.productId = productId;
        this.nameProduct = nameProduct;
        this.image = image;
        this.priceProduct = priceProduct;
        this.descriptionProduct = descriptionProduct;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(int priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }
}
