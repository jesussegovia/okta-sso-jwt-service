FROM openjdk:11-jdk-alpine

ARG JAR_FILE=target/trial-sso-service-0.0.1-SNAPSHOT.jar
ARG JAR_LIB_FILE=target/classes/

# cd /usr/local/runme
WORKDIR /usr/local/runme

# copy target/find-links.jar /usr/local/runme/app.jar
COPY ${JAR_FILE} app.jar

# copy project dependencies
# cp -rf target/classes/  /usr/local/runme/classes
ADD ${JAR_LIB_FILE} classes/

# java -jar /usr/local/runme/app.jar
ENTRYPOINT ["java","-jar","app.jar"]