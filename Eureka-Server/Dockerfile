FROM openjdk:21

WORKDIR /app

COPY target/Eureka-Server-0.0.1-SNAPSHOT.jar /app/Eureka-Server-0.0.1-SNAPSHOT.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "Eureka-Server-0.0.1-SNAPSHOT.jar"]