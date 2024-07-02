# Spring Boot Logging and Security Application

This Spring Boot application provides a simple CRUD functionality for managing courses, along with user authentication and authorization using Spring Security and JSON Web Tokens (JWT).

## Features

1. **Course Management**:
    - CRUD operations for managing courses.
    - Input validation to ensure data integrity.

2. **User Authentication and Authorization**:
    - Login mechanism with role-based access control.
    - Default users with predefined roles (admin-admin and user-zumisg).
    - Access restrictions applied to CRUD operations based on user roles.
    - Logout functionality to terminate user sessions.

3. **User Registration**:
    - Sign-up page for new user registration.
    - Newly registered users have default USER role.
    - Access to course listing after successful registration and login.

4. **Logging**:
    - Logging implemented using SLF4J and Logback.
    - Different logging levels used for controller actions (INFO, WARN, ERROR).

5. **JSON Web Token (JWT) Authentication** (Advanced Task):
    - JWT authentication implemented for enhanced security.
    - Token generation and validation for user authentication.

## Prerequisites

- JDK 8 or higher
- Gradle
- Git

## Getting Started

1. Clone the repository:

   ```bash
   git clone https://github.com/SITE-ADA/as1-spring-boot-logging-and-security-zumrud_isgandarli.git

2. Navigate to the project directory:
   cd as1-spring-boot-logging-and-security-zumrud_isgandarli

3. Build the project:
   ./gradlew build

4. Run the application:
   ./gradlew bootRun

5. Access the application in your web browser at http://localhost:8080.


## To run the Advance part, comment out other files.


Usage
-----

*   **Unregistered Users**: All unregistered users can enter the home page at [http://localhost:8080](http://localhost:8080). However, they won't be able to access course details or perform any CRUD operations.
*   **Viewing Courses**:
    *   To view courses, click on "Signup" on the navbar, create an account, and then log in.
    *   If you already have an account, go directly to the login page by clicking on "Login" on the navbar.
*   **Admin User**: Admin users can log in, navigate to the courses section, and perform CRUD operations on courses.

Video Recording
---------------

A video recording demonstrating the usage of the application is available [here](https://adauniversity-my.sharepoint.com/:v:/g/personal/zisgandarli15645_ada_edu_az/EVQFTj1f9ShPiuFZhEdwUgYB-uoGqR8TSy90cIThlYLTXQ?e=J0xnUn&nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJTdHJlYW1XZWJBcHAiLCJyZWZlcnJhbFZpZXciOiJTaGFyZURpYWxvZy1MaW5rIiwicmVmZXJyYWxBcHBQbGF0Zm9ybSI6IldlYiIsInJlZmVycmFsTW9kZSI6InZpZXcifX0%3D).
https://adauniversity-my.sharepoint.com/:v:/g/personal/zisgandarli15645_ada_edu_az/EVQFTj1f9ShPiuFZhEdwUgYB-uoGqR8TSy90cIThlYLTXQ?e=J0xnUn&nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJTdHJlYW1XZWJBcHAiLCJyZWZlcnJhbFZpZXciOiJTaGFyZURpYWxvZy1MaW5rIiwicmVmZXJyYWxBcHBQbGF0Zm9ybSI6IldlYiIsInJlZmVycmFsTW9kZSI6InZpZXcifX0%3D

For any issues or feedback, please open an issue in the [GitHub repository](https://github.com/SITE-ADA/as1-spring-boot-logging-and-security-zumrud_isgandarli).

  