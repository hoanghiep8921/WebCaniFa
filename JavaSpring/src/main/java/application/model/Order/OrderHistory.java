package application.model.Order;

import java.util.Date;

public class OrderHistory {
    private int orderId;
    private int userId;
    private int statusId;
    private String description;
    private Date created;
    private String address;
    private String detail;
    private int saleNumber;

    public OrderHistory(int orderId, int userId, int statusId, String description, Date created, String address, String detail, int saleNumber) {
        this.orderId = orderId;
        this.userId = userId;
        this.statusId = statusId;
        this.description = description;
        this.created = created;
        this.address = address;
        this.detail = detail;
        this.saleNumber = saleNumber;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
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

    public int getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(int saleNumber) {
        this.saleNumber = saleNumber;
    }
}
