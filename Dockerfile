FROM tomcat:9.0.36-jdk8-adoptopenjdk-openj9

ENV SPRING_PROFILES_ACTIVE docker

ARG WAR_FILE

COPY ${WAR_FILE} /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD [ "catalina.sh", "run" ]