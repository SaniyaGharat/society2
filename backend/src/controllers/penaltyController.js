const pool = require('../config/db');

// Calculate and apply penalties for overdue records
const calculatePenalties = async (req, res) => {
    try {
        // 1. Get all records past due date that are not fully paid
        const today = new Date().toISOString().split('T')[0];
        const overdueRecords = await pool.query(
            "SELECT * FROM maintenance_records WHERE due_date < $1 AND status != 'PAID'",
            [today]
        );

        let updatedCount = 0;
        let totalPenaltyApplied = 0;

        for (const record of overdueRecords.rows) {
            const dueDate = new Date(record.due_date);
            const now = new Date();
            const diffTime = Math.abs(now - dueDate);
            const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

            let penaltyRate = 0;
            if (diffDays >= 1 && diffDays <= 7) penaltyRate = 0.02;
            else if (diffDays >= 8 && diffDays <= 14) penaltyRate = 0.04;
            else if (diffDays >= 15) penaltyRate = 0.06;

            if (penaltyRate > 0) {
                // Simple logic: Increase amount_due if not already penalized (mock check)
                // In real app, we'd check a separate penalty log to avoid double penalizing.
                // Here we just calculate what the penalty *should* be for demo.

                const penaltyAmount = parseFloat(record.amount_due) * penaltyRate;

                // Demo: We won't actually update the DB indefinitely to avoid compounding on restart
                // But we simulate the update
                /*
                await pool.query(
                  "UPDATE maintenance_records SET amount_due = amount_due + $1 WHERE id = $2",
                  [penaltyAmount, record.id]
                );
                */

                updatedCount++;
                totalPenaltyApplied += penaltyAmount;
            }
        }

        res.json({
            msg: 'Penalty Calculation Complete',
            recordsProcessed: overdueRecords.rows.length,
            penalizedCount: updatedCount,
            totalPenaltyAmount: totalPenaltyApplied.toFixed(2)
        });
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

const getPenaltyStats = async (req, res) => {
    try {
        // Mock stats for demo
        res.json({
            totalPenalties: 42350,
            collected: 18900,
            waived: 6200,
            outstanding: 17250,
            defaulters: [
                { name: 'Raj Kumar', amount: 840, days: 15 },
                { name: 'Vikram Sharma', amount: 650, days: 22 }
            ]
        });
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

module.exports = {
    calculatePenalties,
    getPenaltyStats
};
