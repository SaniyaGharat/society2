const { Pool } = require('pg');
require('dotenv').config();

const pool = new Pool({
    user: process.env.DB_USER || 'postgres',
    host: process.env.DB_HOST || 'localhost',
    database: process.env.DB_NAME || 'society_db',
    password: process.env.DB_PASSWORD || 'password',
    port: process.env.DB_PORT || 5432,
});

exports.login = async (req, res) => {
    const { email, password } = req.body;
    try {
        const result = await pool.query('SELECT * FROM users WHERE email = $1', [email]);
        if (result.rows.length === 0) {
            return res.status(401).json({ msg: 'Invalid credentials' });
        }

        const user = result.rows[0];
        // Simple password check (In production, use bcrypt)
        if (user.password_hash !== password) {
            return res.status(401).json({ msg: 'Invalid credentials' });
        }

        // Return user info (excluding password)
        const { password_hash, ...userInfo } = user;
        res.json(userInfo);
    } catch (err) {
        console.error(err);
        res.status(500).json({ msg: 'Server error' });
    }
};

exports.register = async (req, res) => {
    const { name, email, phone, apartment_number, role, password } = req.body;
    try {
        // Check if user exists
        const userExists = await pool.query('SELECT * FROM users WHERE email = $1', [email]);
        if (userExists.rows.length > 0) {
            return res.status(400).json({ msg: 'User already exists' });
        }

        // Insert new user
        const result = await pool.query(
            'INSERT INTO users (name, email, phone, apartment_number, role, password_hash) VALUES ($1, $2, $3, $4, $5, $6) RETURNING *',
            [name, email, phone, apartment_number, role, password]
        );

        const newUser = result.rows[0];
        const { password_hash, ...userInfo } = newUser;
        res.json(userInfo);
    } catch (err) {
        console.error(err);
        res.status(500).json({ msg: 'Server error' });
    }
};
