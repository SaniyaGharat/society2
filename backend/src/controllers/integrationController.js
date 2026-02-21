const pool = require('../config/db');

// Ensure tables exist (Lazy Init for demo)
const initTables = async () => {
    try {
        await pool.query(`
            CREATE TABLE IF NOT EXISTS complaints (
                id SERIAL PRIMARY KEY,
                resident_id INTEGER REFERENCES users(id),
                title VARCHAR(200) NOT NULL,
                description TEXT,
                category VARCHAR(50) DEFAULT 'MAINTENANCE',
                status VARCHAR(20) DEFAULT 'OPEN',
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            );
            ALTER TABLE expenses ADD COLUMN IF NOT EXISTS complaint_id INTEGER REFERENCES complaints(id);
        `);
    } catch (e) {
        console.log("Table init error (harmless if exists):", e.message);
    }
};

initTables();

// Get all maintenance complaints
const getMaintenanceComplaints = async (req, res) => {
    try {
        const result = await pool.query(`
      SELECT c.*, u.name as resident_name, u.apartment_number
      FROM complaints c
      JOIN users u ON c.resident_id = u.id
      WHERE c.category IN ('MAINTENANCE', 'ELECTRICAL', 'PLUMBING', 'OTHER')
      ORDER BY c.created_at DESC
    `);
        res.json(result.rows);
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

// Create a new complaint
const createComplaint = async (req, res) => {
    try {
        const { resident_id, title, description, category } = req.body;
        const result = await pool.query(
            "INSERT INTO complaints (resident_id, title, description, category, status) VALUES ($1, $2, $3, $4, 'OPEN') RETURNING *",
            [resident_id, title, description, category]
        );
        res.json(result.rows[0]);
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

// Convert complaint to expense
const convertToExpense = async (req, res) => {
    const client = await pool.connect();
    try {
        const { complaint_id, amount, description, category } = req.body;

        await client.query('BEGIN');

        // 1. Create Expense
        await client.query(
            "INSERT INTO expenses (category, amount, description, expense_date, complaint_id) VALUES ($1, $2, $3, CURRENT_DATE, $4)",
            [category, amount, description, complaint_id]
        );

        // 2. Update Complaint Status
        await client.query(
            "UPDATE complaints SET status = 'CONVERTED_TO_EXPENSE' WHERE id = $1",
            [complaint_id]
        );

        await client.query('COMMIT');
        res.json({ msg: 'Complaint converted to expense record successfully' });
    } catch (err) {
        await client.query('ROLLBACK');
        console.error(err.message);
        res.status(500).send('Server Error');
    } finally {
        client.release();
    }
};

module.exports = {
    getMaintenanceComplaints,
    createComplaint,
    convertToExpense
};
