# monitoring-api
API REST Solution for managing user behavior inside a digital platform.

# Author
- Jesus Caraballo

# Requirements
- Java 17
- [Maven](https://maven.apache.org/install.html)
- Git
- Docker
- Postman

# Simple Development Guide
## Before to start
```sh
  # Clone
  git clone https://github.com/jesuscluna/monitoring-api.git
```
```sh
  cd monitoring-api
```
## Steps
1. Build the project with maven
```sh
  mvn clean install
```   
2. Build Docker image
```sh
  docker build -t <NOMBRE_IMAGEN>:<TAG> .
```
3. Run Docker container
```sh
  docker run -p <PUERTO_EXTERNO>:<PUERTO_INTERNO> -d <NOMBRE_IMAGEN>:<TAG> 
```
4. The webservices are accessible via localhost:8080. You can see endpoints code in [Controller](src/main/java/com/prevalentware/monitoringapp/controller)
5. Import [collection](PREVALENTWARE%20MONITORINGAPP.postman_collection.json) in Postman for an overview.

# Decisions and Justifications
### 1. Spring Boot Version:
* Decision: Spring Boot 2.7.18 is chosen as the base version.
* Justification: Version 2.7.18 provides stability and compatibility with the latest libraries, ensuring a robust foundation.
### 2. Java Version:
* Decision: Java 17 is specified as the Java version.
* Justification: Java 17, being a Long-Term Support (LTS) version, offers advanced features and long-term support.
### 3. Main Dependencies:
* Decision: Essential dependencies like `spring-boot-starter-data-jpa`, `spring-boot-starter-security`, `spring-boot-starter-web`, and `spring-boot-starter-test` are included.
* Justification: These dependencies offer fundamental functionalities for secure and efficient web application development.
### 4. JWT Token Handling:
* Decision: JJWT (JSON Web Token) library version 0.9.1 is used.
* Justification: JJWT is a widely adopted library for JWT token generation and validation, commonly used in security applications.
### 5. Persistence of Data (JPA/Hibernate and PostgreSQL):
* Decision: JPA/Hibernate is chosen as the Object-Relational Mapping (ORM) tool for managing the PostgreSQL database.
* Justification: This combination of JPA/Hibernate and PostgreSQL is powerful, providing an effective way to interact between the application and the relational database.
### 6. Lombok:
* Decision: Lombok is included with the optional option set to true.
* Justification: Lombok simplifies code writing by automatically generating standard methods, improving code readability.

# Basic AWS deployment proposal
All this information is researched-based.
## AWS Lambda deployment
### 1. Preparing the code
* Validate compatibility of SpringBoot application with AWS Lambda. We can use [AWS SAM](https://aws.amazon.com/es/serverless/sam/) for deployment.
* Adapt if necessary the application in order to work as an AWS Lambda function. In some case, we need to make adjustments.
### 2. Creating SAM file
* Create a `template.yaml` or `template.yml` file, using AWS SAM in order to describe the application and resources. This file must content relevant information about Lambda function, events and any other required resource.
### 3. Deploying with AWS SAM
* Using AWS SAM CLI for packing and deploying AWS Lambda app
```sh
  sam package --template-file template.yaml --output-template-file packaged.yaml --s3-bucket your-s3-bucket-name
```
```sh
  sam deploy --template-file packaged.yaml --stack-name your-stack-name --capabilities CAPABILITY_IAM
```
### 4. Setting Lambda event
* Configure event that will trigger Lambda function. We can use events such as API Gateway, S3, or custom events.
## Amazon ECS deployment
### 1. Dockerized
* Validate the correct dockerized of application. Create a `Dockerfile` and `.dockerignore` in order to build an app Docker image.
### 2. Storing the image
* Build and upload the Docker image in a container record, like Amazon ECR (Elastic Container Registry).
```sh
  docker build -t your-image-name .
```
```sh
  docker tag your-image-name:latest your-ecr-repository-uri/your-image-name:latest
```
```sh
  docker push your-ecr-repository-uri/your-image-name:latest
```
### 3. Amazon ECS configuration
* Create ECS cluster and define a task that point to Docker image in the container record.
* Configure an ECS service in order to execute the task and handle scalability.
### 4. ECS deployment
* Deploy the service in ECS using ECS CLI, AWS CLI or another orchestration services like AWS CloudFormation.