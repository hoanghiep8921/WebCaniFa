package application.data.model;

import javax.persistence.*;

@Entity(name="product_status")
public class ProductStatus {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name="status_id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
