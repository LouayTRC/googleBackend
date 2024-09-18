# Use an official base image
FROM openjdk:17-jdk-slim

# Set build-time environment variables
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

# Set environment variables that will be available to the container
ENV DB_URL=${DB_URL}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}
ENV OWNER_USERNAME=${OWNER_USERNAME}
ENV OWNER_PASSWORD=${OWNER_PASSWORD}
ENV CLUB_MAIL=${CLUB_MAIL}
ENV CLUB_PASSWORD=${CLUB_PASSWORD}
ENV HASH_KEY=${HASH_KEY}

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
