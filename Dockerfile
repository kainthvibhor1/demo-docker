FROM openjdk:8-jdk-alpine

ENV SPRING_PROFILES_ACTIVE docker

RUN mkdir -p /build

WORKDIR /build

ARG JAR_FILE

COPY ${JAR_FILE} app.jar

ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar" ]
