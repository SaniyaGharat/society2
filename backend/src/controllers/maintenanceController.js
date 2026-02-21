const pool = require('../config/db');

const getMaintenanceRecords = async (req, res) => {
    try {
        const { residentId } = req.query;
        let query = 'SELECT * FROM maintenance_records';
        let params = [];

        if (residentId) {
            query += ' WHERE resident_id = $1';
            params.push(residentId);
        }

        query += ' ORDER BY year DESC, month DESC';

        const result = await pool.query(query, params);
        res.json(result.rows);
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

const createMaintenanceRecord = async (req, res) => {
    try {
        const { resident_id, month, year, amount_due, due_date } = req.body;
        const newRecord = await pool.query(
            'INSERT INTO maintenance_records (resident_id, month, year, amount_due, due_date) VALUES ($1, $2, $3, $4, $5) RETURNING *',
            [resident_id, month, year, amount_due, due_date]
        );
        res.json(newRecord.rows[0]);
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

module.exports = {
    getMaintenanceRecords,
    createMaintenanceRecord,
};
