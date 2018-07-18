package application.model.Notification;

public class NotificationContent {
    private int isRead;
    private String code;
    private String content;

    public NotificationContent(int isRead, String code, String content) {
        this.isRead = isRead;
        this.code = code;
        this.content = content;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
