FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/property-*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
