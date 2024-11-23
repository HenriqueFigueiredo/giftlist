FROM openjdk:21-jdk-slim as build

# container work dir
WORKDIR /app

COPY target/giftlist-api-0.0.1.jar giftlist-api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "giftlist-api.jar"]