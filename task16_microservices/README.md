# Task 16: Microservices API Gateway and Load Balancing

This project contains two Spring Boot applications:
1. `student-service`: A simple microservice exposing a `/students` REST API.
2. `api-gateway`: A Spring Cloud Gateway that routes requests to `student-service` and performs load balancing.

---

## Instructions to Import and Run in Eclipse

### 1. Import Projects
1. Open Eclipse IDE.
2. Go to **File** -> **Import**.
3. Select **Maven** -> **Existing Maven Projects** and click **Next**.
4. Browse to the directory containing this project (`task16_microservices`).
5. Select both `api-gateway/pom.xml` and `student-service/pom.xml`.
6. Click **Finish**. Eclipse will download all necessary Maven dependencies.

### 2. Run Multiple Instances of `student-service`
You need to run two instances of the `student-service` on different ports (8081 and 8082).

**Instance 1 (Port 8081):**
1. In Eclipse, right-click on `StudentServiceApplication.java` -> **Run As** -> **Spring Boot App** (or **Java Application**).
2. By default, the `application.properties` specifies `server.port=${port:8081}`, so this will run on port 8081.

**Instance 2 (Port 8082):**
1. In Eclipse, go to **Run** -> **Run Configurations...**
2. Create a new configuration for **Java Application** (or Spring Boot App).
3. Name it `StudentService - 8082`.
4. Main Class: `com.example.studentservice.StudentServiceApplication`.
5. Go to the **Arguments** tab.
6. In **VM arguments**, add: `-Dserver.port=8082` (or in Program arguments: `--server.port=8082`).
7. Click **Apply** and then **Run**.

Check the console to verify that both instances are running successfully on ports 8081 and 8082.

### 3. Run the API Gateway
1. Right-click on `ApiGatewayApplication.java` -> **Run As** -> **Spring Boot App** (or **Java Application**).
2. The gateway will start on port `8080`.

---

## How to Test Using Browser / Postman

1. Open your browser or Postman.
2. Make a GET request to the gateway URL:
   `http://localhost:8080/students`
3. Hit the endpoint multiple times (refresh the page or click "Send" in Postman).

### Expected Output
Because the API Gateway is configured to use Spring Cloud LoadBalancer in a Round-Robin fashion, you will see the responses alternate between the two `student-service` instances:

**First Request:**
`Response from Student Service - Port: 8081`

**Second Request:**
`Response from Student Service - Port: 8082`

**Third Request:**
`Response from Student Service - Port: 8081`

(And so on...)
