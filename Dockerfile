# ==========================================
# Stage 1: Build the application artifact
# ==========================================
FROM gradle:8.8-jdk17 AS builder
WORKDIR /app

# Copy only the dependency configuration files first to leverage Docker layer caching
COPY build.gradle settings.gradle ./

# Copy the actual source code layer
COPY src ./src

# Compile and package the application jar, bypassing unit tests to speed up the build image
RUN gradle bootJar -x test --no-daemon

# ==========================================
# Stage 2: Create the lightweight runtime image
# ==========================================
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Set all database connection environment variables directly inside the image
ENV DB_USERNAME=postgres.tymsvhihcwwzjkrfkyol
ENV DB_PASSWORD=supabase.gestion
ENV DB_URL=jdbc:postgresql://aws-1-us-east-1.pooler.supabase.com:5432/postgres

# Create a non-privileged system user for improved container security
RUN useradd -m springuser && chown -R springuser:springuser /app
USER springuser

# Copy the compiled jar from the builder stage and rename it for simplicity
COPY --from=builder /app/build/libs/*-SNAPSHOT.jar app.jar

# Document that the container listens on port 8090 at runtime
EXPOSE 8090

# Optimize Java performance flags for container environments
ENTRYPOINT ["java", "-XX:+UseG1GC", "-XX:+ExitOnOutOfMemoryError", "-jar", "app.jar"]