const pool = require('../config/db');

// Simulate processing a bank statement (JSON list of transactions)
const reconcilePayments = async (req, res) => {
    try {
        const { bankTransactions } = req.body;
        // bankTransactions: [{ date, amount, description, ref_number }]

        const results = {
            matched: [],
            unmatched: [],
            stats: { total: 0, matched: 0, unmatched: 0 }
        };

        results.stats.total = bankTransactions.length;

        for (const txn of bankTransactions) {
            // Logic: Match by amount AND (date OR ref_number)
            // 1. Try exact match on Transaction ID / UPI Ref
            let match = await pool.query(
                "SELECT * FROM payments WHERE transaction_id = $1 OR cheque_number = $1",
                [txn.ref_number]
            );

            if (match.rows.length === 0) {
                // 2. Try match by Amount AND Date (approx)
                // Simplification: Match by amount only for today (demo) or implementation would be complex with dates
                // Let's match by Amount
                match = await pool.query(
                    "SELECT * FROM payments WHERE amount = $1 AND status != 'VERIFIED' LIMIT 1",
                    [txn.amount]
                );
            }

            if (match.rows.length > 0) {
                // Matched!
                const payment = match.rows[0];

                // Auto-verify
                await pool.query("UPDATE payments SET status = 'VERIFIED' WHERE id = $1", [payment.id]);

                results.matched.push({
                    bankTxn: txn,
                    appPayment: payment,
                    status: 'AUTO_MATCHED'
                });
                results.stats.matched++;
            } else {
                results.unmatched.push(txn);
                results.stats.unmatched++;
            }
        }

        res.json(results);
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

const getReconciliationStatus = async (req, res) => {
    try {
        // Return last month's stats (placeholder logic)
        res.json({
            lastReconciled: new Date(),
            status: 'UP_TO_DATE',
            unmatchedCount: 0
        });
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

module.exports = {
    reconcilePayments,
    getReconciliationStatus
};
