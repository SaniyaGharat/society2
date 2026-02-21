package com.society.app.data.api

import com.society.app.data.model.Announcement
import com.society.app.data.model.Complaint
import com.society.app.data.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import com.society.app.data.model.*

interface SocietyApi {

    @POST("auth/login")
    suspend fun login(@Body credentials: Map<String, String>): Response<User>

    @POST("auth/register")
    suspend fun register(@Body user: User): Response<User>

    @GET("complaints")
    suspend fun getComplaints(): Response<List<Complaint>>

    @POST("complaints")
    suspend fun createComplaint(@Body complaint: Complaint): Response<Complaint>

    @GET("announcements")
    suspend fun getAnnouncements(): Response<List<Announcement>>

    // Feature 1: Maintenance Records
    @GET("api/maintenance")
    suspend fun getMaintenanceRecords(@Query("residentId") residentId: String? = null): Response<List<MaintenanceRecord>>

    @POST("api/maintenance")
    suspend fun createMaintenanceRecord(@Body record: MaintenanceRecord): Response<MaintenanceRecord>

    // Feature 2: Treasurer Dashboard
    @GET("api/dashboard/stats")
    suspend fun getFinancialStats(): Response<DashboardStats>

    @GET("api/dashboard/outstanding")
    suspend fun getOutstandingDuesList(): Response<List<OutstandingResident>>

    // Feature 3: Payment Tracking
    @POST("api/payments")
    suspend fun recordPayment(@Body payment: Payment): Response<Payment>

    @PUT("api/payments/{id}/verify")
    suspend fun verifyPayment(@Path("id") id: Int, @Body verifyRequest: VerifyPaymentRequest): Response<Payment>

    // Feature 4: Resident Ledger
    @GET("api/ledger/{residentId}")
    suspend fun getLedger(@Path("residentId") residentId: String): Response<List<LedgerEntry>>

    // Feature 5: Recurring Expense Management
    @GET("api/maintenance/settings")
    suspend fun getMaintenanceSettings(): Response<List<MaintenanceSetting>>

    @POST("api/maintenance/settings")
    suspend fun addMaintenanceSetting(@Body setting: MaintenanceSetting): Response<MaintenanceSetting>

    @POST("api/maintenance/generate")
    suspend fun generateMonthlyBills(@Body request: GenerateBillRequest): Response<GenerateBillResponse>

    // Feature 6: Payment Reconciliation
    @POST("api/reconciliation/process")
    suspend fun processReconciliation(@Body statement: BankStatement): Response<ReconciliationResult>

    // Feature 7: Penalty Management
    @POST("api/penalties/calculate")
    suspend fun calculatePenalties(): Response<PenaltyCalculationResult>

    @GET("api/penalties/stats")
    suspend fun getPenaltyStats(): Response<PenaltyStats>

    // Feature 8: Expense Tracking
    @POST("api/expenses")
    suspend fun addExpense(@Body expense: Expense): Response<Expense>

    @GET("api/expenses")
    suspend fun getExpenses(): Response<List<Expense>>

    @GET("api/expenses/stats")
    suspend fun getExpenseCategoryStats(): Response<List<CategoryStat>>

    // Feature 9: Financial Reporting
    @GET("api/reports/monthly")
    suspend fun getMonthlyFinancialReport(@Query("year") year: Int): Response<List<MonthlyReport>>

    // Feature 10: Financial Forecasting
    @GET("api/analytics/forecast")
    suspend fun getFinancialForecast(): Response<ForecastResult>

    // Feature 11: Expense Trends
    @GET("api/analytics/trends")
    suspend fun getExpenseTrends(): Response<List<ExpenseTrend>>

    // Feature 12: Resident Maintenance Profile
    @GET("api/residents/{id}/profile")
    suspend fun getResidentProfile(@Path("id") id: String): Response<ResidentProfile>

    // Feature 13: Maintenance Balance Tracking
    @GET("api/balances")
    suspend fun getAllResidentBalances(): Response<List<ResidentBalance>>

    // Feature 14: Month-wise Payment History
    @GET("api/payments/history/monthly")
    suspend fun getMonthlyPaymentHistory(
        @Query("month") month: Int,
        @Query("year") year: Int
    ): Response<List<PaymentHistoryItem>>

    // Feature 15: Bulk Payment Processing
    @POST("api/payments/bulk")
    suspend fun processBulkPayments(@Body request: BulkPaymentRequest): Response<BulkPaymentResponse>

    // Feature 16: Access Control
    @GET("treasurer/access-check")
    suspend fun checkTreasurerAccess(@Header("x-user-role") role: String): Response<AccessCheckResponse>

    // Feature 17: Advanced Filtering & Search
    @GET("api/payments/search")
    suspend fun searchPayments(
        @Query("start_date") startDate: String?,
        @Query("end_date") endDate: String?,
        @Query("min_amount") minAmount: Double?,
        @Query("max_amount") maxAmount: Double?,
        @Query("payment_mode") paymentMode: String?
    ): Response<List<PaymentHistoryItem>>

    // Feature 18: Collection Optimization Tools
    @GET("api/collection/defaulters")
    suspend fun getTopDefaulters(): Response<List<DefaulterProfile>>

    @POST("api/collection/remind")
    suspend fun sendReminders(@Body request: ReminderRequest): Response<ReminderResponse>

    // Feature 19: Complaint Integration
    @GET("api/complaints/maintenance")
    suspend fun getMaintenanceComplaints(): Response<List<Complaint>>

    @POST("api/complaints")
    suspend fun createComplaint(@Body complaint: CreateComplaintRequest): Response<Complaint>

    @POST("api/complaints/convert-expense")
    suspend fun convertToExpense(@Body request: ConvertExpenseRequest): Response<ReminderResponse>

    // Feature 20: Core Modules (Visitors & Directory)
    @GET("api/visitors")
    suspend fun getVisitors(): Response<List<Visitor>>

    @POST("api/visitors")
    suspend fun preApproveVisitor(@Body visitor: Visitor): Response<Visitor>

    @GET("api/residents")
    suspend fun getResidents(): Response<List<Resident>>

    @GET("api/amenities")
    suspend fun getAmenities(): Response<List<Amenity>>

    @POST("api/amenities/book")
    suspend fun bookAmenity(@Body bookingRequest: Map<String, String>): Response<Void>
}
