package com.beksultan.qr_pay.ui.business.director.pay;

import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.db.UserPreferences;
import com.beksultan.qr_pay.model.business.director.Department;
import com.beksultan.qr_pay.model.business.director.param.SetBalanceParam;
import com.beksultan.qr_pay.model.business.director.param.DepartmentParam;
import com.beksultan.qr_pay.ui.base.BaseCategoryActivity;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import static com.beksultan.qr_pay.utils.StringUtils.getDouble;

public class PayActivity extends BaseCategoryActivity implements PayView {

    @InjectPresenter
    PayPresenter mvpPresenter;

    public PayAdapter adapter;

    @BindView(R.id.relativeL)
    RelativeLayout relativeL;

    @BindView(R.id.edt_money)
    EditText edt_money;

    @BindView(R.id.btn_all)
    Button btn_all;

    @BindView(R.id.btn_selected)
    Button btn_selected;

    @BindView(R.id.btn_cancel)
    Button btn_cancel;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    List<Department> departmentList;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_pay;
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

    @OnClick(R.id.btn_all)
    public void onClickAll() {
        List<SetBalanceParam> setBalanceParamList = new ArrayList<>();
        for (Department department : departmentList) {
            setBalanceParamList.add(
                    new SetBalanceParam(
                            UserPreferences.get().id,
                            department.headId,
                            String.valueOf(getDouble(edt_money.getText().toString()))));
        }
        mvpPresenter.sendBalance(setBalanceParamList);
    }

    @OnClick(R.id.btn_selected)
    public void onClickSelect() {
        List<SetBalanceParam> setBalanceParamList = new ArrayList<>();
        for (Department department : departmentList) {
            if (department.isSelected) {
                setBalanceParamList.add(
                        new SetBalanceParam(
                                UserPreferences.get().id,
                                department.headId,
                                String.valueOf(getDouble(edt_money.getText().toString()))));
            }
        }
        mvpPresenter.sendBalance(setBalanceParamList);
    }

    @OnClick(R.id.btn_cancel)
    public void OnclickCancel() {
        finish();
    }

    private void onSetParam() {
        DepartmentParam param = new DepartmentParam(UserPreferences.get().id);
        mvpPresenter.getDepartmentList(param);
    }

    @Override
    public void onSetUpRecyclerView(List<Department> departmentList) {
        this.departmentList = departmentList;
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        adapter = new PayAdapter(departmentList, PayActivity.this);
        recyclerView.setAdapter(adapter);
        adapter.setCallback((Department department) -> {
            for (Department selectedDepartment : departmentList) {
                if (department.equals((selectedDepartment))) {
                    selectedDepartment.isSelected = !selectedDepartment.isSelected;
                }
            }
            adapter.selectItem(departmentList);
        });
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

    @Override
    public void onSuccessBalance() {
        setResult(RESULT_OK);
        finish();
    }
}
