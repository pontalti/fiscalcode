# 🇮🇹 Italian Fiscal Code Generator - Setup & Usage Guide

A Spring Boot application to generate and decode Italian fiscal codes.

---

## 🧰 Development Environment

- **OS**: Windows 10  
- **IDE**: Eclipse IDE for Enterprise Java and Web Developers 2021-09 (4.21.0)  
  - Plugins:
    - Spring Tools 4 for Eclipse (v4.12.1)
    - Java 21 Support for Eclipse
    - Project Lombok
- **Java**: JDK 21  
- **Build Tool**: Maven 3.6.3  
- **Version Control**: Git 2.34.0  
- **GitHub CLI**: gh 2.2.0  
- **API Client**: Postman (Windows version)  
- **Tools**:
  - curl 7.55.1
  - Docker:
    - Docker Engine 20.10.11
    - Docker Desktop 4.3.0

---

## ⚙️ Prerequisites & Setup

### 1. Install Git  
Download and install from:  
👉 [https://git-scm.com/downloads](https://git-scm.com/downloads)

Run the following after installation:  
```bash
git config --global core.autocrlf true
```

### 2. Install GitHub CLI  
👉 [https://cli.github.com/manual/installation](https://cli.github.com/manual/installation)

### 3. Clone the Repository  
```bash
git clone git@github.com:pontalti/fiscalcode.git
```

### 4. Install JDK 21  
👉 [https://www.oracle.com/java/technologies/downloads/](https://www.oracle.com/java/technologies/downloads/)

Set `JAVA_HOME` and update your `PATH`:  
**Windows:**
```bash
JAVA_HOME=C:\Path\To\jdk-21
set PATH=%JAVA_HOME%\bin;%PATH%
```
**Linux:**
```bash
export JAVA_HOME=/path/to/jdk-21
export PATH=$JAVA_HOME/bin:$PATH
```

Verify installation:  
```bash
java -version
```

### 5. Install Maven  
👉 [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)

Set `M2_HOME` and update your `PATH`:  
**Windows:**
```bash
M2_HOME=C:\Path\To\apache-maven-3.6.3
set PATH=%M2_HOME%\bin;%PATH%
```
**Linux:**
```bash
export M2_HOME=/path/to/apache-maven-3.6.3
export PATH=$M2_HOME/bin:$PATH
```

Verify installation:  
```bash
mvn --version
```

### 6. Install an IDE with Java 21 support  
E.g., Eclipse, IntelliJ IDEA, VSCode

### 7. Install Project Lombok  
👉 [https://projectlombok.org/setup/overview](https://projectlombok.org/setup/overview)

---

## 🚀 Build & Run

### Build the Application
From the project root:
```bash
mvn -U clean install package spring-boot:repackage
```

### Run the Application (Local)
```bash
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=*:8085"
```
**Or**
```bash
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8085 -jar target/fiscalcode.jar
```

Configure **Spring Boot Remote DevTools**:  
- URL: `http://localhost:8080/`  
- Secret: `teste`

### Run the Application with Docker

Ensure Docker is installed and running.

From the project root:
```bash
docker-compose build
docker-compose up -d
```

Check logs:
```bash
docker logs -f fiscal-code
```

---

## 🧪 Testing the API

### Install curl *(if needed)*  
**Windows**:
```bash
choco install curl
```
**Linux (Ubuntu/Debian)**:
```bash
sudo apt-get install curl
```
**Linux (RHEL/CentOS/Fedora)**:
```bash
sudo yum install curl
```

### Test with curl
```bash
curl -i -X GET "http://localhost:8080/discoverTaxCodeDetails" -H "Content-Type: application/json" -d "{\"taxCode\":\"SZODND83A27L378E\"}"
```
```bash
curl -i -X GET "http://localhost:8080/discoverTaxCode" -H "Content-Type: application/json" -d "{\"name\":\"Edmundo\",\"surname\":\"Souza\",\"dateOfBirth\":\"27/01/1983\",\"placeOfBirth\":\"TRENTO\",\"gender\":\"M\", \"state\":\"TN\"}"
```

### Install Postman *(if needed)*  
👉 [https://learning.postman.com/docs/getting-started/installation-and-updates/](https://learning.postman.com/docs/getting-started/installation-and-updates/)

### Test with Postman

#### Endpoint: `/discoverTaxCode`
- Method: `GET`  
- URL: `http://localhost:8080/discoverTaxCode`  
- Headers:
  - `Content-Type: application/json`
- Body (raw → JSON):
```json
{
  "name": "Edmundo",
  "surname": "Souza",
  "dateOfBirth": "27/01/1983",
  "placeOfBirth": "TRENTO",
  "gender": "M",
  "state": "TN"
}
```

#### Endpoint: `/discoverTaxCodeDetails`
- Method: `GET`  
- URL: `http://localhost:8080/discoverTaxCodeDetails`  
- Headers:
  - `Content-Type: application/json`
- Body (raw → JSON):
```json
{
  "taxCode": "SZODND83A27L378E"
}
```

---

## 📚 API Documentation

Access the OpenAPI (Swagger UI) documentation at:  
👉 `http://localhost:8080/swagger-ui/index.html`
