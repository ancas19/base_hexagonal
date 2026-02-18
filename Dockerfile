# Runtime stage for Spring Boot application
FROM eclipse-temurin:25-jre-alpine
# Set working directory
WORKDIR /app
# Create a non-root user for security
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
# Copy the pre-built JAR file
COPY *.jar app.jar
# Change ownership to non-root user
RUN chown -R appuser:appgroup /app
# Switch to non-root user
USER appuser
# Expose the application port (Spring Boot default is 8080)
EXPOSE 8080
# JVM options for containerized environments
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:+UseG1GC"
# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

