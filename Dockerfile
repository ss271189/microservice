FROM tomcat:8.0-alpine
LABEL maintainer="Shashank Saurabh"
RUN apk update && apk add --no-cache wget
COPY target/microservice-0.0.1-SNAPSHOT.jar /usr/local/tomcat/webapps/microservice-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["catalina.sh", "run"]