package application.data.service;

import application.data.model.UserNotification;
import application.data.repository.UserNotificationRepository;
import application.model.Notification.NotificationContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserNotificationService {
    @Autowired
    private UserNotificationRepository userNotificationRepository;

    public int totalNotificationNoCheck(int userId){return userNotificationRepository.totalNotificationNoCheck(userId);}

    public List<NotificationContent> getAllNoti(int userId){
        return userNotificationRepository.getAllNoti(userId);
    }
    public void addNewUserNoti(UserNotification userNotification){
        userNotificationRepository.save(userNotification);
    }
    public void updateNoti(int userId){
        userNotificationRepository.updateNoti(userId);
    }
}
