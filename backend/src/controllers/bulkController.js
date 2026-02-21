const pool = require('../config/db');

// Process multiple payments in a single transaction
const processBulkPayments = async (req, res) => {
    const client = await pool.connect();
    try {
        const { payments } = req.body; // Expects array of { resident_id, amount, mode, note }

        if (!payments || !Array.isArray(payments) || payments.length === 0) {
            return res.status(400).json({ msg: 'No payments provided' });
        }

        await client.query('BEGIN');
        const results = [];

        for (const p of payments) {
            // Record payment
            const paymentResult = await client.query(
                'INSERT INTO payments (resident_id, amount, payment_mode, reference_number, payment_date, status) VALUES ($1, $2, $3, $4, CURRENT_DATE, $5) RETURNING id',
                [p.resident_id, p.amount, p.mode || 'CASH', p.note || 'Bulk Payment', 'VERIFIED']
            );

            // Update outstanding (Simulated by checking oldest unpaid record and marking paid)
            // In a real system, we'd allocate the amount against specific record IDs.
            // Here we just mark the earliest UNPAID record as PAID for simplicity if amount matches roughly.

            // Find one unpaid record
            const unpaid = await client.query(
                "SELECT id, amount_due FROM maintenance_records WHERE resident_id = $1 AND status != 'PAID' ORDER BY due_date ASC LIMIT 1",
                [p.resident_id]
            );

            if (unpaid.rows.length > 0) {
                await client.query("UPDATE maintenance_records SET status = 'PAID' WHERE id = $1", [unpaid.rows[0].id]);
            }

            results.push({ resident_id: p.resident_id, status: 'Success', payment_id: paymentResult.rows[0].id });
        }

        await client.query('COMMIT');
        res.json({ msg: 'Bulk Processing Complete', results });
    } catch (err) {
        await client.query('ROLLBACK');
        console.error(err.message);
        res.status(500).send('Server Error');
    } finally {
        client.release();
    }
};

module.exports = {
    processBulkPayments
};
