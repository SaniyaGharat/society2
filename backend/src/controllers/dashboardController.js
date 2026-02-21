const pool = require('../config/db');

const getFinancialStats = async (req, res) => {
    try {
        // Total Collections
        const totalCollectionsResult = await pool.query(
            "SELECT SUM(amount) as total FROM payments WHERE status = 'VERIFIED'"
        );
        const totalCollections = totalCollectionsResult.rows[0].total || 0;

        // Outstanding Dues
        const outstandingDuesResult = await pool.query(
            "SELECT SUM(amount_due - amount_paid) as outstanding FROM maintenance_records WHERE status != 'PAID'"
        );
        const outstandingDues = outstandingDuesResult.rows[0].outstanding || 0;

        // Payment Mode Stats
        const paymentModeStats = await pool.query(
            "SELECT payment_mode, COUNT(*) as count, SUM(amount) as total FROM payments WHERE status = 'VERIFIED' GROUP BY payment_mode"
        );

        res.json({
            totalCollections,
            outstandingDues,
            paymentModeStats: paymentModeStats.rows,
        });
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

const getOutstandingDuesList = async (req, res) => {
    try {
        const result = await pool.query(
            `SELECT u.name, u.apartment_number, SUM(mr.amount_due - mr.amount_paid) as total_outstanding
       FROM maintenance_records mr
       JOIN users u ON mr.resident_id = u.id
       WHERE mr.status != 'PAID'
       GROUP BY u.id, u.name, u.apartment_number
       ORDER BY total_outstanding DESC`
        );
        res.json(result.rows);
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

module.exports = {
    getFinancialStats,
    getOutstandingDuesList,
};
