FROM openjdk:8-jdk-alpine
ADD target/app-0.0.1-SNAPSHOT.jar app-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java" , "-jar", "app-0.0.1-SNAPSHOT.jar"]