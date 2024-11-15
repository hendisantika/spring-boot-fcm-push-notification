# spring-boot-fcm-push-notification

Send push notifications from Spring Boot server-side application using FCM

### Things todo list

1. Clone this repository: `git clone https://github.com/hendisantika/spring-boot-fcm-push-notification.git`
2. Navigate to the folder: `cd spring-boot-fcm-push-notification`
3. Run the application: `mvn clean spring-boot:run`
4. Check console log

### Testing the notification

### Configuration

+ Provide your own Firebase Admin SDK, JSON-formatted file (service account credentials) and place it inside the
  project (for example _src/resources/google_)
+ Change the path and defaults in **application.properties**

### Endpoints

+ **GET /notification** – Trigger sample notification with default values sending

```shell
curl -H "Content-Type: application/json" -X GET http://localhost:8080/notification
```

+ **POST /notification/topic** – Send a message to a specific topic

```shell
curl -d '{"title":"Hello", "message":"The message...", "topic":"contactTopic"}' -H "Content-Type: application/json" -X POST http://localhost:8080/notification/topic
```

+ **POST /notification/token** – Send a message to a specific device (with token)

```shell
curl -d '{"title":"Hey you!", "message":"Watch out!", "token":"cct00ebz8eg:APA91bFcTkFE_0Qafj6nWv5yHxqCLTyxAaqi4QzwsFNLP5M9G78X8Z5UMZTW004q1PUux63Ut-1WMGVToMNTdB3ZfO8lCZlc4lGpxm7LBdWfkhaUxdbpQ5xIO5cAb-w9H2dBLNHT7i-U", "topic": ""}' -H "Content-Type: application/json" -X POST http://localhost:8080/notification/token
```

+ **POST /notification/data** – Send a message to a specific topic with additional payload data.

```shell
curl -d '{"title":"Hello", "message":"Data message", "topic":"contactTopic"}' -H "Content-Type: application/json" -X POST http://localhost:8080/notification/data
```

+ **Success response**

```json
{
  "status": 200,
  "message": "Notification has been sent."
}
```
