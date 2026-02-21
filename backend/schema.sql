-- Users Table
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20) UNIQUE NOT NULL,
    apartment_number VARCHAR(20),
    role VARCHAR(20) NOT NULL CHECK (role IN ('ADMIN', 'RESIDENT', 'TREASURER', 'SECURITY')),
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Maintenance Settings (for Recurring Expenses)
CREATE TABLE IF NOT EXISTS maintenance_settings (
    id SERIAL PRIMARY KEY,
    category VARCHAR(50) NOT NULL, -- Maintenance, Water, Parking
    amount DECIMAL(10, 2) NOT NULL,
    frequency VARCHAR(20) DEFAULT 'MONTHLY',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Maintenance Records (Generated Monthly)
CREATE TABLE IF NOT EXISTS maintenance_records (
    id SERIAL PRIMARY KEY,
    resident_id INTEGER REFERENCES users(id),
    month VARCHAR(20) NOT NULL, -- e.g., "March 2024"
    year INTEGER NOT NULL,
    amount_due DECIMAL(10, 2) NOT NULL,
    amount_paid DECIMAL(10, 2) DEFAULT 0,
    status VARCHAR(20) DEFAULT 'PENDING' CHECK (status IN ('PENDING', 'PARTIAL', 'PAID', 'OVERDUE')),
    due_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Payments Table (with Mode Tracking)
CREATE TABLE IF NOT EXISTS payments (
    id SERIAL PRIMARY KEY,
    resident_id INTEGER REFERENCES users(id),
    maintenance_record_id INTEGER REFERENCES maintenance_records(id),
    amount DECIMAL(10, 2) NOT NULL,
    payment_mode VARCHAR(20) NOT NULL CHECK (payment_mode IN ('CASH', 'UPI', 'CHEQUE', 'CARD', 'BANK_TRANSFER')),
    transaction_id VARCHAR(100), -- UPI/Card Ref
    cheque_number VARCHAR(50),
    receipt_number VARCHAR(50) UNIQUE,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'VERIFIED' CHECK (status IN ('VERIFIED', 'PENDING', 'FAILED', 'BOUNCED')),
    notes TEXT,
    verified_by INTEGER REFERENCES users(id) -- Treasurer ID
);

-- Complaints Table (NEW)
CREATE TABLE IF NOT EXISTS complaints (
    id SERIAL PRIMARY KEY,
    resident_id INTEGER REFERENCES users(id),
    title VARCHAR(200) NOT NULL,
    description TEXT,
    category VARCHAR(50) DEFAULT 'MAINTENANCE', -- Electrical, Plumbing, etc.
    status VARCHAR(20) DEFAULT 'OPEN' CHECK (status IN ('OPEN', 'IN_PROGRESS', 'RESOLVED', 'CONVERTED_TO_EXPENSE')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Expenses Table (Society Expenses)
CREATE TABLE IF NOT EXISTS expenses (
    id SERIAL PRIMARY KEY,
    category VARCHAR(50) NOT NULL, -- Maintenance, Salaries, Utilities
    amount DECIMAL(10, 2) NOT NULL,
    description TEXT,
    expense_date DATE NOT NULL,
    recorded_by INTEGER REFERENCES users(id),
    complaint_id INTEGER REFERENCES complaints(id), -- Linked Complaint
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Visitors Table
CREATE TABLE IF NOT EXISTS visitors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(20) NOT NULL CHECK (type IN ('GUEST', 'DELIVERY', 'SERVICE')),
    status VARCHAR(20) DEFAULT 'EXPECTED' CHECK (status IN ('EXPECTED', 'CHECKED_IN', 'CHECKED_OUT')),
    expected_date DATE,
    resident_id INTEGER REFERENCES users(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Announcements Table
CREATE TABLE IF NOT EXISTS announcements (
    id SERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    type VARCHAR(50) DEFAULT 'GENERAL',
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    posted_by INTEGER REFERENCES users(id)
);
