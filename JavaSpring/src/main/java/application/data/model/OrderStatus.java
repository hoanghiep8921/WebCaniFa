package application.data.model;

import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;

@Entity(name = "order_status")
public class OrderStatus {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name ="status_id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "des")
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
