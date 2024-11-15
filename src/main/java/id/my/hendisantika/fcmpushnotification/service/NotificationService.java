package id.my.hendisantika.fcmpushnotification.service;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-fcm-push-notification
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 16/11/24
 * Time: 06.16
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final SimpMessagingTemplate template;

    public void sendNotification(String message) {
        template.convertAndSend("/topic/notifications", message);
    }
}
