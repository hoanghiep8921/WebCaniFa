package application.data.model;

import javax.persistence.*;



@Entity(name = "category")
public class Category {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
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
