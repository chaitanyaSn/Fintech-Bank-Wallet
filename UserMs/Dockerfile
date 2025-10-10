FROM openjdk:21

WORKDIR /app

COPY target/UserMs-0.0.1-SNAPSHOT.jar /app/UserMS-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "UserMS-0.0.1-SNAPSHOT.jar"]