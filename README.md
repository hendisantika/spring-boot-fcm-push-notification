# spring-boot-fcm-push-notification

[![Java CI with Maven](https://github.com/hendisantika/spring-boot-fcm-push-notification/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/spring-boot-fcm-push-notification/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1-6DB33F?logo=springboot)
![Dependabot](https://img.shields.io/badge/Dependabot-enabled-025E8C?logo=dependabot)

Send push notifications from Spring Boot server-side application using FCM

### Tech Stack

+ Java 25 (Temurin)
+ Spring Boot 4.1 (Web MVC, WebSocket/STOMP)
+ Firebase Admin SDK
+ Maven (via Maven Wrapper `./mvnw`)
+ Lombok

### Requirements

+ JDK 25
+ A Firebase project and its Admin SDK service account JSON file

### Things todo list

1. Clone this repository: `git clone https://github.com/hendisantika/spring-boot-fcm-push-notification.git`
2. Navigate to the folder: `cd spring-boot-fcm-push-notification`
3. Put your Firebase service account JSON file in place (see [Configuration](#configuration))
4. Run the application: `./mvnw clean spring-boot:run`
5. Check console log, or open http://localhost:8080 for the WebSocket demo page

### Configuration

+ Provide your own Firebase Admin SDK, JSON-formatted file (service account credentials) and place it inside the
  project (for example _src/main/resources/google_)
+ Change the path and defaults in **application.properties**:

```properties
app.firebase-configuration-file=google/notifikasi.json
```

> **Warning:** the service account file contains a private key. Never commit it to a public repository —
> keep it out of version control and rotate the key immediately if it has ever been pushed.

### Build & Test

```shell
./mvnw clean verify
```

The tests mock `FirebaseMessaging` and `FCMService`, so they run without Firebase credentials or network access.

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

### Continuous Integration

GitHub Actions ([`.github/workflows/maven.yml`](.github/workflows/maven.yml)) builds and tests the project with
JDK 25 (Temurin) on every push and pull request to `main`. Pushes to `main` also submit the Maven dependency graph so
Dependabot alerts stay accurate.

### Dependabot

[`.github/dependabot.yml`](.github/dependabot.yml) checks for updates every day at 05:00 (Asia/Jakarta):

+ **Maven**: updates are grouped (`spring-boot`, `firebase`, `maven-plugins`, `minor-and-patch`) with a cooldown of
  3 days (7 days for major versions), using `build(deps)` commit prefixes.
+ **GitHub Actions**: all action updates are grouped into a single PR with a `ci(deps)` commit prefix.
