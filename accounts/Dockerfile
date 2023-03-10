FROM openjdk:17.0.2
MAINTAINER easybank.com
RUN mkdir  /opt/app
COPY target/accounts-0.0.1-SNAPSHOT.jar /opt/app/accounts-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/opt/app/accounts-0.0.1-SNAPSHOT.jar"]