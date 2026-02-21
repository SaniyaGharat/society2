package com.society.app.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00c6\u0003J-\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n\u00a8\u0006\u0019"}, d2 = {"Lcom/society/app/data/model/DashboardStats;", "", "totalCollections", "", "outstandingDues", "paymentModeStats", "", "Lcom/society/app/data/model/PaymentModeStats;", "(DDLjava/util/List;)V", "getOutstandingDues", "()D", "getPaymentModeStats", "()Ljava/util/List;", "getTotalCollections", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
public final class DashboardStats {
    @com.google.gson.annotations.SerializedName(value = "totalCollections")
    private final double totalCollections = 0.0;
    @com.google.gson.annotations.SerializedName(value = "outstandingDues")
    private final double outstandingDues = 0.0;
    @com.google.gson.annotations.SerializedName(value = "paymentModeStats")
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.society.app.data.model.PaymentModeStats> paymentModeStats = null;
    
    public DashboardStats(double totalCollections, double outstandingDues, @org.jetbrains.annotations.NotNull()
    java.util.List<com.society.app.data.model.PaymentModeStats> paymentModeStats) {
        super();
    }
    
    public final double getTotalCollections() {
        return 0.0;
    }
    
    public final double getOutstandingDues() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.society.app.data.model.PaymentModeStats> getPaymentModeStats() {
        return null;
    }
    
    public final double component1() {
        return 0.0;
    }
    
    public final double component2() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.society.app.data.model.PaymentModeStats> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.society.app.data.model.DashboardStats copy(double totalCollections, double outstandingDues, @org.jetbrains.annotations.NotNull()
    java.util.List<com.society.app.data.model.PaymentModeStats> paymentModeStats) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}