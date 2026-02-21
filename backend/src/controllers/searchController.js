const pool = require('../config/db');

// Advanced search for payments
const searchPayments = async (req, res) => {
    try {
        const { start_date, end_date, min_amount, max_amount, payment_mode } = req.query;

        let query = `
      SELECT 
        p.id,
        p.amount,
        p.payment_date,
        p.payment_mode,
        p.status,
        u.name as resident_name,
        u.apartment_number
      FROM payments p
      JOIN users u ON p.resident_id = u.id
      WHERE 1=1
    `;
        const params = [];

        if (start_date) {
            params.push(start_date);
            query += ` AND p.payment_date >= $${params.length}`;
        }

        if (end_date) {
            params.push(end_date);
            query += ` AND p.payment_date <= $${params.length}`;
        }

        if (min_amount) {
            params.push(min_amount);
            query += ` AND p.amount >= $${params.length}`;
        }

        if (max_amount) {
            params.push(max_amount);
            query += ` AND p.amount <= $${params.length}`;
        }

        if (payment_mode) {
            params.push(payment_mode);
            query += ` AND p.payment_mode = $${params.length}`;
        }

        query += ' ORDER BY p.payment_date DESC';

        const result = await pool.query(query, params);
        res.json(result.rows);
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

module.exports = {
    searchPayments
};
