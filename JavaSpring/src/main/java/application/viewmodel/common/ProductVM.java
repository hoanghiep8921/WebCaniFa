package application.viewmodel.common;

import application.model.Category.CategoryDataModel;
import application.model.Product.ProductStatusDataModel;

import java.util.Date;

public class ProductVM {
    private int id;
    private String name;
    private String image;
    private String description;
    private Date createdDate;
    private Date updateDate;
    private int price;
    private int quantity;
    private String category;
    private String productStatus;

    public ProductVM(int id, String name, String image, String description, Date createdDate, Date updateDate, int price, int quantity, String category, String productStatus) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.productStatus = productStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }
}
