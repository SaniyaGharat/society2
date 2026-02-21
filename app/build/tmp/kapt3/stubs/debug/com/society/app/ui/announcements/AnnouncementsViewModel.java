package com.society.app.ui.announcements;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\r\u001a\u00020\u000eR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/society/app/ui/announcements/AnnouncementsViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/society/app/data/repository/AnnouncementRepository;", "(Lcom/society/app/data/repository/AnnouncementRepository;)V", "_announcements", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/society/app/data/model/Announcement;", "announcements", "Landroidx/lifecycle/LiveData;", "getAnnouncements", "()Landroidx/lifecycle/LiveData;", "loadAnnouncements", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class AnnouncementsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.society.app.data.repository.AnnouncementRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.society.app.data.model.Announcement>> _announcements = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.society.app.data.model.Announcement>> announcements = null;
    
    @javax.inject.Inject()
    public AnnouncementsViewModel(@org.jetbrains.annotations.NotNull()
    com.society.app.data.repository.AnnouncementRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.society.app.data.model.Announcement>> getAnnouncements() {
        return null;
    }
    
    public final void loadAnnouncements() {
    }
}