const pool = require('../config/db');

exports.getAnnouncements = async (req, res) => {
    try {
        const result = await pool.query('SELECT * FROM announcements ORDER BY date DESC');
        res.json(result.rows);
    } catch (err) {
        console.error(err);
        res.status(500).json({ msg: 'Server error' });
    }
};

exports.createAnnouncement = async (req, res) => {
    const { title, description, type, posted_by } = req.body;
    try {
        const result = await pool.query(
            'INSERT INTO announcements (title, description, type, posted_by) VALUES ($1, $2, $3, $4) RETURNING *',
            [title, description, type, posted_by]
        );
        res.json(result.rows[0]);
    } catch (err) {
        console.error(err);
        res.status(500).json({ msg: 'Server error' });
    }
};
