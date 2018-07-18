package application.data.model;


import javax.persistence.*;
import java.util.Date;

@Entity(name="order_detail")
public class Order {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name="order_id")
    private int id;

    @Column(name = "status_id")
    private int status;

    @Column(name="user_id")
    private int user;

    @Column(name="created_date")
    private Date createdDate;

    @Column(name = "address")
    private String address;

    @Column(name = "detail")
    private String detail;

    @Column(name = "sale_id")
    private int saleId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }
}
