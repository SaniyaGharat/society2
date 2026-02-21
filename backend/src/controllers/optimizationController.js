const pool = require('../config/db');

// Get top defaulters (highest outstanding balance)
const getTopDefaulters = async (req, res) => {
    try {
        const query = `
      SELECT 
        u.id, 
        u.name, 
        u.apartment_number, 
        u.phone,
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
      GROUP BY u.id, u.name, u.apartment_number, u.phone, p.total_paid
      HAVING (COALESCE(SUM(mr.amount_due), 0) - COALESCE(p.total_paid, 0)) > 0
      ORDER BY balance DESC
      LIMIT 10
    `;

        const result = await pool.query(query);
        res.json(result.rows);
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

// Simulate sending reminders to listed residents
const sendReminders = async (req, res) => {
    try {
        const { resident_ids } = req.body; // Array of IDs

        // In a real app, this would integrate with SMS/Email API
        console.log(`Sending reminders to residents: ${resident_ids.join(', ')}`);

        res.json({ msg: `Reminders sent successfully to ${resident_ids.length} residents.` });
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

module.exports = {
    getTopDefaulters,
    sendReminders
};
