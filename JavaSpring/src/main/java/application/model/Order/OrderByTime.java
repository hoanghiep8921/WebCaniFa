package application.model.Order;

import java.util.Date;

public class OrderByTime {
    private int id;
    private int name;
    private long soluong;
    private Date create;
    private String address;
    private String detail;


    public OrderByTime(int id, int name, long soluong, Date create, String address, String detail) {
        this.id = id;
        this.name = name;
        this.soluong = soluong;
        this.create = create;
        this.address = address;
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreate() {
        return create;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public void setCreate(Date create) {
        this.create = create;
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

    public long getSoluong() {
        return soluong;
    }

    public void setSoluong(long soluong) {
        this.soluong = soluong;
    }
}
