package com.society.app.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J$\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\u0007H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\t\u0010\nJ\"\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\f0\u0006H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\r\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u000f"}, d2 = {"Lcom/society/app/data/repository/ComplaintRepository;", "", "api", "Lcom/society/app/data/api/SocietyApi;", "(Lcom/society/app/data/api/SocietyApi;)V", "addComplaint", "Lkotlin/Result;", "Lcom/society/app/data/model/Complaint;", "complaint", "addComplaint-gIAlu-s", "(Lcom/society/app/data/model/Complaint;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getComplaints", "", "getComplaints-IoAF18A", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class ComplaintRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.society.app.data.api.SocietyApi api = null;
    
    @javax.inject.Inject()
    public ComplaintRepository(@org.jetbrains.annotations.NotNull()
    com.society.app.data.api.SocietyApi api) {
        super();
    }
}