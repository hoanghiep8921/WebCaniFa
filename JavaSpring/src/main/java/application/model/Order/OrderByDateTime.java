package application.model.Order;

public class OrderByDateTime {
    private int id;
    private int name;
    private long count;
    private String create;
    private String address;
    private String detail;

    public OrderByDateTime(int id, int name, long count, String create, String address, String detail) {
        this.id = id;
        this.name = name;
        this.count = count;
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

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
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
}
