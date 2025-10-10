FROM openjdk:21

WORKDIR /app

COPY target/WalletMs-0.0.1-SNAPSHOT.jar /app/WalletMs-0.0.1-SNAPSHOT.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "WalletMs-0.0.1-SNAPSHOT.jar"]