package application.data.repository;

import application.data.model.UserNotification;
import application.model.Notification.NotificationContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserNotificationRepository extends JpaRepository<UserNotification, Integer> {
    @Query(value = "SELECT count(u.read_status) FROM db_demo.user_notification u where u.user_id=?1 and u.read_status=0",nativeQuery = true)
    int totalNotificationNoCheck(int userId);

    @Query("select  new application.model.Notification.NotificationContent(u.readStatus,n.code,n.content) from user_notification u , notification n where u.notificationId=n.id and u.userId=?1")
    List<NotificationContent> getAllNoti(int userId);

    @Modifying
    @Query(value = "update user_notification u set u.readStatus=1 where  u.userId = ?1 ")
    @Transactional
    void updateNoti(int userId);
}
