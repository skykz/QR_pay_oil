package com.beksultan.qr_pay.ui.business.director.detail;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.model.business.director.detail.Staff;
import com.beksultan.qr_pay.model.business.director.param.DetailDepartmentParam;
import com.beksultan.qr_pay.ui.base.BaseCategoryActivity;
import com.beksultan.qr_pay.ui.base.stafftransaction.StaffTransactionActivity;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailDepartmentActivity extends BaseCategoryActivity implements DetailDepartmentView {

    @InjectPresenter
    DetailDepartmentPresenter mvpPresenter;

    StaffAdapter adapter;

    @BindView(R.id.relativeL)
    RelativeLayout relativeL;

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.tv_name)
    TextView tv_name;

    @BindView(R.id.tv_balance)
    TextView tv_balance;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_detail_department;
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
        tv_name.setText(getIntent().getExtras().getString("department_name"));
        tv_balance.setText(String.valueOf("Баланс: " + getIntent().getExtras().getString("price") + " KZT"));
        onSetParam();
    }

    private void onSetParam() {
        DetailDepartmentParam param = new DetailDepartmentParam(getIntent().getExtras().getString("departmentId_director"));
        mvpPresenter.getDepartmentStaff(param);
    }

    @OnClick(R.id.img_back)
    public void onClickBack() {
        finish();
    }

    @Override
    public void onSetUpRecyclerView(List<Staff> staffList) {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        adapter = new StaffAdapter(staffList);
        recyclerView.setAdapter(adapter);
        adapter.setCallback((staff ->
                new AlertDialog.Builder(DetailDepartmentActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Желаете посмотреть транзакций сотрудника ?")
                        .setPositiveButton("Да", (dialogInterface, i) ->
                                startActivity(new Intent(DetailDepartmentActivity.this, StaffTransactionActivity.class)
                                        .putExtra("staffId", staff.id)))
                        .setNegativeButton("Нет", (dialogInterface, i) -> {
                        })
                        .show()));
    }

    @Override
    public void onHideProgressBar() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onShowProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onError(String message) {
        Snackbar.make(relativeL, message, Snackbar.LENGTH_LONG).show();
    }
}
