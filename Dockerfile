FROM openjdk:17-jdk-slim
LABEL authors="lukegiles"
VOLUME /tmp
EXPOSE 3000
RUN cp target/budgets-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]