package id.my.hendisantika.fcmpushnotification;

import com.google.firebase.FirebaseApp;
import id.my.hendisantika.fcmpushnotification.firebase.FCMInitializer;
import id.my.hendisantika.fcmpushnotification.firebase.FCMService;
import id.my.hendisantika.fcmpushnotification.model.PushNotificationRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class SpringBootFcmPushNotificationApplicationTests {

    @Mock
    FCMInitializer fcmInitializer;

    @Mock
    FCMService fcmClient;

    @Test
    public void firebaseAppInitializationTest() {
        fcmInitializer.initialize();
        System.out.println(FirebaseApp.getApps());
        assertFalse(FirebaseApp.getApps().isEmpty());
    }

    @Test
    public void sendTestMessage() {
        PushNotificationRequest request = new PushNotificationRequest("hello", "testMessage", "testTopic");
        Map<String, String> pushData = new HashMap<>();
        try {
            fcmClient.sendMessage(pushData, request);
        } catch (Exception e) {
            fail();
        }
    }
}
