
# syntax=docker/dockerfile:1.7

# Build Stage - Java 27
FROM eclipse-temurin:27-jdk-alpine AS build

RUN apk add --no-cache curl

WORKDIR /home/app

# Maven Wrapper
COPY mvnw ./
COPY .mvn/ ./.mvn/
COPY pom.xml ./

RUN chmod +x ./mvnw

# Application sources
COPY src/ ./src/

# Compile and package
RUN --mount=type=cache,target=/root/.m2 \
    ./mvnw -B clean package


# Runtime Stage - Java 27
FROM eclipse-temurin:27-jre-alpine

LABEL org.opencontainers.image.authors="Gustavo Pontalti"

WORKDIR /app

COPY --from=build /home/app/target/fiscalcode.jar ./fiscalcode.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "fiscalcode.jar"]
