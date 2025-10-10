FROM openjdk:21

WORKDIR /app

COPY target/NotificationMs-0.0.1-SNAPSHOT.jar /app/NotificationMs-0.0.1-SNAPSHOT.jar

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "NotificationMs-0.0.1-SNAPSHOT.jar"]