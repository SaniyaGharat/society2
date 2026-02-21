const pool = require('../config/db');

// Get generic monthly report (Income vs Expense)
const getMonthlyFinancialReport = async (req, res) => {
    try {
        const { year } = req.query; // e.g., 2024

        // 1. Total Income (Collections) per month
        // Simplified: Assuming payment_date has correct year
        const incomeQuery = `
      SELECT EXTRACT(MONTH FROM payment_date) as month, SUM(amount) as total_income
      FROM payments
      WHERE EXTRACT(YEAR FROM payment_date) = $1 AND status = 'VERIFIED'
      GROUP BY month
    `;

        // 2. Total Expenses per month
        const expenseQuery = `
      SELECT EXTRACT(MONTH FROM expense_date) as month, SUM(amount) as total_expense
      FROM expenses
      WHERE EXTRACT(YEAR FROM expense_date) = $1
      GROUP BY month
    `;

        const incomeResult = await pool.query(incomeQuery, [year || new Date().getFullYear()]);
        const expenseResult = await pool.query(expenseQuery, [year || new Date().getFullYear()]);

        // Merge results
        const report = [];
        for (let m = 1; m <= 12; m++) {
            const inc = incomeResult.rows.find(r => parseInt(r.month) === m);
            const exp = expenseResult.rows.find(r => parseInt(r.month) === m);

            report.push({
                month: m,
                income: inc ? parseFloat(inc.total_income) : 0,
                expense: exp ? parseFloat(exp.total_expense) : 0,
                net_savings: (inc ? parseFloat(inc.total_income) : 0) - (exp ? parseFloat(exp.total_expense) : 0)
            });
        }

        res.json(report);
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

module.exports = {
    getMonthlyFinancialReport
};
