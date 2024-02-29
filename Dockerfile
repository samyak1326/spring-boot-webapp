FROM maven:latest AS builder

WORKDIR /web-app

COPY . /web-app

RUN mvn clean

RUN mvn install


FROM amazoncorretto:17-alpine3.19-jdk

COPY --from=builder /web-app/target/webapp-0.0.1.jar /app.jar

EXPOSE 8080

RUN ls -la

CMD ["java", "-jar", "app.jar"]