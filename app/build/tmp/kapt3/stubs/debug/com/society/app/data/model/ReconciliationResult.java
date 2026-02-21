package com.society.app.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u00a2\u0006\u0002\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u0005H\u00c6\u0003J3\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001R\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000b\u00a8\u0006\u001a"}, d2 = {"Lcom/society/app/data/model/ReconciliationResult;", "", "stats", "Lcom/society/app/data/model/ReconStats;", "matched", "", "Lcom/society/app/data/model/MatchedTransaction;", "unmatched", "Lcom/society/app/data/model/BankTransaction;", "(Lcom/society/app/data/model/ReconStats;Ljava/util/List;Ljava/util/List;)V", "getMatched", "()Ljava/util/List;", "getStats", "()Lcom/society/app/data/model/ReconStats;", "getUnmatched", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
public final class ReconciliationResult {
    @com.google.gson.annotations.SerializedName(value = "stats")
    @org.jetbrains.annotations.NotNull()
    private final com.society.app.data.model.ReconStats stats = null;
    @com.google.gson.annotations.SerializedName(value = "matched")
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.society.app.data.model.MatchedTransaction> matched = null;
    @com.google.gson.annotations.SerializedName(value = "unmatched")
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.society.app.data.model.BankTransaction> unmatched = null;
    
    public ReconciliationResult(@org.jetbrains.annotations.NotNull()
    com.society.app.data.model.ReconStats stats, @org.jetbrains.annotations.NotNull()
    java.util.List<com.society.app.data.model.MatchedTransaction> matched, @org.jetbrains.annotations.NotNull()
    java.util.List<com.society.app.data.model.BankTransaction> unmatched) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.society.app.data.model.ReconStats getStats() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.society.app.data.model.MatchedTransaction> getMatched() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.society.app.data.model.BankTransaction> getUnmatched() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.society.app.data.model.ReconStats component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.society.app.data.model.MatchedTransaction> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.society.app.data.model.BankTransaction> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.society.app.data.model.ReconciliationResult copy(@org.jetbrains.annotations.NotNull()
    com.society.app.data.model.ReconStats stats, @org.jetbrains.annotations.NotNull()
    java.util.List<com.society.app.data.model.MatchedTransaction> matched, @org.jetbrains.annotations.NotNull()
    java.util.List<com.society.app.data.model.BankTransaction> unmatched) {
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