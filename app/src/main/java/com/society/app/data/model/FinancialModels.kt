package com.society.app.data.model

import com.google.gson.annotations.SerializedName

data class MaintenanceRecord(
    @SerializedName("id") val id: Int,
    @SerializedName("resident_id") val residentId: Int,
    @SerializedName("month") val month: String,
    @SerializedName("year") val year: Int,
    @SerializedName("amount_due") val amountDue: Double,
    @SerializedName("amount_paid") val amountPaid: Double,
    @SerializedName("status") val status: String,
    @SerializedName("due_date") val dueDate: String,
    @SerializedName("created_at") val createdAt: String
)

data class DashboardStats(
    @SerializedName("totalCollections") val totalCollections: Double,
    @SerializedName("outstandingDues") val outstandingDues: Double,
    @SerializedName("paymentModeStats") val paymentModeStats: List<PaymentModeStats>
)

data class PaymentModeStats(
    @SerializedName("payment_mode") val paymentMode: String,
    @SerializedName("count") val count: Int,
    @SerializedName("total") val total: Double
)

data class OutstandingResident(
    @SerializedName("name") val name: String,
    @SerializedName("apartment_number") val apartmentNumber: String,
    @SerializedName("total_outstanding") val totalOutstanding: Double
)

data class Payment(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("resident_id") val residentId: Int,
    @SerializedName("maintenance_record_id") val maintenanceRecordId: Int?,
    @SerializedName("amount") val amount: Double,
    @SerializedName("payment_mode") val paymentMode: String,
    @SerializedName("transaction_id") val transactionId: String?,
    @SerializedName("cheque_number") val chequeNumber: String?,
    @SerializedName("receipt_number") val receiptNumber: String?,
    @SerializedName("notes") val notes: String?,
    @SerializedName("status") val status: String? = null,
    @SerializedName("payment_date") val paymentDate: String? = null
)

data class VerifyPaymentRequest(
    @SerializedName("status") val status: String,
    @SerializedName("verified_by") val verifiedBy: Int
)

data class LedgerEntry(
    @SerializedName("id") val id: Int,
    @SerializedName("date") val date: String,
    @SerializedName("description") val description: String,
    @SerializedName("debit") val debit: Double,
    @SerializedName("credit") val credit: Double,
    @SerializedName("balance") val balance: Double,
    @SerializedName("status") val status: String?
)

data class MaintenanceSetting(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("category") val category: String,
    @SerializedName("amount") val amount: Double,
    @SerializedName("frequency") val frequency: String? = "MONTHLY"
)

data class GenerateBillRequest(
    @SerializedName("month") val month: String,
    @SerializedName("year") val year: Int
)

data class GenerateBillResponse(
    @SerializedName("msg") val msg: String
)

data class BankStatement(
    @SerializedName("bankTransactions") val bankTransactions: List<BankTransaction>
)

data class BankTransaction(
    @SerializedName("date") val date: String,
    @SerializedName("amount") val amount: Double,
    @SerializedName("description") val description: String,
    @SerializedName("ref_number") val refNumber: String
)

data class ReconciliationResult(
    @SerializedName("stats") val stats: ReconStats,
    @SerializedName("matched") val matched: List<MatchedTransaction>,
    @SerializedName("unmatched") val unmatched: List<BankTransaction>
)

data class ReconStats(
    @SerializedName("total") val total: Int,
    @SerializedName("matched") val matched: Int,
    @SerializedName("unmatched") val unmatched: Int
)

data class MatchedTransaction(
    @SerializedName("bankTxn") val bankTxn: BankTransaction,
    @SerializedName("status") val status: String
)

data class PenaltyCalculationResult(
    @SerializedName("msg") val msg: String,
    @SerializedName("totalPenaltyAmount") val totalPenaltyAmount: String,
    @SerializedName("penalizedCount") val penalizedCount: Int
)

data class PenaltyStats(
    @SerializedName("totalPenalties") val totalPenalties: Double,
    @SerializedName("collected") val collected: Double,
    @SerializedName("outstanding") val outstanding: Double,
    @SerializedName("defaulters") val defaulters: List<Defaulter>
)

data class Defaulter(
    @SerializedName("name") val name: String,
    @SerializedName("amount") val amount: Double,
    @SerializedName("days") val days: Int
)

data class Expense(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("category") val category: String,
    @SerializedName("amount") val amount: Double,
    @SerializedName("description") val description: String,
    @SerializedName("expense_date") val expenseDate: String,
    @SerializedName("recorded_by") val recordedBy: Int? = null
)

data class CategoryStat(
    @SerializedName("category") val category: String,
    @SerializedName("total") val total: Double
)

data class MonthlyReport(
    @SerializedName("month") val month: Int,
    @SerializedName("income") val income: Double,
    @SerializedName("expense") val expense: Double,
    @SerializedName("net_savings") val netSavings: Double
)

data class ForecastResult(
    @SerializedName("averageMonthlyIncome") val averageMonthlyIncome: Double,
    @SerializedName("averageMonthlyExpense") val averageMonthlyExpense: Double,
    @SerializedName("forecast") val forecast: List<ForecastItem>
)

data class ForecastItem(
    @SerializedName("month") val month: Int,
    @SerializedName("projectedIncome") val projectedIncome: Double,
    @SerializedName("projectedExpense") val projectedExpense: Double,
    @SerializedName("projectedSavings") val projectedSavings: Double
)

data class ExpenseTrend(
    @SerializedName("category") val category: String,
    @SerializedName("currentMonthTotal") val currentMonthTotal: Double,
    @SerializedName("previousMonthTotal") val previousMonthTotal: Double,
    @SerializedName("changePercentage") val changePercentage: Double,
    @SerializedName("trend") val trend: String
)

data class ResidentProfile(
    @SerializedName("user") val user: UserProfile,
    @SerializedName("stats") val stats: ProfileStats,
    @SerializedName("lastPayment") val lastPayment: LastPayment?
)

data class UserProfile(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("apartment_number") val apartmentNumber: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String
)

data class ProfileStats(
    @SerializedName("totalPaid") val totalPaid: Double,
    @SerializedName("outstanding") val outstanding: Double,
    @SerializedName("pendingBillsCount") val pendingBillsCount: Int
)

data class LastPayment(
    @SerializedName("amount") val amount: Double,
    @SerializedName("payment_date") val paymentDate: String,
    @SerializedName("payment_mode") val paymentMode: String
)

data class ResidentBalance(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("apartment_number") val apartmentNumber: String,
    @SerializedName("total_billed") val totalBilled: Double,
    @SerializedName("total_paid") val totalPaid: Double,
    @SerializedName("balance") val balance: Double
)

data class PaymentHistoryItem(
    @SerializedName("id") val id: Int,
    @SerializedName("amount") val amount: Double,
    @SerializedName("payment_date") val paymentDate: String,
    @SerializedName("payment_mode") val paymentMode: String,
    @SerializedName("status") val status: String,
    @SerializedName("resident_name") val residentName: String,
    @SerializedName("apartment_number") val apartmentNumber: String
)

data class BulkPaymentRequest(
    @SerializedName("payments") val payments: List<BulkPaymentItem>
)

data class BulkPaymentItem(
    @SerializedName("resident_id") val residentId: Int,
    @SerializedName("amount") val amount: Double,
    @SerializedName("mode") val mode: String,
    @SerializedName("note") val note: String
)

data class BulkPaymentResponse(
    @SerializedName("msg") val msg: String,
    @SerializedName("results") val results: List<BulkResult>
)

data class BulkResult(
    @SerializedName("resident_id") val residentId: Int,
    @SerializedName("status") val status: String,
    @SerializedName("payment_id") val paymentId: Int
)

data class AccessCheckResponse(
    @SerializedName("msg") val msg: String
)

data class DefaulterProfile(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("apartment_number") val apartmentNumber: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("balance") val balance: Double
)

data class ReminderRequest(
    @SerializedName("resident_ids") val residentIds: List<Int>
)

data class ReminderResponse(
    @SerializedName("msg") val msg: String
)



data class CreateComplaintRequest(
    @SerializedName("resident_id") val residentId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("category") val category: String
)

data class ConvertExpenseRequest(
    @SerializedName("complaint_id") val complaintId: Int,
    @SerializedName("amount") val amount: Double,
    @SerializedName("description") val description: String,
    @SerializedName("category") val category: String
)
