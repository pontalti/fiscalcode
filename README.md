# Italian fiscal code generator instructions

1. My Dev enviroment 👍
   - Windows 10
   - Eclipse IDE for Enterprise Java and Web Developers 2021-09 (4.21.0)
   		- Eclipse Plugins
   			- Spring Tools 4 for Eclipse (Spring Tool Suite 4 - 4.12.1 ) 
   			- Java 21 support for Eclipse
   			- Project Lombok
   - JDK 21
   - Maven  3.6.3
   - git 2.34.0.windows.1
   - gh version 2.2.0
   - curl 7.55.1
   - Postman for Windows Version
   - Docker
		- Docker for Windows (Docker version 20.10.11) 
		- Docker Desktop for Windows 4.3.0

2. Install if necessary git, follow the instruction on the link below.
	- ```  
	   https://git-scm.com/downloads 
	  ```
	- After install run the command below in the terminal
		- ``` git config --global core.autocrlf true ```

3. Install if necessary gh, follow the instruction on the link below.
	- ``` https://cli.github.com/manual/installation ```

4. try to access the link below
	- ``` https://github.com/pontalti/fiscalcode ```

5. Clone the repository
	- ``` git clone git@github.com:pontalti/fiscalcode.git ```

6. If necessary install the JDK 21, download it on the link below
	- ``` https://www.oracle.com/java/technologies/downloads/ ```
	- Choose your distribution and install the JDK
	- Create the Java Home
		- Windows -> ``` JAVA_HOME = [YOUR_PATCH]\jdk-21 ```
		- Linux -> ``` JAVA_HOME = [YOUR_PATCH]/jdk-21 ```
	- Put the JAVA_HOME on the System Patch
		- For Windows -> ``` %JAVA_HOME%\bin ```
		- For Linux -> ``` export PATH=$JAVA_HOME/bin:$PATH ```
	- Test JDK on command line
		- ``` java -version ```		

7. If necessary install Maven, download it on the link below
	- ``` https://maven.apache.org/download.cgi ```
	- Extract compressed file in your prefered tool folder.
	- Create the M2_HOME
		- Windows -> ``` M2_HOME = [YOUR_PATCH]\apache-maven-3.6.3 ```
		- Linux -> ``` M2_HOME = [YOUR_PATCH]/apache-maven-3.6.3 ```
	- Put the Maven on the System Patch
		- For Windows -> ``` %M2_HOME%\bin ```
		- For Linux -> ``` export PATH=$M2_HOME/bin:$PATH ```
	- Test Maven on command line
		- ``` mvn --version ```

8. If necessary install your favorite IDE with support to JDK 21.

9. if necessary Install the project Lombok on your IDE, follow the instruction on the link below.
	- ``` https://projectlombok.org/setup/overview ```

10. Open the project in favotite IDE

11. To build please.
	- Go to the project root folder.
	- Run the command below.
		- ``` mvn -U clean install package spring-boot:repackage ```

12. To run the SpringBoot application in localhost.
	- Go to the project root folder.
	- Run the command below.
		- ``` mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=*:8080" ```
	- OR
		- ``` java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8080 -jar target\fiscalcode-0.0.1.jar ```
	- To use the Spring dev tools features please configure the -> ``` Spring Boot Remote ```
		- Remote URL -> ``` http://localhost:8080/ ```
		- Remote Secret -> ``` teste ```

13. To run the SpringBoot application with Docker.
	- Please install Docker.
	- Go to the project root folder.
	- Run the commands below.
		- ``` docker-compose build ```			
		- ``` docker-compose up -d ```
	- To check the log, please run the command below.
		- ``` docker logs -f fiscal-code ```
	- To use the Spring dev tools features please configure the -> ``` Spring Boot Remote ```
		- Remote URL -> ``` http://localhost:8080/ ```
		- Remote Secret -> ``` teste ```
		
14. if necessary install curl on Windows or Linux.
	- for Windows -> ``` choco install curl ```
	- for Linux(Ubuntu/Debian) -> ``` apt-get install curl ```
	- for Linux(RHEL/CentOS/Fedora) -> ``` yum install curl ```
	
15. To call the services using curl please use the commands below on the Windows(CMD) orLinux(Terminal).
	- ``` curl -i -X GET "http://localhost:8080/discoverTaxCodeDetails" -H "Content-Type: application/json" -d "{\"taxCode\":\"SZODND83A27L378E\"}" ```
	- ``` curl -i -X GET "http://localhost:8080/discoverTaxCode" -H "Content-Type: application/json" -d "{\"name\":\"Edmundo\",\"surname\":\"Souza\",\"dateOfBirth\":\"27/01/1983\",\"placeOfBirth\":\"TRENTO\",\"gender\":\"M\", \"state\":\"TN\"}" ```

16. if necessary install POSTMAN, follow the instructions in the link below.
	- ``` https://learning.postman.com/docs/getting-started/installation-and-updates/ ```

17. To run discoverTaxCode service with POSTMAN.
	- Copy and paste the URL -> ``` http://localhost:8080/discoverTaxCode ```
	- Open a new request and choose -> ``` GET ```
	- Add the in the Headers section -> ``` Content-Type: application/json ```
	- In the body section choose -> ``` RAW ```
	- In the body section select -> ``` JSON in the drop list ```
	- Copy and past the json below
		``` 
		{
		    "name": "Edmundo",
		    "surname": "Souza",
		    "dateOfBirth": "27/01/1983",
		    "placeOfBirth": "TRENTO",
		    "gender": "M",
		    "state": "TN"
		}
		```
		
		
18. To run discoverTaxCodeDetails service with POSTMAN.
	- Copy and paste the URL -> ``` http://localhost:8080/discoverTaxCodeDetails ```
	- Open a new request and choose -> ``` GET ```
	- Add the in the Headers section -> ``` Content-Type: application/json ```
	- In the body section choose -> ``` RAW ```
	- In the body section select -> ``` JSON in the drop list ```
	- Copy and past the json below.
		- ```{"taxCode": "SZODND83A27L378E"}```

19. To access the OpenAPI definition, please use the link below
	- ``` http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/ ``` 


