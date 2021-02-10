FROM openjdk:8-jdk-alpine
LABEL maintainer="Shashank Saurabh"
RUN apk update && apk add --no-cache wget
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} football-league.jar
ENTRYPOINT ["java","-jar","/football-league.jar"]
