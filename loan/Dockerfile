FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/loan-*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
