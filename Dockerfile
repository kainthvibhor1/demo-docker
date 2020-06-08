FROM openjdk:8-jdk-alpine

RUN mkdir -p /usr/local/spring/app

WORKDIR /usr/local/spring/app

COPY . .

RUN chmod 777 mvnw

RUN ./mvnw clean install -DskipTests

COPY target/goal-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD [ "java", "-jar", "./goal-0.0.1-SNAPSHOT.jar" ]