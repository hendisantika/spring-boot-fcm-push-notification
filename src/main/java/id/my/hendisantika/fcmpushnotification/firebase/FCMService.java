package id.my.hendisantika.fcmpushnotification.firebase;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import id.my.hendisantika.fcmpushnotification.model.PushNotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-fcm-push-notification
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 15/11/24
 * Time: 07.26
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Service
public class FCMService {

    public void sendMessage(Map<String, String> data, PushNotificationRequest request)
            throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageWithData(data, request);
        String response = sendAndGetResponse(message);
        logger.info("Sent message with data. Topic: " + request.getTopic() + ", " + response);
    }

    public void sendMessageWithoutData(PushNotificationRequest request)
            throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageWithoutData(request);
        String response = sendAndGetResponse(message);
        logger.info("Sent message without data. Topic: " + request.getTopic() + ", " + response);
    }

    public void sendMessageToToken(PushNotificationRequest request)
            throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageToToken(request);
        String response = sendAndGetResponse(message);
        logger.info("Sent message to token. Device token: " + request.getToken() + ", " + response);
    }

    private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }

    private AndroidConfig getAndroidConfig(String topic) {
        return AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder().setSound(NotificationParameter.SOUND.getValue())
                        .setColor(NotificationParameter.COLOR.getValue()).setTag(topic).build()).build();
    }

    private ApnsConfig getApnsConfig(String topic) {
        return ApnsConfig.builder()
                .setAps(Aps.builder().setCategory(topic).setThreadId(topic).build()).build();
    }

    private Message getPreconfiguredMessageToToken(PushNotificationRequest request) {
        return getPreconfiguredMessageBuilder(request).setToken(request.getToken())
                .build();
    }
}
