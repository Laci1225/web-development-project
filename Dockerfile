FROM openjdk:17-alpine
WORKDIR /app
COPY ./target/web-development-project-0.0.1-SNAPSHOT.jar /app.jar
CMD ["java", "-jar", "/app.jar"]