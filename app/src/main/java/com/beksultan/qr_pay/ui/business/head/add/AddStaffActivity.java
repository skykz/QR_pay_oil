package com.beksultan.qr_pay.ui.business.head.add;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.model.business.head.add.HeadStaff;
import com.beksultan.qr_pay.model.business.head.param.AddStaffParam;
import com.beksultan.qr_pay.model.business.head.param.DeleteStaffParam;
import com.beksultan.qr_pay.model.business.head.param.SearchStaffParam;
import com.beksultan.qr_pay.ui.base.BaseCategoryActivity;
import com.beksultan.qr_pay.ui.business.head.HeadActivity;
import com.beksultan.qr_pay.ui.business.head.detail.DepartmentStaffActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddStaffActivity extends BaseCategoryActivity implements AddStaffView {

    @InjectPresenter
    AddStaffPresenter mvpPresenter;

    SearchStaffAdapter adapter;

    @BindView(R.id.relativeL)
    RelativeLayout relativeL;

    @BindView(R.id.edt_email_staff)
    EditText edt_email_staff;

    @BindView(R.id.btn_cancel)
    Button btn_cancel;

    @BindView(R.id.btn_search)
    Button btn_search;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_add_staff;
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
    }

    @OnClick(R.id.btn_cancel)
    public void OnClickCancel() {
        finish();
    }

    @OnClick(R.id.btn_search)
    public void OnClickSearchStaff() {
        SearchStaffParam param = new SearchStaffParam(edt_email_staff.getText().toString().trim());
        mvpPresenter.searchStaff(param);
    }

    @Override
    public void onSetUpRecyclerView(List<HeadStaff> headStaffList) {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        adapter = new SearchStaffAdapter(headStaffList);
        recyclerView.setAdapter(adapter);
        adapter.setCallback((staff -> {

            new AlertDialog.Builder(AddStaffActivity.this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Желаете добавить этого сотрудника ?")
                    .setPositiveButton("Да", (dialog, which) -> {
                        AddStaffParam param = new AddStaffParam(staff.id, getIntent().getExtras().getString("departmentId"));
                        mvpPresenter.addStaff(param);
                    })
                    .setNegativeButton("Нет", (dialog, which) -> {
                    })
                    .show();
        }));
    }

    @Override
    public void onSuccessCreated(String s) {
        if (s.equals("true")) {
            startActivity(new Intent(AddStaffActivity.this, HeadActivity.class));
            finish();
        }
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
    public void onError(String string) {
        Snackbar.make(relativeL, string, Snackbar.LENGTH_LONG).show();
    }
}
