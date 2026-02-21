const pool = require('../config/db');

// Predict future stats based on simple averages
const getFinancialForecast = async (req, res) => {
    try {
        // 1. Calculate Average Monthly Income from last 6 months
        const incomeResult = await pool.query(`
      SELECT AVG(monthly_total) as avg_income FROM (
        SELECT SUM(amount) as monthly_total
        FROM payments
        WHERE status = 'VERIFIED'
        GROUP BY EXTRACT(MONTH FROM payment_date)
      ) as sub
    `);
        const avgIncome = parseFloat(incomeResult.rows[0].avg_income) || 0;

        // 2. Calculate Average Monthly Expense
        const expenseResult = await pool.query(`
      SELECT AVG(monthly_total) as avg_expense FROM (
        SELECT SUM(amount) as monthly_total
        FROM expenses
        GROUP BY EXTRACT(MONTH FROM expense_date)
      ) as sub
    `);
        const avgExpense = parseFloat(expenseResult.rows[0].avg_expense) || 0;

        // 3. Forecast for next 3 months
        const forecast = [];
        const currentMonth = new Date().getMonth() + 1; // 1-12

        for (let i = 1; i <= 3; i++) {
            // Add random variation (simulated volatility)
            const volatility = 0.05; // 5%
            const incomeProjected = avgIncome * (1 + (Math.random() * volatility * 2 - volatility));
            const expenseProjected = avgExpense * (1 + (Math.random() * volatility * 2 - volatility));

            forecast.push({
                month: (currentMonth + i) % 12 || 12, // Handle December wrap
                projectedIncome: incomeProjected.toFixed(2),
                projectedExpense: expenseProjected.toFixed(2),
                projectedSavings: (incomeProjected - expenseProjected).toFixed(2)
            });
        }

        res.json({
            averageMonthlyIncome: avgIncome.toFixed(2),
            averageMonthlyExpense: avgExpense.toFixed(2),
            forecast: forecast
        });
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

module.exports = {
    getFinancialForecast
};
