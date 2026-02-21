const { Pool } = require('pg');
require('dotenv').config();

const pool = new Pool({
    user: process.env.DB_USER || 'postgres',
    host: process.env.DB_HOST || 'localhost',
    database: process.env.DB_NAME || 'society_db',
    password: process.env.DB_PASSWORD || 'password',
    port: process.env.DB_PORT || 5432,
});

const seedUsers = async () => {
    try {
        console.log('Seeding users...');

        const users = [
            {
                name: 'Admin User',
                email: 'admin@society.com',
                phone: '9999999999',
                apartment_number: 'A-001',
                role: 'ADMIN',
                password: 'password123'
            },
            {
                name: 'Treasurer User',
                email: 'treasurer@society.com',
                phone: '8888888888',
                apartment_number: 'B-101',
                role: 'TREASURER',
                password: 'password123'
            },
            {
                name: 'Resident User',
                email: 'resident@society.com',
                phone: '7777777777',
                apartment_number: 'C-202',
                role: 'RESIDENT',
                password: 'password123'
            }
        ];

        for (const user of users) {
            // Check if user exists
            const exists = await pool.query('SELECT * FROM users WHERE email = $1', [user.email]);
            if (exists.rows.length === 0) {
                await pool.query(
                    'INSERT INTO users (name, email, phone, apartment_number, role, password_hash) VALUES ($1, $2, $3, $4, $5, $6)',
                    [user.name, user.email, user.phone, user.apartment_number, user.role, user.password]
                );
                console.log(`Created user: ${user.email} (${user.role})`);
            } else {
                console.log(`User already exists: ${user.email}`);
            }
        }

        console.log('Seeding complete.');
        process.exit(0);

    } catch (err) {
        console.error('Error seeding users:', err);
        process.exit(1);
    }
};

seedUsers();
