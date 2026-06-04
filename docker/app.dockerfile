FROM maven:3.9.11-eclipse-temurin-25-alpine AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package spring-boot:repackage 

FROM eclipse-temurin:25-jre-alpine
LABEL Gustavo Pontalti
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000
WORKDIR /app
COPY --from=build /home/app/target/fiscalcode.jar fiscalcode.jar
EXPOSE 8080 8000
CMD ["java", "-jar", "fiscalcode.jar"]
