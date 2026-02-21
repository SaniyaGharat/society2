const pool = require('../config/db');

// Get balances for all residents
const getAllResidentBalances = async (req, res) => {
    try {
        const query = `
      SELECT 
        u.id, 
        u.name, 
        u.apartment_number,
        COALESCE(SUM(mr.amount_due), 0) as total_billed,
        COALESCE(p.total_paid, 0) as total_paid,
        (COALESCE(SUM(mr.amount_due), 0) - COALESCE(p.total_paid, 0)) as balance
      FROM users u
      LEFT JOIN maintenance_records mr ON u.id = mr.resident_id
      LEFT JOIN (
        SELECT resident_id, SUM(amount) as total_paid 
        FROM payments 
        WHERE status = 'VERIFIED' 
        GROUP BY resident_id
      ) p ON u.id = p.resident_id
      WHERE u.role = 'RESIDENT'
      GROUP BY u.id, u.name, u.apartment_number, p.total_paid
      ORDER BY balance DESC
    `;

        const result = await pool.query(query);
        res.json(result.rows);
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

module.exports = {
    getAllResidentBalances
};
