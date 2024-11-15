package id.my.hendisantika.fcmpushnotification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootFcmPushNotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFcmPushNotificationApplication.class, args);
    }

}
