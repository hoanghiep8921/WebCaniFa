package application.data.model;

public class ProductInOrder {
    private int productId;
    private int quantity;
    private int price;
    private String name;
    private String image;
    private String detail;

    public ProductInOrder(int productId, int quantity, int price, String name, String image, String detail) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.name = name;
        this.image = image;
        this.detail = detail;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
