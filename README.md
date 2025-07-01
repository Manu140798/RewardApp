# RewardApp
This app will calculate reward for customers.

## Features

- Calculate reward points from transactions
- Fetch rewards for all customers or specific customers
- Monthly reward breakdown
- RESTful APIs with date range filters
- Connected to MySQL database

---

## 🔧 Technologies Used

- Java 17+
- Spring Boot
- Spring Data JPA
- MySQL

---

## 🗂 Project Structure

com.rewardapp
├── RewardProgramApplication.java         # Main class
├── rewardcontroller                      # REST controller
│   └── RewardController.java
├── rewardservice                         # Service layer
│   └── RewardService.java
├── rewardrepository                      # JPA repository
│   └── TransactionRepository.java
├── rewardprogramdto                      # DTOs & Entity
│   ├── Customer.java
    ├──RewardResponse.java
│   ├── Transaction.java
│   └── TransactionDetail.java
├── rewardutil
│   └── RewardCalculator.java             # Points calculation logic
├── rewardexception
    └── APIExceptionHandler.java
│   └── CustomerNotFoundException.java

#Setup Instruction
Install MySQL and create the database:
CREATE DATABASE reward_app;
Create user and grant access:


# Configure application.properties
server.port=8083
spring.datasource.url=jdbc:mysql://localhost:3306/rewardprogram
spring.datasource.username=root
spring.datasource.password=admin
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Run the Application
./mvnw spring-boot:run
App will start on: http://localhost:8083

# Sample API Calls
Get rewards for all customers:
GET /api/rewards?startDate=2024-04-01&endDate=2024-06-30
curl "http://localhost:8083/api/rewards?startDate=2024-04-01&endDate=2024-06-30"

Get rewards for a specific customer:
GET /api/rewards/{customer}?startDate=YYYY-MM-DD&endDate=YYYY-MM-DD
curl "http://localhost:8083/api/rewards/JohnDoe?startDate=2024-04-01&endDate=2024-06-30"

# Sample Data Insert
SQL
INSERT INTO transactions (customer_name, amount, date) VALUES
('satyam', 120.00, '2024-05-15'),
('ManuTiwari', 130.00, '2024-05-18'),
('TanuTiwari', 200.00, '2024-05-15');

# Reward Point Logic
No points for transactions ≤ $50

1 point per $ over $50

2 points per $ over $100 (cumulative)

Example:

$120 → (50 x 1) + (20 x 2) = 90 points


📃 License
This project is for educational/demo purposes.

yaml
Copy
Edit
