FROM openjdk:17-jdk-slim
LABEL authors="lukegiles"
COPY target/budgets-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 3000
ENTRYPOINT ["java","-jar","/app.jar"]
