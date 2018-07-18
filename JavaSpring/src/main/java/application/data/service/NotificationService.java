package application.data.service;

import application.data.model.Notification;
import application.data.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.soap.Addressing;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public void addNewNotification(Notification notification){
        notificationRepository.save(notification);
    }
}
