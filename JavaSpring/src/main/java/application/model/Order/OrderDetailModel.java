package application.model.Order;

public class OrderDetailModel {
    private int id;

    private int status;

    private int user;

    private String address;

    private String detail;

    public OrderDetailModel(int id, int user, String address, String detail) {
        this.id = id;
        this.user = user;
        this.address = address;
        this.detail = detail;
    }

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
}
