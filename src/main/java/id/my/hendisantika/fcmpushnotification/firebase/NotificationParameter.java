package id.my.hendisantika.fcmpushnotification.firebase;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-fcm-push-notification
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 15/11/24
 * Time: 07.30
 * To change this template use File | Settings | File Templates.
 */
public enum NotificationParameter {
    SOUND("default"),
    COLOR("#FFFF00");

    private final String value;

    NotificationParameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
