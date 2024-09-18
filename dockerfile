# Use an official base image
FROM openjdk:17-jdk-slim

# Set environment variables
ARG APP_PORT
ARG DB_URL
ARG DB_USERNAME
ARG DB_PASSWORD
ARG OWNER_USERNAME
ARG OWNER_PASSWORD
ARG CLUB_MAIL
ARG CLUB_PASSWORD
ARG HASH_KEY

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/auth-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE ${APP_PORT}

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
