# Use a compatible JRE for Java 17
FROM eclipse-temurin:17-jre-alpine

# Set the working directory
WORKDIR /demo

ENV DB_URL=jdbc:postgresql://aws-1-us-east-1.pooler.supabase.com:5432/postgres
#ENV DB_URL=jdbc:postgresql://aws-1-us-east-1.pooler.supabase.com:6543/postgres
ENV DB_USERNAME=postgres.tymsvhihcwwzjkrfkyol
ENV DB_PASSWORD=supabase.gestion
ENV CORS_URL=*

# Copy the JAR built by Gradle
# Note: 'build/libs/' is the standard location for your project
COPY demo/build/libs/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8090

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]