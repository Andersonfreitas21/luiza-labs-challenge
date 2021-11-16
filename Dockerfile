FROM openjdk:11
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
WORKDIR /app/
COPY target/schedule-request-0.0.1-SNAPSHOT.jar /app/schedule.jar
ENTRYPOINT ["java", "-jar", "-Xmx256m", "/app/schedule.jar"]
