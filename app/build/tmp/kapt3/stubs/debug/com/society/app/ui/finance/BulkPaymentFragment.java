package com.society.app.ui.finance;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001:\u0001!B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J$\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0014H\u0016J\u001a\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u00162\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\b\u0010 \u001a\u00020\u0014H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/society/app/ui/finance/BulkPaymentFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/society/app/databinding/FragmentBulkPaymentBinding;", "adapter", "Lcom/society/app/ui/finance/BulkPaymentFragment$BulkSelectionAdapter;", "api", "Lcom/society/app/data/api/SocietyApi;", "getApi", "()Lcom/society/app/data/api/SocietyApi;", "setApi", "(Lcom/society/app/data/api/SocietyApi;)V", "binding", "getBinding", "()Lcom/society/app/databinding/FragmentBulkPaymentBinding;", "residents", "", "Lcom/society/app/data/model/ResidentBalance;", "loadResidents", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "processPayments", "BulkSelectionAdapter", "app_debug"})
public final class BulkPaymentFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.society.app.databinding.FragmentBulkPaymentBinding _binding;
    @javax.inject.Inject()
    public com.society.app.data.api.SocietyApi api;
    private com.society.app.ui.finance.BulkPaymentFragment.BulkSelectionAdapter adapter;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.society.app.data.model.ResidentBalance> residents;
    
    public BulkPaymentFragment() {
        super();
    }
    
    private final com.society.app.databinding.FragmentBulkPaymentBinding getBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.society.app.data.api.SocietyApi getApi() {
        return null;
    }
    
    public final void setApi(@org.jetbrains.annotations.NotNull()
    com.society.app.data.api.SocietyApi p0) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void loadResidents() {
    }
    
    private final void processPayments() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0012\u0013B\u0005\u00a2\u0006\u0002\u0010\u0004J\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\rH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/society/app/ui/finance/BulkPaymentFragment$BulkSelectionAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/society/app/data/model/ResidentBalance;", "Lcom/society/app/ui/finance/BulkPaymentFragment$BulkSelectionAdapter$ViewHolder;", "()V", "selectedItems", "", "getSelectedItems", "", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "DiffCallback", "ViewHolder", "app_debug"})
    public static final class BulkSelectionAdapter extends androidx.recyclerview.widget.ListAdapter<com.society.app.data.model.ResidentBalance, com.society.app.ui.finance.BulkPaymentFragment.BulkSelectionAdapter.ViewHolder> {
        @org.jetbrains.annotations.NotNull()
        private final java.util.Set<com.society.app.data.model.ResidentBalance> selectedItems = null;
        
        public BulkSelectionAdapter() {
            super(null);
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public com.society.app.ui.finance.BulkPaymentFragment.BulkSelectionAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override()
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
        com.society.app.ui.finance.BulkPaymentFragment.BulkSelectionAdapter.ViewHolder holder, int position) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<com.society.app.data.model.ResidentBalance> getSelectedItems() {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/society/app/ui/finance/BulkPaymentFragment$BulkSelectionAdapter$DiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/society/app/data/model/ResidentBalance;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
        public static final class DiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.society.app.data.model.ResidentBalance> {
            
            public DiffCallback() {
                super();
            }
            
            @java.lang.Override()
            public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
            com.society.app.data.model.ResidentBalance oldItem, @org.jetbrains.annotations.NotNull()
            com.society.app.data.model.ResidentBalance newItem) {
                return false;
            }
            
            @java.lang.Override()
            public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
            com.society.app.data.model.ResidentBalance oldItem, @org.jetbrains.annotations.NotNull()
            com.society.app.data.model.ResidentBalance newItem) {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/society/app/ui/finance/BulkPaymentFragment$BulkSelectionAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/society/app/databinding/ItemLedgerEntryBinding;", "(Lcom/society/app/databinding/ItemLedgerEntryBinding;)V", "getBinding", "()Lcom/society/app/databinding/ItemLedgerEntryBinding;", "app_debug"})
        public static final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
            @org.jetbrains.annotations.NotNull()
            private final com.society.app.databinding.ItemLedgerEntryBinding binding = null;
            
            public ViewHolder(@org.jetbrains.annotations.NotNull()
            com.society.app.databinding.ItemLedgerEntryBinding binding) {
                super(null);
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.society.app.databinding.ItemLedgerEntryBinding getBinding() {
                return null;
            }
        }
    }
}