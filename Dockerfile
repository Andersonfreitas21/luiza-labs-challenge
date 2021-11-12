FROM openjdk:8-jdk-alpine
ENV TZ=America/Fortaleza
RUN apk add --no-cache tzdata
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
WORKDIR /app/
COPY target/schedule-request-0.0.1-SNAPSHOT.jar /app/schedule.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "-Xmx256m", "/app/schedule.jar"]
