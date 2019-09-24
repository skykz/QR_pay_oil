package com.beksultan.qr_pay.ui.transaction.fragments.user;

import android.os.Bundle;
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
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.adapter.transaction.TransactionAdapter;
import com.beksultan.qr_pay.db.UserPreferences;
import com.beksultan.qr_pay.model.transaction.Transaction;
import com.beksultan.qr_pay.model.transaction.param.UserAndCompanyParam;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.beksultan.qr_pay.Constant.END_DATE;
import static com.beksultan.qr_pay.Constant.START_DATE;

public class UserFragment extends MvpAppCompatFragment implements UserView {

    @InjectPresenter
    public UserPresenter mvpPresenter;

    private TransactionAdapter adapter;

    private String start_date;

    private String end_date;

    @BindView(R.id.FrameL)
    FrameLayout FrameL;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.tv_empty)
    TextView tv_empty;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_phys, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setConfigureRecyclerView();
        onSetParam();
    }

    public void setConfigureRecyclerView() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        adapter = new TransactionAdapter();
        recyclerView.setAdapter(adapter);
    }

    public void onSetParam() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            start_date = getArguments().getString(START_DATE, "");
            end_date = getArguments().getString(END_DATE, "");
        }
        UserAndCompanyParam param = new
                UserAndCompanyParam(UserPreferences.get().id, start_date, end_date);
        mvpPresenter.getUserTransactionList(param);
    }

    @Override
    public void onSetUserTransactions(List<Transaction> transactionList) {
        adapter.set(transactionList);
    }

    @Override
    public void onShowEmpty() {
        tv_empty.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideEmpty() {
        tv_empty.setVisibility(View.GONE);
    }

    @Override
    public void onShowProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onHideProgressBar() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(String error) {
        Snackbar.make(FrameL, error, Snackbar.LENGTH_LONG).show();
    }

}
