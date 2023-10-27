FROM maven:3.9.5-amazoncorretto-21 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package spring-boot:repackage 

FROM openjdk:21
LABEL Gustavo Pontalti
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000
WORKDIR /app
COPY --from=build /home/app/target/fiscalcode.jar fiscalcode.jar
EXPOSE 8080 8000
CMD java -jar fiscalcode.jar