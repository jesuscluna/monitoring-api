FROM eclipse-temurin:17-jdk-alpine
COPY target/monitoring.jar monitoring.jar
ENTRYPOINT ["java","-jar", "/monitoring.jar"]