package application.model.Sale;

public class SaleDetailModel {
    private int id;
    private String content;
    private int saleNumber;
    private String code;
    private String createDate;
    private String endDate;
    private int status;

    public SaleDetailModel(int id, String content, int saleNumber, String code, String createDate, String endDate, int status) {
        this.id = id;
        this.content = content;
        this.saleNumber = saleNumber;
        this.code = code;
        this.createDate = createDate;
        this.endDate = endDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(int saleNumber) {
        this.saleNumber = saleNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
