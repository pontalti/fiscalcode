FROM openjdk:17
LABEL Gustavo Pontalti
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000
WORKDIR /app
COPY /target/fiscalcode-0.0.1.jar fiscalcode.jar
EXPOSE 8080 8000
CMD java -jar fiscalcode.jar
