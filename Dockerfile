# Build stage
FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copy pom.xml first for better caching
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source and build
COPY src ./src
RUN mvn clean package -DskipTests -B

# Run stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Create a non-root user
RUN addgroup -g 1001 -S appuser && adduser -u 1001 -S appuser -G appuser

# Copy the jar with wildcard to handle any version
COPY --from=build /app/target/*.jar app.jar

# Change ownership
RUN chown -R appuser:appuser /app

# Switch to non-root user
USER appuser

# Expose port (Render uses PORT env var, defaults to 8080)
EXPOSE 8080

# Run with explicit memory settings and production profile
# Use shell form to allow environment variable substitution
ENTRYPOINT sh -c 'java \
    -Xms256m \
    -Xmx512m \
    -XX:+UseG1GC \
    -Dspring.profiles.active=prod \
    -Djava.security.egd=file:/dev/./urandom \
    -Dspring.datasource.url=jdbc:${DATABASE_URL} \
    -jar app.jar'