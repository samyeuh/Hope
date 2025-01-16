# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml ./
RUN mvn dependency:go-offline -B

# Copy the source code and build the application
COPY src ./src
RUN mvn package -DskipTests


FROM openjdk:17-jdk-alpine AS runtime
WORKDIR /app
COPY --from=build app/target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar", "--spring.config.location=optional:classpath:/,optional:file:config/"]