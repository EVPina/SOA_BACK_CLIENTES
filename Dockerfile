FROM eclipse-temurin:17-jdk-alpine
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
WORKDIR /app
COPY --chown=appuser:appgroup target/soaclientes-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
USER appuser
ENTRYPOINT ["java", "-jar", "app.jar"]