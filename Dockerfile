# Use Maven to build the application
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Use a slim version of OpenJDK for the runtime image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the compiled JAR file into the container
COPY --from=build target/Application-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
