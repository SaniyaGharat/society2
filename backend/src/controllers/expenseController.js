const pool = require('../config/db');

// Add a new expense
const addExpense = async (req, res) => {
    try {
        const { category, amount, description, expense_date, recorded_by } = req.body;

        const newExpense = await pool.query(
            'INSERT INTO expenses (category, amount, description, expense_date, recorded_by) VALUES ($1, $2, $3, $4, $5) RETURNING *',
            [category, amount, description, expense_date, recorded_by]
        );

        res.json(newExpense.rows[0]);
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

// Get all expenses with optional filtering
const getExpenses = async (req, res) => {
    try {
        const { start_date, end_date, category } = req.query;
        let query = 'SELECT * FROM expenses';
        let params = [];
        let conditions = [];

        if (category) {
            params.push(category);
            conditions.push(`category = $${params.length}`);
        }

        if (start_date && end_date) {
            params.push(start_date);
            params.push(end_date);
            conditions.push(`expense_date >= $${params.length - 1} AND expense_date <= $${params.length}`);
        }

        if (conditions.length > 0) {
            query += ' WHERE ' + conditions.join(' AND ');
        }

        query += ' ORDER BY expense_date DESC';

        const result = await pool.query(query, params);
        res.json(result.rows);
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

// Get expense summary by category
const getExpenseCategoryStats = async (req, res) => {
    try {
        const result = await pool.query(
            'SELECT category, SUM(amount) as total FROM expenses GROUP BY category ORDER BY total DESC'
        );
        res.json(result.rows);
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

module.exports = {
    addExpense,
    getExpenses,
    getExpenseCategoryStats
};
