FROM openjdk:17-jdk-slim
LABEL authors="lukegiles"
VOLUME /tmp
EXPOSE 3000
COPY target/budgets-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]