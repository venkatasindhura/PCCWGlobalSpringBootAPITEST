Instructions to run the spring boot application in Docker
I would like to implement a backend API to register a user, it can also edit/read/(soft) delete single or multiple user(s). Once registration done, automatically notification sends to gmail.
Technologies Used to implement the Module:
1, Spring Boot.
2, Spring JPA.
3, MySQL
4, Maven 
5, Eclipse
5, Docker to run the spring boot application.
Please find below steps to create the run the application.
1, Created Spring Boot application using Maven build tool in eclipse.
2, And Created packages for model, controller, exceptions, repository and api to write email   service.
3, Implemented the module based on PCCW Global document. Created sample smtp server for      email service.
4, Created jar file for the application using Maven Test and 
5, For that we should give target location to save the Jar file.
6, Then created one Docker file inside the application named as “Docker.file” . Inside the file, we give configurations to run the application in the Docker such as port , Jar file and entry point to run the Jar.
Docker. File
FROM openjdk:8
EXPOSE 8084
ADD target/PCCWGlobalSpringbootRestAPI.jar PCCWGlobalSpringbootRestAPI.jar
ENTRYPOINT ["java","-jar","/PCCWGlobalSpringbootRestAPI.jar"].
7, First we need to install Docker based on OS.
8, Docker Engine has to be initialized. Once Started, it shows the Docker is running.
9, Open the docker command prompt and open the spring boot project directory.
10, Then run the command “docker build -t PCCWGlobalSpringbootRestAPI.jar”.
11, Click enter then it shows the configurations. The docker image created successfully.
       If we want to see docker image, we run the command “docker image ls”.
12, Run the command “docker run -p 9090: 8089 PCCWGlobalSpringbootRestAPI.jar”. The spring application is exposing  with the Port 8089. Docker is running with the host 9090.
13, It runs the spring boot application in Docker.
14, Go to browser and open http://localhost:9090/api/users.
15, Or else whenever we created docker, it gives the IP address at the time installation. 
16, Go to browser http://Ipaddress:9090/api/users.
Thank you.

