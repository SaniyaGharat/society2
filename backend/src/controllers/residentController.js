const pool = require('../config/db');

// Get comprehensive maintenance profile for a resident
const getResidentProfile = async (req, res) => {
    try {
        const { id } = req.params;

        // 1. Get User Details
        const userResult = await pool.query('SELECT id, name, email, phone, apartment_number, role FROM users WHERE id = $1', [id]);
        if (userResult.rows.length === 0) {
            return res.status(404).json({ msg: 'User not found' });
        }
        const user = userResult.rows[0];

        // 2. Calculate Financial Stats
        const statsResult = await pool.query(`
      SELECT 
        COALESCE(SUM(amount_due), 0) as total_billed,
        COALESCE(SUM(amount_paid), 0) as total_paid
      FROM maintenance_records 
      WHERE resident_id = $1
    `, [id]);

        const totalBilled = parseFloat(statsResult.rows[0].total_billed);
        const totalPaid = parseFloat(statsResult.rows[0].total_paid);
        const outstanding = totalBilled - totalPaid;

        // 3. Get Last Payment
        const lastPaymentResult = await pool.query(`
      SELECT amount, payment_date, payment_mode 
      FROM payments 
      WHERE resident_id = $1 AND status = 'VERIFIED'
      ORDER BY payment_date DESC 
      LIMIT 1
    `, [id]);
        const lastPayment = lastPaymentResult.rows[0] || null;

        // 4. Get Pending Bills Count
        const pendingResult = await pool.query(`
        SELECT COUNT(*) as count FROM maintenance_records WHERE resident_id = $1 AND status != 'PAID'
    `, [id]);
        const pendingBillsCount = parseInt(pendingResult.rows[0].count);

        res.json({
            user,
            stats: {
                totalPaid,
                outstanding,
                pendingBillsCount
            },
            lastPayment
        });
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

// Get all residents for directory
const getAllResidents = async (req, res) => {
    try {
        const result = await pool.query('SELECT id, name, apartment_number, phone, email, role FROM users WHERE role = \'RESIDENT\' ORDER BY apartment_number');
        res.json(result.rows);
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

module.exports = {
    getResidentProfile,
    getAllResidents
};
