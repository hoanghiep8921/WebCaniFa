package application.controller.api;

import application.data.model.Notification;
import application.data.model.User;
import application.data.model.UserNotification;
import application.data.service.NotificationService;
import application.data.service.UserNotificationService;
import application.data.service.UserServiceImpl;
import application.model.BaseApiResult;
import application.model.DataApiResult;
import application.model.Notification.NotificationModel;
import application.model.Notification.NotificationUserModel;
import application.model.User.UserByRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/notification")
public class NotificationApiController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserNotificationService userNotificationService;

    @PostMapping("/create-notification-listUser")
    public BaseApiResult createNotification(@RequestBody NotificationModel notificationModel){
        DataApiResult result = new DataApiResult();
        List<Integer> listUser = userService.userByRole(notificationModel.getRole());
        Notification notification = new Notification();
        notification.setContent(notificationModel.getContent());
        notification.setCode(notificationModel.getCode());
        notificationService.addNewNotification(notification);
        for(Integer userByRole : listUser){
            UserNotification userNotification = new UserNotification();
            userNotification.setReadStatus(0);
            userNotification.setUserId(userByRole);
            userNotification.setNotificationId(notification.getId());
            userNotificationService.addNewUserNoti(userNotification);
        }
        result.setMessage("OK");
        result.setSuccess(true);
        result.setData(200);
        return result;
    }
    @PostMapping("/create-notification-user")
    public BaseApiResult createNotificationUser(@RequestBody NotificationUserModel notificationUserModel){
        DataApiResult result = new DataApiResult();
        Notification notification = new Notification();
        notification.setContent(notificationUserModel.getContent());
        notification.setCode(notificationUserModel.getCode());
        notificationService.addNewNotification(notification);

        UserNotification userNotification = new UserNotification();
        userNotification.setReadStatus(0);
        userNotification.setUserId(notificationUserModel.getUserId());
        userNotification.setNotificationId(notification.getId());
        userNotificationService.addNewUserNoti(userNotification);

        result.setMessage("OK");
        result.setSuccess(true);
        result.setData(200);
        return result;
    }


    @GetMapping("/getAllNoti/{userId}")
    public BaseApiResult getAllNoti(@PathVariable int userId){
        DataApiResult result = new DataApiResult();
        result.setData(userNotificationService.getAllNoti(userId));
        result.setSuccess(true);
        result.setMessage("OK");
        return result;
    }

    @GetMapping("/getIsReadNoti/{userId}")
    public BaseApiResult getIsReadNoti(@PathVariable int userId){
        DataApiResult result = new DataApiResult();
        result.setData(userNotificationService.totalNotificationNoCheck(userId));
        result.setSuccess(true);
        result.setMessage("OK");
        return result;
    }

    @CrossOrigin
    @GetMapping("/updateNoti/{userId}")
    public BaseApiResult updateNoti(@PathVariable int userId){
        DataApiResult result = new DataApiResult();
        userNotificationService.updateNoti(userId);
        result.setData(0);
        result.setSuccess(true);
        result.setMessage("OK");
        return result;
    }
}
