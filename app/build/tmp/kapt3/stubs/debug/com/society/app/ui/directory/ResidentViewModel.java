package com.society.app.ui.directory;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0012"}, d2 = {"Lcom/society/app/ui/directory/ResidentViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/society/app/data/repository/ResidentRepository;", "(Lcom/society/app/data/repository/ResidentRepository;)V", "_residents", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/society/app/data/model/Resident;", "residents", "Landroidx/lifecycle/LiveData;", "getResidents", "()Landroidx/lifecycle/LiveData;", "loadResidents", "", "search", "query", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ResidentViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.society.app.data.repository.ResidentRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.society.app.data.model.Resident>> _residents = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.society.app.data.model.Resident>> residents = null;
    
    @javax.inject.Inject()
    public ResidentViewModel(@org.jetbrains.annotations.NotNull()
    com.society.app.data.repository.ResidentRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.society.app.data.model.Resident>> getResidents() {
        return null;
    }
    
    public final void loadResidents() {
    }
    
    public final void search(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
}