# Use the official OpenJDK base image
FROM openjdk:latest

# Set the working directory inside the container
WORKDIR /app

# Copy local code to the container
COPY . /app

# Use Gradle to build the game
RUN ./gradlew build

# Specify the command to run on container start
CMD ["./gradlew", "run"]