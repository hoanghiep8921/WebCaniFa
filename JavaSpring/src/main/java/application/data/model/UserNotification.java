package application.data.model;

import javax.persistence.*;

@Entity(name = "user_notification")
public class UserNotification {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name="id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "notification_id")
    private int notificationId;

    @Column(name = "read_status")
    private int readStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(int readStatus) {
        this.readStatus = readStatus;
    }
}
