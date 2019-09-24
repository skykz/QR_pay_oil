package com.beksultan.qr_pay.ui.business.head.detail;

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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.db.DepartmentBalancePreference;
import com.beksultan.qr_pay.db.UserPreferences;
import com.beksultan.qr_pay.model.business.head.param.DepartmentHeadParam;
import com.beksultan.qr_pay.ui.base.BaseCategoryActivity;
import com.beksultan.qr_pay.model.business.head.DepartmentStaff;
import com.beksultan.qr_pay.ui.base.stafftransaction.StaffTransactionActivity;
import com.beksultan.qr_pay.ui.business.head.add.AddStaffActivity;
import com.beksultan.qr_pay.ui.business.head.pay.PayStaffActivity;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

public class DepartmentStaffActivity extends BaseCategoryActivity implements DepartmentStaffView {

    @InjectPresenter
    DepartmentStaffPresenter mvpPresenter;

    DepartmentStaffAdapter adapter;

    String departmentId = null;

    @BindView(R.id.relativeL)
    RelativeLayout relativeL;

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.tv_headName)
    TextView tv_headName;

    @BindView(R.id.tv_departmentBalance)
    TextView tv_departmentBalance;

    @BindView(R.id.ll_add_staff)
    LinearLayout ll_add_staff;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    public int RESULT_CODE_FROM_PAY_STAFF_ACTIVITY = 10;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_department_staff;
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
        departmentId = getIntent().getExtras().getString("departmentId");
        tv_headName.setText(getIntent().getExtras().getString("headName"));
        onSetParam();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == RESULT_CODE_FROM_PAY_STAFF_ACTIVITY) {
                onSetParam();
            }
        }
    }

    private void onSetParam() {
        DepartmentHeadParam param = new DepartmentHeadParam(UserPreferences.get().id);
        mvpPresenter.getStaff(param);
    }

    @OnClick(R.id.img_back)
    public void OnclickBack() {
        finish();
    }

    @OnClick(R.id.ll_add_staff)
    public void OnclickAddStaff() {
        startActivity(new Intent(DepartmentStaffActivity.this, AddStaffActivity.class)
                .putExtra("departmentId", departmentId));
    }

    @Override
    public void onSetUpRecyclerView(List<DepartmentStaff> departmentStaffList) {
        tv_departmentBalance.setText(getString(R.string.department_staff_balance, DepartmentBalancePreference.get().departmentBalance + " KZT"));
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        adapter = new DepartmentStaffAdapter(departmentStaffList);
        recyclerView.setAdapter(adapter);
        adapter.setCallBack(new DepartmentStaffAdapter.CallBack() {
            @Override
            public void delete(DepartmentStaff departmentStaff) {
                new AlertDialog.Builder(DepartmentStaffActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Желаете удалить этот сотрудника ?")
                        .setPositiveButton("Да", (dialog, which) -> {
                            mvpPresenter.deleteStaff(departmentStaff, departmentId);
                        })
                        .setNegativeButton("Нет", (dialog, which) -> {
                        })
                        .show();
            }

            @Override
            public void pay(DepartmentStaff departmentStaff) {
                new AlertDialog.Builder(DepartmentStaffActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Желаете начислить деньги ?")
                        .setPositiveButton("Да", ((dialog, which) ->
                                startActivityForResult(new Intent(DepartmentStaffActivity.this, PayStaffActivity.class), RESULT_CODE_FROM_PAY_STAFF_ACTIVITY)))
                        .setNegativeButton("Нет", (dialog, which) -> {
                        })
                        .show();
            }

            @Override
            public void selectedItem(DepartmentStaff departmentStaff) {
                new AlertDialog.Builder(DepartmentStaffActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Желаете посмотреть транзакций сотрудника ?")
                        .setPositiveButton("Да", (dialog, which) ->
                                startActivity(new Intent(DepartmentStaffActivity.this, StaffTransactionActivity.class)
                                        .putExtra("staffId", departmentStaff.id)))
                        .setNegativeButton("Нет", (dialog, which) -> {
                        })
                        .show();
            }
        });
    }

    @Override
    public void onDeleteDepartment(DepartmentStaff departmentStaff) {
        adapter.remove(departmentStaff);
        Snackbar.make(relativeL, "success", Snackbar.LENGTH_LONG).show();
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
