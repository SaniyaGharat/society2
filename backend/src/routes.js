const express = require('express');
const router = express.Router();

const maintenanceController = require('./controllers/maintenanceController');
const dashboardController = require('./controllers/dashboardController');
const paymentController = require('./controllers/paymentController');
const ledgerController = require('./controllers/ledgerController');
const authController = require('./controllers/authController');

// Auth Routes
router.post('/auth/login', authController.login);
router.post('/auth/register', authController.register);

// Core: Announcements
const announcementController = require('./controllers/announcementController');
router.get('/announcements', announcementController.getAnnouncements);
router.post('/announcements', announcementController.createAnnouncement);

// Feature 1: Maintenance Records
router.get('/maintenance', maintenanceController.getMaintenanceRecords);
router.post('/maintenance', maintenanceController.createMaintenanceRecord);

// Feature 2: Treasurer Dashboard
router.get('/dashboard/stats', dashboardController.getFinancialStats);
router.get('/dashboard/outstanding', dashboardController.getOutstandingDuesList);

// Feature 3: Payment Tracking
router.post('/payments', paymentController.recordPayment);
router.put('/payments/:id/verify', paymentController.verifyPayment);

// Feature 4: Resident Ledger
router.get('/ledger/:residentId', ledgerController.getLedger);

// Feature 5: Recurring Expense Management
const recurringExpenseController = require('./controllers/recurringExpenseController');
router.get('/maintenance/settings', recurringExpenseController.getMaintenanceSettings);
router.post('/maintenance/settings', recurringExpenseController.addMaintenanceSetting);
router.post('/maintenance/generate', recurringExpenseController.generateMonthlyBills);

// Feature 6: Payment Reconciliation
const reconciliationController = require('./controllers/reconciliationController');
router.post('/reconciliation/process', reconciliationController.reconcilePayments);
router.post('/reconciliation/process', reconciliationController.reconcilePayments);
router.get('/reconciliation/status', reconciliationController.getReconciliationStatus);

// Feature 7: Penalty Management
const penaltyController = require('./controllers/penaltyController');
router.post('/penalties/calculate', penaltyController.calculatePenalties);
router.post('/penalties/calculate', penaltyController.calculatePenalties);
router.get('/penalties/stats', penaltyController.getPenaltyStats);

// Feature 8: Expense Tracking
const expenseController = require('./controllers/expenseController');
router.post('/expenses', expenseController.addExpense);
router.get('/expenses', expenseController.getExpenses);
router.get('/expenses/stats', expenseController.getExpenseCategoryStats);

// Feature 9: Financial Reporting
const reportController = require('./controllers/reportController');
router.get('/reports/monthly', reportController.getMonthlyFinancialReport);

// Feature 10: Financial Forecasting
const analyticsController = require('./controllers/analyticsController');
router.get('/analytics/forecast', analyticsController.getFinancialForecast);

// Feature 11: Expense Comparison & Trends
const trendsController = require('./controllers/trendsController');
router.get('/analytics/trends', trendsController.getExpenseTrends);

// Feature 12: Resident Maintenance Profile & Directory
const residentController = require('./controllers/residentController');
router.get('/residents/:id/profile', residentController.getResidentProfile);
router.get('/residents', residentController.getAllResidents);

// Feature: Visitor Management
const visitorController = require('./controllers/visitorController');
router.get('/visitors', visitorController.getVisitors);
router.post('/visitors', visitorController.preApproveVisitor);

// Feature 13: Maintenance Balance Tracking
const balanceController = require('./controllers/balanceController');
router.get('/balances', balanceController.getAllResidentBalances);

// Feature 14: Month-wise Payment History
const historyController = require('./controllers/historyController');
router.get('/payments/history/monthly', historyController.getMonthlyPaymentHistory);

// Feature 15: Bulk Payment Processing
const bulkController = require('./controllers/bulkController');
// Protect financial routes: Only TREASURER or ADMIN can access
const { checkRole } = require('./middleware/auth');
router.post('/payments/bulk', checkRole(['TREASURER', 'ADMIN']), bulkController.processBulkPayments);

// Feature 16: Access Control Test
// Feature 16: Access Control Test
router.get('/treasurer/access-check', checkRole(['TREASURER', 'ADMIN']), (req, res) => {
    res.json({ msg: 'Access Granted: You have Treasurer privileges.' });
});

// Feature 17: Advanced Filtering & Search
const searchController = require('./controllers/searchController');
router.get('/payments/search', searchController.searchPayments);

// Feature 18: Collection Optimization Tools
const optimizationController = require('./controllers/optimizationController');
router.get('/collection/defaulters', optimizationController.getTopDefaulters);
router.post('/collection/remind', optimizationController.sendReminders);

// Feature 19: Complaint & Maintenance Integration
const integrationController = require('./controllers/integrationController');
router.get('/complaints/maintenance', integrationController.getMaintenanceComplaints);
router.post('/complaints', integrationController.createComplaint);
router.post('/complaints/convert-expense', integrationController.convertToExpense);

module.exports = router;
