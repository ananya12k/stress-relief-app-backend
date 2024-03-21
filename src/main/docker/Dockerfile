FROM maven:3.9.6-eclipse-temurin-21-jammy
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
