const pool = require('../config/db');

exports.getVisitors = async (req, res) => {
    try {
        const result = await pool.query('SELECT * FROM visitors ORDER BY created_at DESC');
        res.json(result.rows);
    } catch (err) {
        console.error(err);
        res.status(500).json({ msg: 'Server error' });
    }
};

exports.preApproveVisitor = async (req, res) => {
    const { name, type, expected_date, resident_id } = req.body;
    try {
        const result = await pool.query(
            'INSERT INTO visitors (name, type, expected_date, resident_id, status) VALUES ($1, $2, $3, $4, $5) RETURNING *',
            [name, type, expected_date, resident_id, 'EXPECTED']
        );
        res.json(result.rows[0]);
    } catch (err) {
        console.error(err);
        res.status(500).json({ msg: 'Server error' });
    }
};
