const pool = require('../config/db');

// Compare current month vs previous month expenses by category
const getExpenseTrends = async (req, res) => {
    try {
        const currentMonth = new Date().getMonth() + 1;
        const previousMonth = currentMonth === 1 ? 12 : currentMonth - 1;
        const currentYear = new Date().getFullYear();
        const previousYear = currentMonth === 1 ? currentYear - 1 : currentYear;

        // 1. Get Current Month Stats
        const currentStats = await pool.query(`
      SELECT category, SUM(amount) as total
      FROM expenses
      WHERE EXTRACT(MONTH FROM expense_date) = $1 AND EXTRACT(YEAR FROM expense_date) = $2
      GROUP BY category
    `, [currentMonth, currentYear]);

        // 2. Get Previous Month Stats
        const previousStats = await pool.query(`
      SELECT category, SUM(amount) as total
      FROM expenses
      WHERE EXTRACT(MONTH FROM expense_date) = $1 AND EXTRACT(YEAR FROM expense_date) = $2
      GROUP BY category
    `, [previousMonth, previousYear]);

        // 3. Compare
        const trends = [];

        // Create a set of all categories
        const categories = new Set([
            ...currentStats.rows.map(r => r.category),
            ...previousStats.rows.map(r => r.category)
        ]);

        categories.forEach(category => {
            const curr = currentStats.rows.find(r => r.category === category);
            const prev = previousStats.rows.find(r => r.category === category);

            const currTotal = curr ? parseFloat(curr.total) : 0;
            const prevTotal = prev ? parseFloat(prev.total) : 0;

            let changePercent = 0;
            if (prevTotal > 0) {
                changePercent = ((currTotal - prevTotal) / prevTotal) * 100;
            } else if (currTotal > 0) {
                changePercent = 100; // New expense
            }

            trends.push({
                category,
                currentMonthTotal: currTotal,
                previousMonthTotal: prevTotal,
                changePercentage: changePercent.toFixed(1),
                trend: changePercent > 0 ? 'UP' : (changePercent < 0 ? 'DOWN' : 'STABLE')
            });
        });

        res.json(trends);
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

module.exports = {
    getExpenseTrends
};
