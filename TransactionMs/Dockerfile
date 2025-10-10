FROM openjdk:21

WORKDIR /app

COPY target/TransactionMs-0.0.1-SNAPSHOT.jar /app/TransactionMs-0.0.1-SNAPSHOT.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "TransactionMs-0.0.1-SNAPSHOT.jar"]