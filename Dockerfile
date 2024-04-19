FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY target/fibonacci-1.0-SNAPSHOT.jar /app/fibonacci-1.0-SNAPSHOT.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "fibonacci-1.0-SNAPSHOT.jar"]
