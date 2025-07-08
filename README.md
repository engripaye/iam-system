## IAM (IDENTITY ACCESS MANAGEMENT) SYSTEM

🔧 Project: Simple IAM System

A web app with:
•👤 User registration & login
•🛡 Role-based access control (Admin vs. User)
•✅ JWT authentication
•🔒 Secured endpoints

⚙ Tech Stack:
•Java 21
•Spring Boot 3.x
•Spring Security
•JWT (JSON Web Token)
•Maven
•In-memory storage (Map, not DB, for simplicity)

📁 Project Structure

simple-iam/
├── controller/
│   └── http://AuthController.java
│   └── http://HelloController.java
├── model/
│   └── http://User.java
│   └── http://Role.java
├── security/
│   └── http://JwtUtil.java
│   └── http://SecurityConfig.java
│   └── http://JwtFilter.java
├── service/
│   └── http://UserService.java
├── http://SimpleIamApplication.java
└── pom.xml
