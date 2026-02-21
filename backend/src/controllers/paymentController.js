const pool = require('../config/db');

const recordPayment = async (req, res) => {
    try {
        const {
            resident_id,
            maintenance_record_id,
            amount,
            payment_mode,
            transaction_id,
            cheque_number,
            receipt_number,
            notes,
        } = req.body;

        const newPayment = await pool.query(
            `INSERT INTO payments 
       (resident_id, maintenance_record_id, amount, payment_mode, transaction_id, cheque_number, receipt_number, notes) 
       VALUES ($1, $2, $3, $4, $5, $6, $7, $8) RETURNING *`,
            [
                resident_id,
                maintenance_record_id,
                amount,
                payment_mode,
                transaction_id,
                cheque_number,
                receipt_number,
                notes,
            ]
        );

        // Update maintenance record amount paid and status
        // Logic to update status based on amount paid vs due would go here (omitted for brevity)

        res.json(newPayment.rows[0]);
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

const verifyPayment = async (req, res) => {
    try {
        const { id } = req.params;
        const { status, verified_by } = req.body;

        const updatedPayment = await pool.query(
            'UPDATE payments SET status = $1, verified_by = $2 WHERE id = $3 RETURNING *',
            [status, verified_by, id]
        );

        res.json(updatedPayment.rows[0]);
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

module.exports = {
    recordPayment,
    verifyPayment,
};
