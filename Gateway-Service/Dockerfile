FROM openjdk:21

WORKDIR /app

COPY target/Gateway-Service-0.0.1-SNAPSHOT.jar /app/Gateway-Service-0.0.1-SNAPSHOT.jar

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "Gateway-Service-0.0.1-SNAPSHOT.jar"]