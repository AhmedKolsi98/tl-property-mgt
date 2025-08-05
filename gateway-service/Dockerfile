FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/gateway-*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
