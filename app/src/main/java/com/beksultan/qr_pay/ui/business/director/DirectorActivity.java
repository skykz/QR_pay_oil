package com.beksultan.qr_pay.ui.business.director;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.adapter.business.director.DepartmentAdapter;
import com.beksultan.qr_pay.db.UserPreferences;
import com.beksultan.qr_pay.model.business.director.Company;
import com.beksultan.qr_pay.model.business.director.Department;
import com.beksultan.qr_pay.model.business.director.param.DepartmentParam;
import com.beksultan.qr_pay.ui.base.BaseCategoryActivity;
import com.beksultan.qr_pay.ui.business.director.add.AddDepartmentActivity;
import com.beksultan.qr_pay.ui.business.director.detail.DetailDepartmentActivity;
import com.beksultan.qr_pay.ui.business.director.edit.EditActivity;
import com.beksultan.qr_pay.ui.business.director.pay.PayActivity;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DirectorActivity extends BaseCategoryActivity implements DirectorView {

    @InjectPresenter
    DirectorPresenter mvpPresenter;

    public DepartmentAdapter adapter;

    @BindView(R.id.tv_balance)
    TextView tv_balance;

    @BindView(R.id.relativeL)
    RelativeLayout relativeL;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.ll_add_department)
    LinearLayout ll_add_department;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public int RESULT_CODE_FROM_PAY_ACTIVITY = 10;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_business;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == RESULT_CODE_FROM_PAY_ACTIVITY) {
                onSetParam();
            }
        }
    }

    private void onSetParam() {
        DepartmentParam param = new DepartmentParam(UserPreferences.get().id);
        mvpPresenter.getDepartmentList(param);
    }

    @Override
    public void onSetUpRecyclerView(List<Department> departmentList, Company company) {
        tv_balance.setText(String.valueOf("Баланс: " + company.companyBalance + " KZT"));
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        adapter = new DepartmentAdapter(departmentList);
        recyclerView.setAdapter(adapter);
        adapter.setCallback(new DepartmentAdapter.Callback() {
            @Override
            public void edit(Department department) {
                new AlertDialog.Builder(DirectorActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Желаете редактироавть отдел?")
                        .setPositiveButton("Да", (dialog, which) ->
                                startActivity(new Intent(DirectorActivity.this, EditActivity.class)
                                        .putExtra("departmentId", department.id)
                                        .putExtra("departmentName", department.name)))
                        .setNegativeButton("Нет", (dialog, which) -> {
                        })
                        .show();
            }

            @Override
            public void delete(Department department) {
                new AlertDialog.Builder(DirectorActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Желаете удалить этот отдел ?")
                        .setPositiveButton("Да", (dialog, which) ->
                                mvpPresenter.deleteDepartment(department)
                        )
                        .setNegativeButton("Нет", (dialog, which) -> {
                        })
                        .show();
            }

            @Override
            public void pay(Department department) {
                new AlertDialog.Builder(DirectorActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Желаете начислить деньги ?")
                        .setPositiveButton("Да", ((dialog, which) ->
                                startActivityForResult(new Intent(DirectorActivity.this, PayActivity.class), RESULT_CODE_FROM_PAY_ACTIVITY)))
                        .setNegativeButton("Нет", (dialog, which) -> {
                        })
                        .show();
            }

            @Override
            public void selectedItem(Department department) {
                new AlertDialog.Builder(DirectorActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Желаете посмотреть детально о этой отделений ?")
                        .setPositiveButton("Да", ((dialog, which) ->
                                startActivity(new Intent(DirectorActivity.this, DetailDepartmentActivity.class)
                                        .putExtra("departmentId_director", department.id)
                                        .putExtra("price", department.departmentBalance)
                                        .putExtra("department_name", department.name))))
                        .setNegativeButton("Нет", (dialog, which) -> {
                        })
                        .show();
            }
        });
    }

    @Override
    public void onDeleteDepartment(Department department) {
        adapter.remove(department);
        Snackbar.make(relativeL, "success", Snackbar.LENGTH_LONG).show();
    }

    @OnClick(R.id.ll_add_department)
    public void onClickAdDepartment() {
        startActivity(new Intent(DirectorActivity.this, AddDepartmentActivity.class));
    }

    @Override
    public void onHideProgressBar() {
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onShowProgressBar() {
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(String error) {
        Snackbar.make(relativeL, error, Snackbar.LENGTH_LONG).show();
    }
}
