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
