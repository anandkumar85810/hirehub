\# HireHub — Job Portal Backend



A RESTful Job Portal Backend built using \*\*Java, Spring Boot, Spring Security, JWT Authentication, Spring Data JPA, and MySQL\*\*. HireHub provides APIs for managing users, companies, job listings, job applications, candidate profiles, recruiter profiles, and interviews.



The project focuses on backend development concepts such as REST API design, entity relationships, DTO-based data transfer, input validation, authentication, authorization, and database integration.



\## 🚀 Features



\### 1. Authentication and Authorization

\- User authentication using JWT (JSON Web Tokens).

\- Spring Security integration.

\- Role-based access control.

\- Password hashing using BCrypt.

\- Protected REST API endpoints.

\- Custom security error handling for authentication and authorization failures.



\### 2. User Management

\- User entity and database integration.

\- Role-based users, including Candidate, Company, Recruiter, and Admin.

\- User management endpoints with restricted access.



\### 3. Company Management

\- Create and manage company information.

\- Company details such as name, description, website, industry, location, phone, and email.

\- Company and job relationship management.



\### 4. Job Management

\- Create, view, update, and delete job listings.

\- Job details include title, description, location, salary range, required experience, employment type, and status.

\- Associate jobs with companies.



\### 5. Job Applications

\- Candidates can apply for jobs through the application API.

\- Retrieve an application by ID.

\- Retrieve applications by candidate ID.

\- Retrieve applications by job ID.

\- Retrieve all applications.

\- Track application status and application date.

\- Prevent duplicate applications for the same candidate and job.



\### 6. Candidate Profiles

\- Create and manage candidate profiles.

\- Store education, experience, skills, expected salary, location, profile photo, and bio.

\- Associate candidate profiles with users.



\### 7. Recruiter Profiles

\- Recruiter profile management and related API implementation.



\### 8. Interview Management

\- Schedule and manage interviews associated with job applications.

\- Store interview type, scheduled date and time, meeting link, status, and feedback.

\- Update and delete interview records.



\## 🛠️ Tech Stack



\- \*\*Language:\*\* Java

\- \*\*Backend Framework:\*\* Spring Boot

\- \*\*Security:\*\* Spring Security

\- \*\*Authentication:\*\* JWT

\- \*\*Password Encryption:\*\* BCrypt

\- \*\*ORM:\*\* Hibernate / Spring Data JPA

\- \*\*Database:\*\* MySQL

\- \*\*Build Tool:\*\* Maven

\- \*\*API Testing:\*\* Postman

\- \*\*Version Control:\*\* Git and GitHub



\## 🏗️ Project Architecture



The application follows a layered architecture:



\- \*\*Controller Layer:\*\* Exposes REST API endpoints and handles HTTP requests.

\- \*\*Service Layer:\*\* Contains business logic and application workflows.

\- \*\*Repository Layer:\*\* Communicates with the database using Spring Data JPA.

\- \*\*Entity Layer:\*\* Defines database entities and their relationships.

\- \*\*DTO Layer:\*\* Separates request and response data from entity objects.

\- \*\*Security Layer:\*\* Handles JWT authentication, authorization, and role-based access.

\- \*\*Exception Handling:\*\* Provides custom error handling for application and security errors.



\## 🗄️ Main Entities



The project includes the following main entities:



\- User

\- Company

\- Job

\- JobApplication

\- CandidateProfile

\- RecruiterProfile

\- Interview



\### Key Relationships



\- One Company can have multiple Jobs.

\- One Candidate can submit multiple Job Applications.

\- One Job can receive multiple Job Applications.

\- One Job Application can have multiple Interviews.

\- A User can have an associated Candidate Profile.



\## 🔐 Security



HireHub uses Spring Security with JWT-based authentication.



Protected endpoints require a valid Bearer token in the HTTP Authorization header:



```http

Authorization: Bearer <your-jwt-token>

```



Role-based authorization is configured for different API operations. Access should be granted only to the appropriate roles, such as Candidate, Company, Recruiter, or Admin, according to the endpoint's intended purpose.



\## 🧪 API Testing



The APIs are tested using Postman.



Testing includes:



\- Creating and retrieving records.

\- Updating and deleting records.

\- Testing valid and invalid IDs.

\- Checking duplicate job applications.

\- Verifying JWT authentication.

\- Testing role-based access restrictions.

\- Checking validation and error responses.



\## ⚙️ Getting Started



\### Prerequisites



Install the following tools:



\- Java JDK

\- Maven

\- MySQL Server

\- Git

\- Postman



\### 1. Clone the Repository



```bash

git clone <YOUR\_GITHUB\_REPOSITORY\_URL>

cd HireHub

```



Replace the repository URL with your actual GitHub repository URL.



\### 2. Configure the Database



Create a MySQL database:



```sql

CREATE DATABASE hirehub\_db;

```



Configure the database connection in `application.properties` using your local database settings.



Example:



```properties

spring.datasource.url=jdbc:mysql://localhost:3306/hirehub\_db

spring.datasource.username=${DB\_USERNAME}

spring.datasource.password=${DB\_PASSWORD}

```



Set `DB\_USERNAME` and `DB\_PASSWORD` in your local environment before running the application. Do not commit real credentials to GitHub.



\### 3. Configure JWT



Configure the JWT signing secret securely using a local environment variable or another appropriate secret-management mechanism. Never publish the production JWT secret in the repository.



\### 4. Run the Application



Using Maven:



```bash

mvn spring-boot:run

```



Alternatively, build the project:



```bash

mvn clean package

```



Then run the generated JAR from the `target` directory.



\### 5. Test the APIs



Open Postman and send requests to your local application. The base URL used during local testing is:



```text

http://localhost:8080

```



Use the appropriate HTTP method, request body, and Bearer token for each protected endpoint.



\## 📌 Project Status



\*\*Development Status:\*\* In Progress



Core backend modules have been implemented and several CRUD, job application, interview, and API authentication flows have been tested using Postman. Security restrictions and remaining endpoint tests are still being verified.



\## 🎯 Learning Outcomes



This project demonstrates practical experience with:



\- Java and Spring Boot backend development.

\- RESTful API design and implementation.

\- Spring Security and JWT authentication.

\- Role-based authorization.

\- Entity relationships using JPA and Hibernate.

\- DTOs and request validation.

\- MySQL database integration.

\- Exception handling and API testing.

\- Git and GitHub version control.



\## 👨‍💻 Author



\*\*Anand Kumar\*\*



Java Backend Developer | Spring Boot | REST APIs | MySQL



\---



\*HireHub is a backend development project created to practice and demonstrate real-world job portal workflows and secure REST API development.\*



