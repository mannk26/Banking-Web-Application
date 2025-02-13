# Banking Web Application

## Overview
The **Banking Web Application** is a full-stack web application that allows users to perform banking operations such as account creation, fund transfers, and transaction history tracking. It is built using Java, Spring Boot, JSP, and MySQL.

## Features
- **User Authentication:** Secure login and registration system.
- **Account Management:** Users can create and manage bank accounts.
- **Fund Transfer:** Secure and seamless money transfers between accounts.
- **Transaction History:** View detailed transaction logs.
- **Admin Panel:** Manage users and transactions.

## Technologies Used
- **Backend:** Java, Spring Boot, Hibernate, JSP, Servlets
- **Frontend:** HTML, CSS, JSP
- **Database:** MySQL
- **Build Tool:** Maven
- **Version Control:** Git, GitHub

## Project Structure
```
BankingApplication/
│── src/main/java/com/banking
│   ├── controller/     # Servlets and Controllers
│   ├── dao/            # Data Access Objects (DAO)
│   ├── model/          # Entity classes
│   ├── service/        # Business logic layer
│   ├── utils/          # Utility classes
│── src/main/webapp/
│   ├── WEB-INF/        # JSP files and configurations
│   ├── assets/         # CSS, JS, and images
│── pom.xml            # Maven dependencies
│── README.md          # Project documentation
```

## Setup Instructions
### Prerequisites
- Java 8 or later
- MySQL database
- Maven
- Tomcat Server (or any servlet container)

### Steps to Run the Application
1. **Clone the repository:**
   ```sh
   git clone https://github.com/mannk26/Banking-Web-Application.git
   cd Banking-Web-Application
   ```
2. **Configure Database:**
   - Create a MySQL database named `banking_db`.
   - Update `application.properties` with your MySQL credentials.
   
3. **Build and Run the Application:**
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```
4. **Access the Application:**
   - Open `http://localhost:8080` in your browser.

## Future Enhancements
- Implement JWT-based authentication
- Add microservices architecture
- Introduce AI-based fraud detection

## Contributing
Contributions are welcome! Feel free to submit issues or pull requests.

## License
This project is licensed under the MIT License.

---
**Author:** [mannk26](https://github.com/mannk26)

