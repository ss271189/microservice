FROM tomcat:8.0-alpine
LABEL maintainer="shashank.saurabh@nagarro.com"
RUN apk update && apk add --no-cache wget
RUN wget -d -O /usr/local/tomcat/webapps/shashanksaurabh.war http://192.168.0.107:8082/artifactory/CI-Automation-JAVA/com/nagarro/devops-tools/devops/demosampleapplication/1.0.0-SNAPSHOT/demosampleapplication-1.0.0-20200902.091437-1.war --http-user=admin --http-password=admin@123
EXPOSE 8080
CMD ["catalina.sh", "run"]