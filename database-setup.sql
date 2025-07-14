-- PostgreSQL Database Setup for Banking Application
-- Run these commands in your PostgreSQL database

-- Create database
CREATE DATABASE banking_app;

-- Connect to the database
\c banking_app;

-- Create tables (Spring Boot JPA will create these automatically, but here's the reference)

-- Users table
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(15),
    address TEXT,
    date_of_birth TIMESTAMP,
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
    is_verified BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Accounts table
CREATE TABLE IF NOT EXISTS accounts (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id),
    account_number VARCHAR(20) UNIQUE NOT NULL,
    account_type VARCHAR(50) NOT NULL,
    balance DECIMAL(15,2) NOT NULL DEFAULT 0.00,
    currency VARCHAR(3) NOT NULL DEFAULT 'USD',
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Transactions table
CREATE TABLE IF NOT EXISTS transactions (
    id BIGSERIAL PRIMARY KEY,
    transaction_id VARCHAR(100) UNIQUE NOT NULL,
    account_id BIGINT NOT NULL REFERENCES accounts(id),
    to_account_id BIGINT REFERENCES accounts(id),
    amount DECIMAL(15,2) NOT NULL,
    transaction_type VARCHAR(50) NOT NULL,
    description VARCHAR(255) NOT NULL,
    category VARCHAR(100),
    balance_after DECIMAL(15,2) NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'COMPLETED',
    reference_number VARCHAR(100),
    merchant_name VARCHAR(100),
    location VARCHAR(100),
    transaction_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    processed_date TIMESTAMP
);

-- Insert sample data for testing

-- Sample user
INSERT INTO users (full_name, email, password, phone_number, status, is_verified) 
VALUES 
('John Doe', 'demo@securebank.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', '+1234567890', 'ACTIVE', TRUE);
-- Password: password

-- Sample accounts
INSERT INTO accounts (user_id, account_number, account_type, balance, status) 
VALUES 
((SELECT id FROM users WHERE email = 'demo@securebank.com'), 'ACC1234567', 'CHECKING', 5000.00, 'ACTIVE'),
((SELECT id FROM users WHERE email = 'demo@securebank.com'), 'ACC1234568', 'SAVINGS', 10000.00, 'ACTIVE');

-- Sample transactions
INSERT INTO transactions (transaction_id, account_id, amount, transaction_type, description, balance_after, status)
VALUES 
('TXN001', (SELECT id FROM accounts WHERE account_number = 'ACC1234567'), 1000.00, 'DEPOSIT', 'Initial deposit', 1000.00, 'COMPLETED'),
('TXN002', (SELECT id FROM accounts WHERE account_number = 'ACC1234567'), -50.00, 'WITHDRAWAL', 'ATM withdrawal', 950.00, 'COMPLETED'),
('TXN003', (SELECT id FROM accounts WHERE account_number = 'ACC1234568'), 5000.00, 'DEPOSIT', 'Salary deposit', 5000.00, 'COMPLETED');

-- Create indexes for better performance
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_accounts_user_id ON accounts(user_id);
CREATE INDEX idx_accounts_account_number ON accounts(account_number);
CREATE INDEX idx_transactions_account_id ON transactions(account_id);
CREATE INDEX idx_transactions_transaction_date ON transactions(transaction_date);
CREATE INDEX idx_transactions_transaction_id ON transactions(transaction_id);
