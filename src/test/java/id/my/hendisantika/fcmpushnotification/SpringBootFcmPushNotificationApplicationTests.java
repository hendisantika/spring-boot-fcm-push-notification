package id.my.hendisantika.fcmpushnotification;

import com.google.firebase.messaging.FirebaseMessaging;
import id.my.hendisantika.fcmpushnotification.firebase.FCMService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(properties = "app.firebase-configuration-file=google/test-missing-credentials.json")
class SpringBootFcmPushNotificationApplicationTests {

    @MockitoBean
    FirebaseMessaging firebaseMessaging;

    @MockitoBean
    FCMService fcmService;

    @Autowired
    ApplicationContext context;

    @Test
    void contextLoads() {
        assertNotNull(context.getBean(FCMService.class));
    }
}
