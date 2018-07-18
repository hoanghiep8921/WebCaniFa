package application.data.model;

import javax.persistence.*;

@Entity(name="product_history")
public class ProductHistory {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "history_id")
    private int id;

    @Column(name = "before")
    private String before;

    @Column(name = "after")
    private String after;

    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }
}
