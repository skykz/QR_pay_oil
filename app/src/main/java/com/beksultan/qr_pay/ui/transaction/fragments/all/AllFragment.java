package com.beksultan.qr_pay.ui.transaction.fragments.all;

import android.os.Bundle;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.adapter.transaction.TransactionPaginationAdapter;
import com.beksultan.qr_pay.db.UserPreferences;
import com.beksultan.qr_pay.model.transaction.Transaction;
import com.beksultan.qr_pay.model.transaction.param.AllParam;
import com.beksultan.qr_pay.ui.base.EndlessScrollListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import static com.beksultan.qr_pay.Constant.END_DATE;
import static com.beksultan.qr_pay.Constant.START_DATE;

public class AllFragment extends MvpAppCompatFragment implements AllView {

    @InjectPresenter
    public AllPresenter mvpPresenter;

    public TransactionPaginationAdapter adapter;

    @BindView(R.id.FrameL)
    FrameLayout FrameL;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setConfigureRecyclerView();
        onSetParam(false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setConfigureRecyclerView(){
        adapter = new TransactionPaginationAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    public void onSetParam(boolean isLoadMore) {
        Bundle bundle = getArguments();
        String start = bundle != null ? bundle.getString(START_DATE): null;
        String end = bundle != null ? bundle.getString(END_DATE) : null;
        AllParam param = new AllParam(UserPreferences.get().id, start, end);
        mvpPresenter.getTransactions(param, isLoadMore);
        recyclerView.addOnScrollListener(new EndlessScrollListener(recyclerView) {
            @Override
            public void onRequestMoreItems() {
                if(!adapter.isLoading())
                {
                    mvpPresenter.getTransactions(param, true);
                }
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_transaction, container, false);
    }

    @Override
    public void onShowProgressBar(boolean isShow) {
        progressBar.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onShowProgressBarLoadMore(boolean isShow) {
        adapter.setLoading(isShow);
    }

    @Override
    public void onSetTransactionList(List<Transaction> transactionList) {
        adapter.clearItems();
        adapter.addList(transactionList);
    }

    @Override
    public void addTransactionList(List<Transaction> transactionList) {
        adapter.addList(transactionList);
    }

    @Override
    public void onShowError(String error) {
        Snackbar.make(FrameL,error,Snackbar.LENGTH_LONG).show();
    }
}
