package com.beksultan.qr_pay.ui.base.stafftransaction;

import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.model.transaction.Transaction;
import com.beksultan.qr_pay.model.transaction.param.UserAndCompanyParam;
import com.beksultan.qr_pay.ui.base.BaseCategoryActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StaffTransactionActivity extends BaseCategoryActivity implements StaffTransactionView {

    @InjectPresenter
    StaffTransactionPresenter mvpPresenter;

    StaffTransactionAdapter adapter;

    @BindView(R.id.relativeL)
    RelativeLayout relativeL;

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_staff_transaction;
    }

    @Override
    protected void setCurrentNavigationButton() {
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_business);
        MenuItem menuItem = btn_navigation.getMenu().findItem(R.id.nav_business);
        menuItem.setIcon(drawable);
        menuItem.setEnabled(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        onSetParam();
    }

    @OnClick(R.id.img_back)
    public void onClickBack() {
        finish();
    }

    private void onSetParam() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -29);
        String end_date = format.format(date);
        String start_date = format.format(calendar.getTime());

        UserAndCompanyParam param = new
                UserAndCompanyParam(getIntent().getExtras().getString("staffId"), start_date, end_date);
        mvpPresenter.getStaffTransactionList(param);
    }

    @Override
    public void onSetUpRecyclerView(List<Transaction> transactionList) {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        adapter = new StaffTransactionAdapter(transactionList);
        recyclerView.setAdapter(adapter);
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
    public void onError(String message) {
        Snackbar.make(relativeL, message, Snackbar.LENGTH_LONG).show();
    }
}
