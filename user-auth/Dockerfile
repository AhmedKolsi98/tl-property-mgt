FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/user-auth-*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
