package application.data.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "product")
public class Product {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name="price")
    private int price;

    @Column(name = "description")
    private String description;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name="update_date")
    private Date updateDate;

    @Column(name="quantity")
    private int quantity;

//    @ManyToOne(optional = true,fetch = FetchType.LAZY)
//    @JoinColumn(name="category_id")
    @Column(name="category_id")
    private int category;
//
//    @ManyToOne(optional = true,fetch = FetchType.LAZY)
//    @JoinColumn(name="status_id")
    @Column(name="status_id")
    private int productStatus;


    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
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
}
