const pool = require('../config/db');

// Get payments filtered by month and year
const getMonthlyPaymentHistory = async (req, res) => {
    try {
        const { month, year } = req.query;

        // Default to current month/year if not provided
        const now = new Date();
        const targetMonth = month || (now.getMonth() + 1);
        const targetYear = year || now.getFullYear();

        const query = `
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
      WHERE EXTRACT(MONTH FROM p.payment_date) = $1 
      AND EXTRACT(YEAR FROM p.payment_date) = $2
      ORDER BY p.payment_date DESC
    `;

        const result = await pool.query(query, [targetMonth, targetYear]);
        res.json(result.rows);
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

module.exports = {
    getMonthlyPaymentHistory
};
