const pool = require('../config/db');

const getLedger = async (req, res) => {
    try {
        const { residentId } = req.params;

        // Get all debits (charges)
        const debits = await pool.query(
            "SELECT id, created_at as date, 'Maintenance Charge' as description, amount_due as debit, 0 as credit, status FROM maintenance_records WHERE resident_id = $1",
            [residentId]
        );

        // Get all credits (payments)
        const credits = await pool.query(
            "SELECT id, payment_date as date, 'Payment - ' || payment_mode as description, 0 as debit, amount as credit, status FROM payments WHERE resident_id = $1",
            [residentId]
        );

        // Combine and sort by date
        const ledger = [...debits.rows, ...credits.rows].sort((a, b) => new Date(a.date) - new Date(b.date));

        // Calculate running balance
        let balance = 0;
        const ledgerWithBalance = ledger.map(entry => {
            balance += parseFloat(entry.debit) - parseFloat(entry.credit);
            return { ...entry, balance: balance.toFixed(2) };
        });

        res.json(ledgerWithBalance);
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

module.exports = {
    getLedger,
};
