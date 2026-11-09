package id.my.hendisantika.fcmpushnotification.controller;

import com.google.firebase.messaging.FirebaseMessaging;
import id.my.hendisantika.fcmpushnotification.firebase.FCMService;
import id.my.hendisantika.fcmpushnotification.model.PushNotificationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-fcm-push-notification
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Link : s.id/hendisantika
 * Date: 15/11/24
 * Time: 07.41
 */
@SpringBootTest(properties = "app.firebase-configuration-file=google/test-missing-credentials.json")
@AutoConfigureMockMvc
class PushNotificationControllerTest {

    private static final String BODY = """
            {"title":"Hello","message":"Test message","topic":"testTopic","token":"test-token"}
            """;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FirebaseMessaging firebaseMessaging;

    @MockitoBean
    private FCMService fcmService;

    @Test
    void sendSampleNotification() throws Exception {
        mockMvc.perform(get("/notification").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Notification has been sent."));
        verify(fcmService).sendMessageWithoutData(any(PushNotificationRequest.class));
    }

    @Test
    void sendTopicNotification() throws Exception {
        mockMvc.perform(post("/notification/topic").contentType(MediaType.APPLICATION_JSON).content(BODY))
                .andExpect(status().isOk());
        verify(fcmService).sendMessageWithoutData(any(PushNotificationRequest.class));
    }

    @Test
    void sendTokenNotification() throws Exception {
        mockMvc.perform(post("/notification/token").contentType(MediaType.APPLICATION_JSON).content(BODY))
                .andExpect(status().isOk());
        verify(fcmService).sendMessageToToken(any(PushNotificationRequest.class));
    }

    @Test
    void sendDataNotification() throws Exception {
        mockMvc.perform(post("/notification/data").contentType(MediaType.APPLICATION_JSON).content(BODY))
                .andExpect(status().isOk());
        verify(fcmService).sendMessage(anyMap(), any(PushNotificationRequest.class));
    }
}
