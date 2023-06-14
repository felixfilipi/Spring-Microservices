FROM eclipse-temurin:17

LABEL mentainer="felixfilipi4@gmail.com"

WORKDIR /app

COPY target/service-registry-0.0.1-SNAPSHOT.jar /app/service-registry.jar

ENTRYPOINT ["java", "-jar", "service-registry.jar"]