package com.society.app.data.api;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0092\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0004H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\b\b\u0001\u0010\t\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\rJ\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00032\b\b\u0001\u0010\u0015\u001a\u00020\u0016H\u00a7@\u00a2\u0006\u0002\u0010\u0017J\u001e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00032\b\b\u0001\u0010\u001a\u001a\u00020\u0019H\u00a7@\u00a2\u0006\u0002\u0010\u001bJ\u001e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00032\b\b\u0001\u0010\u001a\u001a\u00020\u001cH\u00a7@\u00a2\u0006\u0002\u0010\u001dJ\u001e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00032\b\b\u0001\u0010 \u001a\u00020\u001fH\u00a7@\u00a2\u0006\u0002\u0010!J\u001e\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u00032\b\b\u0001\u0010\u0015\u001a\u00020$H\u00a7@\u00a2\u0006\u0002\u0010%J\u001a\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0\'0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\rJ\u001a\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0\'0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\rJ\u001a\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\'0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\rJ\u001a\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020-0\'0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\rJ\u001a\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020/0\'0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\rJ\u001a\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\'0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\rJ\u0014\u00101\u001a\b\u0012\u0004\u0012\u0002020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\rJ\u0014\u00103\u001a\b\u0012\u0004\u0012\u0002040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\rJ$\u00105\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002060\'0\u00032\b\b\u0001\u00107\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u001a\u00108\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\'0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\rJ&\u00109\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\'0\u00032\n\b\u0003\u00107\u001a\u0004\u0018\u00010\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u001a\u0010:\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\'0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\rJ$\u0010;\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020<0\'0\u00032\b\b\u0001\u0010=\u001a\u00020>H\u00a7@\u00a2\u0006\u0002\u0010?J.\u0010@\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020A0\'0\u00032\b\b\u0001\u0010B\u001a\u00020>2\b\b\u0001\u0010=\u001a\u00020>H\u00a7@\u00a2\u0006\u0002\u0010CJ\u001a\u0010D\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020E0\'0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\rJ\u0014\u0010F\u001a\b\u0012\u0004\u0012\u00020G0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\rJ\u001e\u0010H\u001a\b\u0012\u0004\u0012\u00020I0\u00032\b\b\u0001\u0010J\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u001a\u0010K\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020L0\'0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\rJ*\u0010M\u001a\b\u0012\u0004\u0012\u00020N0\u00032\u0014\b\u0001\u0010O\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110PH\u00a7@\u00a2\u0006\u0002\u0010QJ\u001e\u0010R\u001a\b\u0012\u0004\u0012\u00020S0\u00032\b\b\u0001\u0010\u0015\u001a\u00020TH\u00a7@\u00a2\u0006\u0002\u0010UJ\u001e\u0010V\u001a\b\u0012\u0004\u0012\u00020W0\u00032\b\b\u0001\u0010X\u001a\u00020YH\u00a7@\u00a2\u0006\u0002\u0010ZJ\u001e\u0010[\u001a\b\u0012\u0004\u0012\u00020\\0\u00032\b\b\u0001\u0010]\u001a\u00020\\H\u00a7@\u00a2\u0006\u0002\u0010^J\u001e\u0010_\u001a\b\u0012\u0004\u0012\u00020N0\u00032\b\b\u0001\u0010`\u001a\u00020NH\u00a7@\u00a2\u0006\u0002\u0010aJV\u0010b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020A0\'0\u00032\n\b\u0001\u0010c\u001a\u0004\u0018\u00010\u00112\n\b\u0001\u0010d\u001a\u0004\u0018\u00010\u00112\n\b\u0001\u0010e\u001a\u0004\u0018\u00010f2\n\b\u0001\u0010g\u001a\u0004\u0018\u00010f2\n\b\u0001\u0010h\u001a\u0004\u0018\u00010\u0011H\u00a7@\u00a2\u0006\u0002\u0010iJ\u001e\u0010j\u001a\b\u0012\u0004\u0012\u00020\u00140\u00032\b\b\u0001\u0010\u0015\u001a\u00020kH\u00a7@\u00a2\u0006\u0002\u0010lJ(\u0010m\u001a\b\u0012\u0004\u0012\u00020\\0\u00032\b\b\u0001\u0010J\u001a\u00020>2\b\b\u0001\u0010n\u001a\u00020oH\u00a7@\u00a2\u0006\u0002\u0010p\u00a8\u0006q"}, d2 = {"Lcom/society/app/data/api/SocietyApi;", "", "addExpense", "Lretrofit2/Response;", "Lcom/society/app/data/model/Expense;", "expense", "(Lcom/society/app/data/model/Expense;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addMaintenanceSetting", "Lcom/society/app/data/model/MaintenanceSetting;", "setting", "(Lcom/society/app/data/model/MaintenanceSetting;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "calculatePenalties", "Lcom/society/app/data/model/PenaltyCalculationResult;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkTreasurerAccess", "Lcom/society/app/data/model/AccessCheckResponse;", "role", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "convertToExpense", "Lcom/society/app/data/model/ReminderResponse;", "request", "Lcom/society/app/data/model/ConvertExpenseRequest;", "(Lcom/society/app/data/model/ConvertExpenseRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createComplaint", "Lcom/society/app/data/model/Complaint;", "complaint", "(Lcom/society/app/data/model/Complaint;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/society/app/data/model/CreateComplaintRequest;", "(Lcom/society/app/data/model/CreateComplaintRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createMaintenanceRecord", "Lcom/society/app/data/model/MaintenanceRecord;", "record", "(Lcom/society/app/data/model/MaintenanceRecord;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateMonthlyBills", "Lcom/society/app/data/model/GenerateBillResponse;", "Lcom/society/app/data/model/GenerateBillRequest;", "(Lcom/society/app/data/model/GenerateBillRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllResidentBalances", "", "Lcom/society/app/data/model/ResidentBalance;", "getAnnouncements", "Lcom/society/app/data/model/Announcement;", "getComplaints", "getExpenseCategoryStats", "Lcom/society/app/data/model/CategoryStat;", "getExpenseTrends", "Lcom/society/app/data/model/ExpenseTrend;", "getExpenses", "getFinancialForecast", "Lcom/society/app/data/model/ForecastResult;", "getFinancialStats", "Lcom/society/app/data/model/DashboardStats;", "getLedger", "Lcom/society/app/data/model/LedgerEntry;", "residentId", "getMaintenanceComplaints", "getMaintenanceRecords", "getMaintenanceSettings", "getMonthlyFinancialReport", "Lcom/society/app/data/model/MonthlyReport;", "year", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMonthlyPaymentHistory", "Lcom/society/app/data/model/PaymentHistoryItem;", "month", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOutstandingDuesList", "Lcom/society/app/data/model/OutstandingResident;", "getPenaltyStats", "Lcom/society/app/data/model/PenaltyStats;", "getResidentProfile", "Lcom/society/app/data/model/ResidentProfile;", "id", "getTopDefaulters", "Lcom/society/app/data/model/DefaulterProfile;", "login", "Lcom/society/app/data/model/User;", "credentials", "", "(Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processBulkPayments", "Lcom/society/app/data/model/BulkPaymentResponse;", "Lcom/society/app/data/model/BulkPaymentRequest;", "(Lcom/society/app/data/model/BulkPaymentRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processReconciliation", "Lcom/society/app/data/model/ReconciliationResult;", "statement", "Lcom/society/app/data/model/BankStatement;", "(Lcom/society/app/data/model/BankStatement;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "recordPayment", "Lcom/society/app/data/model/Payment;", "payment", "(Lcom/society/app/data/model/Payment;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "register", "user", "(Lcom/society/app/data/model/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchPayments", "startDate", "endDate", "minAmount", "", "maxAmount", "paymentMode", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendReminders", "Lcom/society/app/data/model/ReminderRequest;", "(Lcom/society/app/data/model/ReminderRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verifyPayment", "verifyRequest", "Lcom/society/app/data/model/VerifyPaymentRequest;", "(ILcom/society/app/data/model/VerifyPaymentRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface SocietyApi {
    
    @retrofit2.http.POST(value = "auth/login")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object login(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> credentials, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.society.app.data.model.User>> $completion);
    
    @retrofit2.http.POST(value = "auth/register")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object register(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.society.app.data.model.User user, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.society.app.data.model.User>> $completion);
    
    @retrofit2.http.GET(value = "complaints")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getComplaints(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.society.app.data.model.Complaint>>> $completion);
    
    @retrofit2.http.POST(value = "complaints")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createComplaint(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.society.app.data.model.Complaint complaint, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.society.app.data.model.Complaint>> $completion);
    
    @retrofit2.http.GET(value = "announcements")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAnnouncements(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.society.app.data.model.Announcement>>> $completion);
    
    @retrofit2.http.GET(value = "api/maintenance")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMaintenanceRecords(@retrofit2.http.Query(value = "residentId")
    @org.jetbrains.annotations.Nullable()
    java.lang.String residentId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.society.app.data.model.MaintenanceRecord>>> $completion);
    
    @retrofit2.http.POST(value = "api/maintenance")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createMaintenanceRecord(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.society.app.data.model.MaintenanceRecord record, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.society.app.data.model.MaintenanceRecord>> $completion);
    
    @retrofit2.http.GET(value = "api/dashboard/stats")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getFinancialStats(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.society.app.data.model.DashboardStats>> $completion);
    
    @retrofit2.http.GET(value = "api/dashboard/outstanding")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getOutstandingDuesList(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.society.app.data.model.OutstandingResident>>> $completion);
    
    @retrofit2.http.POST(value = "api/payments")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object recordPayment(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.society.app.data.model.Payment payment, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.society.app.data.model.Payment>> $completion);
    
    @retrofit2.http.PUT(value = "api/payments/{id}/verify")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object verifyPayment(@retrofit2.http.Path(value = "id")
    int id, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.society.app.data.model.VerifyPaymentRequest verifyRequest, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.society.app.data.model.Payment>> $completion);
    
    @retrofit2.http.GET(value = "api/ledger/{residentId}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getLedger(@retrofit2.http.Path(value = "residentId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String residentId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.society.app.data.model.LedgerEntry>>> $completion);
    
    @retrofit2.http.GET(value = "api/maintenance/settings")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMaintenanceSettings(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.society.app.data.model.MaintenanceSetting>>> $completion);
    
    @retrofit2.http.POST(value = "api/maintenance/settings")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addMaintenanceSetting(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.society.app.data.model.MaintenanceSetting setting, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.society.app.data.model.MaintenanceSetting>> $completion);
    
    @retrofit2.http.POST(value = "api/maintenance/generate")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object generateMonthlyBills(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.society.app.data.model.GenerateBillRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.society.app.data.model.GenerateBillResponse>> $completion);
    
    @retrofit2.http.POST(value = "api/reconciliation/process")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object processReconciliation(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.society.app.data.model.BankStatement statement, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.society.app.data.model.ReconciliationResult>> $completion);
    
    @retrofit2.http.POST(value = "api/penalties/calculate")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object calculatePenalties(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.society.app.data.model.PenaltyCalculationResult>> $completion);
    
    @retrofit2.http.GET(value = "api/penalties/stats")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPenaltyStats(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.society.app.data.model.PenaltyStats>> $completion);
    
    @retrofit2.http.POST(value = "api/expenses")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addExpense(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.society.app.data.model.Expense expense, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.society.app.data.model.Expense>> $completion);
    
    @retrofit2.http.GET(value = "api/expenses")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getExpenses(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.society.app.data.model.Expense>>> $completion);
    
    @retrofit2.http.GET(value = "api/expenses/stats")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getExpenseCategoryStats(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.society.app.data.model.CategoryStat>>> $completion);
    
    @retrofit2.http.GET(value = "api/reports/monthly")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMonthlyFinancialReport(@retrofit2.http.Query(value = "year")
    int year, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.society.app.data.model.MonthlyReport>>> $completion);
    
    @retrofit2.http.GET(value = "api/analytics/forecast")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getFinancialForecast(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.society.app.data.model.ForecastResult>> $completion);
    
    @retrofit2.http.GET(value = "api/analytics/trends")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getExpenseTrends(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.society.app.data.model.ExpenseTrend>>> $completion);
    
    @retrofit2.http.GET(value = "api/residents/{id}/profile")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getResidentProfile(@retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.society.app.data.model.ResidentProfile>> $completion);
    
    @retrofit2.http.GET(value = "api/balances")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllResidentBalances(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.society.app.data.model.ResidentBalance>>> $completion);
    
    @retrofit2.http.GET(value = "api/payments/history/monthly")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMonthlyPaymentHistory(@retrofit2.http.Query(value = "month")
    int month, @retrofit2.http.Query(value = "year")
    int year, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.society.app.data.model.PaymentHistoryItem>>> $completion);
    
    @retrofit2.http.POST(value = "api/payments/bulk")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object processBulkPayments(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.society.app.data.model.BulkPaymentRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.society.app.data.model.BulkPaymentResponse>> $completion);
    
    @retrofit2.http.GET(value = "treasurer/access-check")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object checkTreasurerAccess(@retrofit2.http.Header(value = "x-user-role")
    @org.jetbrains.annotations.NotNull()
    java.lang.String role, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.society.app.data.model.AccessCheckResponse>> $completion);
    
    @retrofit2.http.GET(value = "api/payments/search")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object searchPayments(@retrofit2.http.Query(value = "start_date")
    @org.jetbrains.annotations.Nullable()
    java.lang.String startDate, @retrofit2.http.Query(value = "end_date")
    @org.jetbrains.annotations.Nullable()
    java.lang.String endDate, @retrofit2.http.Query(value = "min_amount")
    @org.jetbrains.annotations.Nullable()
    java.lang.Double minAmount, @retrofit2.http.Query(value = "max_amount")
    @org.jetbrains.annotations.Nullable()
    java.lang.Double maxAmount, @retrofit2.http.Query(value = "payment_mode")
    @org.jetbrains.annotations.Nullable()
    java.lang.String paymentMode, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.society.app.data.model.PaymentHistoryItem>>> $completion);
    
    @retrofit2.http.GET(value = "api/collection/defaulters")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTopDefaulters(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.society.app.data.model.DefaulterProfile>>> $completion);
    
    @retrofit2.http.POST(value = "api/collection/remind")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sendReminders(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.society.app.data.model.ReminderRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.society.app.data.model.ReminderResponse>> $completion);
    
    @retrofit2.http.GET(value = "api/complaints/maintenance")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMaintenanceComplaints(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.society.app.data.model.Complaint>>> $completion);
    
    @retrofit2.http.POST(value = "api/complaints")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createComplaint(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.society.app.data.model.CreateComplaintRequest complaint, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.society.app.data.model.Complaint>> $completion);
    
    @retrofit2.http.POST(value = "api/complaints/convert-expense")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object convertToExpense(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.society.app.data.model.ConvertExpenseRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.society.app.data.model.ReminderResponse>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}