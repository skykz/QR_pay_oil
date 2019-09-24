package com.beksultan.qr_pay.ui.business.head;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.db.UserPreferences;
import com.beksultan.qr_pay.model.business.head.DepartmentHead;
import com.beksultan.qr_pay.model.business.head.param.DepartmentHeadParam;
import com.beksultan.qr_pay.ui.base.BaseCategoryActivity;
import com.beksultan.qr_pay.ui.business.head.detail.DepartmentStaffActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class HeadActivity extends BaseCategoryActivity implements HeadView {

    @InjectPresenter
    HeadPresenter mvpPresenter;

    String departmentId;

    String headName;

    @BindView(R.id.relativeL)
    RelativeLayout relativeL;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.ll_head)
    LinearLayout ll_head;

    @BindView(R.id.tv_department_name)
    TextView tv_department_name;

    @BindView(R.id.tv_departmentBalance)
    TextView tv_departmentBalance;

    @BindView(R.id.tv_headName)
    TextView tv_headName;

    @BindView(R.id.tv_count)
    TextView tv_count;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_head;
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
        onSetParam();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        onSetParam();
    }

    private void onSetParam() {
        DepartmentHeadParam param = new DepartmentHeadParam(UserPreferences.get().id);
        mvpPresenter.getHead(param);
    }

    @Override
    public void onSetUpHead(DepartmentHead departmentHead) {
        tv_departmentBalance.setText(String.valueOf("Баланс: " + departmentHead.departmentBalance + " KZT"));
        departmentId = departmentHead.departmentId;
        headName = departmentHead.departmentHeadName + " " + departmentHead.departmentHeadSurname;
        tv_department_name.setText(departmentHead.departmentName);
        tv_headName.setText(String.valueOf(departmentHead.departmentHeadName + " " + departmentHead.departmentHeadSurname));
        tv_count.setText(departmentHead.departmentNumberOfStaff);
    }

    @Override
    public void onHideProgressBar() {
        progressBar.setVisibility(View.GONE);
        ll_head.setVisibility(View.VISIBLE);
    }

    @Override
    public void onShowProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        ll_head.setVisibility(View.GONE);
    }

    @OnClick(R.id.ll_head)
    public void OnclickHead() {
        startActivity(new Intent(HeadActivity.this, DepartmentStaffActivity.class)
                .putExtra("departmentId", departmentId)
                .putExtra("headName", headName));
    }

    @Override
    public void onError(String message) {
        Snackbar.make(relativeL, message, Snackbar.LENGTH_LONG).show();
    }
}
