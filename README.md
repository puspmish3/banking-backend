# Banking Application Backend

## Project Overview

This is a comprehensive Spring Boot backend API for a banking application with PostgreSQL database integration. It provides secure authentication, account management, and transaction handling capabilities.

## Technology Stack

- **Spring Boot 3.2.1** - Main framework
- **Spring Security** - Authentication and authorization
- **JWT (JJWT 0.12.3)** - Token-based authentication
- **Spring Data JPA** - Database ORM
- **PostgreSQL** - Primary database
- **Maven** - Build tool
- **Java 17** - Programming language

## API Endpoints

### Authentication Endpoints

- `POST /api/auth/signin` - User login
- `POST /api/auth/signup` - User registration
- `GET /api/auth/me` - Get current user info

### Account Management Endpoints

- `GET /api/accounts` - Get user's accounts
- `GET /api/accounts/{accountId}` - Get specific account
- `POST /api/accounts` - Create new account
- `GET /api/accounts/{accountId}/transactions` - Get account transactions
- `GET /api/accounts/transactions` - Get all user transactions

## Database Schema

### Tables Created

1. **users** - User information and authentication
2. **accounts** - Bank accounts linked to users
3. **transactions** - Financial transactions

### Key Features

- **JWT Authentication** - Secure token-based auth
- **Role-based Security** - User authorization
- **Database Relationships** - Proper entity mapping
- **Transaction Management** - ACID compliance
- **Input Validation** - Request validation
- **Error Handling** - Comprehensive exception handling
- **CORS Support** - Cross-origin requests enabled

## Configuration Files

- `application.properties` - Database and JWT configuration
- `pom.xml` - Maven dependencies and build configuration

## Entity Relationships

- User → Account (One to Many)
- Account → Transaction (One to Many)
- Account → Account (For transfers - Many to Many)

## Security Implementation

- Password encryption using BCrypt
- JWT token generation and validation
- Protected endpoints with Spring Security
- User authentication filter chain

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- PostgreSQL 12 or higher
- Git (for version control)

## Setup Instructions

### 1. Clone the Repository
```bash
git clone <repository-url>
cd banking-backend
```

### 2. Database Setup
- Install PostgreSQL and create a database named `banking_app`
- Run the SQL script provided in `database-setup.sql` to create tables and sample data
- Update `src/main/resources/application.properties` with your database credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/banking_app
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Build and Run
```bash
# Install dependencies and compile
mvn clean install

# Run the application
mvn spring-boot:run
```

### 4. Test the API
- API will be available at `http://localhost:8080`
- Test endpoints using Postman or curl
- Default test credentials: `demo@securebank.com` / `password`

## Environment Configuration

Create different property files for different environments:
- `application-dev.properties` - Development environment
- `application-prod.properties` - Production environment

## API Testing

### Sample Login Request
```bash
curl -X POST http://localhost:8080/api/auth/signin \
  -H "Content-Type: application/json" \
  -d '{"email":"demo@securebank.com","password":"password"}'
```

### Sample Account Request (with JWT token)
```bash
curl -X GET http://localhost:8080/api/accounts \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## Git Workflow

This project uses Git for version control. The repository includes:
- Complete Spring Boot backend source code
- Database schema and setup scripts
- Maven configuration and dependencies
- Comprehensive .gitignore for Java/Spring Boot projects

## How to Run

1. Set up PostgreSQL database
2. Update `application.properties` with database credentials
3. Run `mvn spring-boot:run`
4. API will be available at `http://localhost:8080`

## Frontend Integration

This backend is designed to work with:

- React Native mobile app (BankingMobile)
- Next.js web app (banking-web)

Both frontend applications are configured to consume these APIs for authentication and account management.

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request
