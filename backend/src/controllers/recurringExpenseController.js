const pool = require('../config/db');

// Get all configured recurring expenses
const getMaintenanceSettings = async (req, res) => {
    try {
        const result = await pool.query('SELECT * FROM maintenance_settings ORDER BY id');
        res.json(result.rows);
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

// Add a new recurring expense configuration
const addMaintenanceSetting = async (req, res) => {
    try {
        const { category, amount, frequency } = req.body;
        const newSetting = await pool.query(
            'INSERT INTO maintenance_settings (category, amount, frequency) VALUES ($1, $2, $3) RETURNING *',
            [category, amount, frequency || 'MONTHLY']
        );
        res.json(newSetting.rows[0]);
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

// Trigger bill generation for a specific month
const generateMonthlyBills = async (req, res) => {
    try {
        const { month, year } = req.body; // e.g., "April", 2024

        // 1. Get all active residents
        const residents = await pool.query("SELECT id FROM users WHERE role = 'RESIDENT'");

        // 2. Get all monthly settings
        const settings = await pool.query("SELECT * FROM maintenance_settings WHERE frequency = 'MONTHLY'");

        if (settings.rows.length === 0) {
            return res.status(400).json({ msg: 'No recurring expenses configured' });
        }

        let totalAmount = 0;
        settings.rows.forEach(setting => {
            totalAmount += parseFloat(setting.amount);
        });

        // 3. Create maintenance record for each resident
        const createdRecords = [];

        // Using a transaction would be better here, but keeping it simple for now
        for (const resident of residents.rows) {
            // Check if record already exists
            const existing = await pool.query(
                'SELECT * FROM maintenance_records WHERE resident_id = $1 AND month = $2 AND year = $3',
                [resident.id, month, year]
            );

            if (existing.rows.length === 0) {
                const newRecord = await pool.query(
                    'INSERT INTO maintenance_records (resident_id, month, year, amount_due, due_date) VALUES ($1, $2, $3, $4, $5) RETURNING *',
                    [resident.id, month, year, totalAmount, `${year}-04-05`] // Hardcoded due date for demo
                );
                createdRecords.push(newRecord.rows[0]);
            }
        }

        res.json({ msg: `Generated bills for ${createdRecords.length} residents`, records: createdRecords });
    } catch (err) {
        console.error(err.message);
        res.status(500).send('Server Error');
    }
};

module.exports = {
    getMaintenanceSettings,
    addMaintenanceSetting,
    generateMonthlyBills
};
