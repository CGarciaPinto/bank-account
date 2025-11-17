FROM eclipse-temurin:17-jdk-alpine

COPY target/bank-account-0.0.1-SNAPSHOT.jar bank-app.jar

ENTRYPOINT ["java", "-jar", "bank-app.jar"]