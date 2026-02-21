package com.society.app.ui.complaints;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J&\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0010J\u0006\u0010\u0014\u001a\u00020\u000eR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/society/app/ui/complaints/ComplaintViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/society/app/data/repository/ComplaintRepository;", "(Lcom/society/app/data/repository/ComplaintRepository;)V", "_complaints", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/society/app/data/model/Complaint;", "complaints", "Landroidx/lifecycle/LiveData;", "getComplaints", "()Landroidx/lifecycle/LiveData;", "addComplaint", "", "title", "", "desc", "category", "priority", "loadComplaints", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ComplaintViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.society.app.data.repository.ComplaintRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.society.app.data.model.Complaint>> _complaints = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.society.app.data.model.Complaint>> complaints = null;
    
    @javax.inject.Inject()
    public ComplaintViewModel(@org.jetbrains.annotations.NotNull()
    com.society.app.data.repository.ComplaintRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.society.app.data.model.Complaint>> getComplaints() {
        return null;
    }
    
    public final void loadComplaints() {
    }
    
    public final void addComplaint(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String desc, @org.jetbrains.annotations.NotNull()
    java.lang.String category, @org.jetbrains.annotations.NotNull()
    java.lang.String priority) {
    }
}