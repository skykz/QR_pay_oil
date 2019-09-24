package com.beksultan.qr_pay.ui.business.head.pay;

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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.db.UserPreferences;
import com.beksultan.qr_pay.model.business.director.param.SetBalanceParam;
import com.beksultan.qr_pay.model.business.head.DepartmentStaff;
import com.beksultan.qr_pay.model.business.head.param.DepartmentHeadParam;
import com.beksultan.qr_pay.ui.base.BaseCategoryActivity;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import static com.beksultan.qr_pay.utils.StringUtils.getDouble;

public class PayStaffActivity extends BaseCategoryActivity implements PayStaffView {

    @InjectPresenter
    PayStaffPresenter mvpPresenter;

    PayStaffAdapter adapter;

    @BindView(R.id.relativeL)
    RelativeLayout relativeL;

    @BindView(R.id.img_back)
    ImageView img_back;

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

    List<DepartmentStaff> departmentStaffList;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_pay_staff;
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

    @OnClick(R.id.img_back)
    public void onClickBack() {
        finish();
    }

    @OnClick(R.id.btn_cancel)
    public void onClickCancel() {
        finish();
    }

    @OnClick(R.id.btn_all)
    public void onClickAll() {
        List<SetBalanceParam> setBalanceParamList = new ArrayList<>();
        for (DepartmentStaff departmentStaff : departmentStaffList) {
            setBalanceParamList.add(
                    new SetBalanceParam(
                            UserPreferences.get().id,
                            departmentStaff.id,
                            String.valueOf(getDouble(edt_money.getText().toString()))));

        }
        mvpPresenter.sendBalance(setBalanceParamList);
    }

    @OnClick(R.id.btn_selected)
    public void onClickSelected() {
        List<SetBalanceParam> setBalanceParamList = new ArrayList<>();
        for (DepartmentStaff departmentStaff : departmentStaffList) {
            if (departmentStaff.isSelected) {
                setBalanceParamList.add(
                        new SetBalanceParam(
                                UserPreferences.get().id,
                                departmentStaff.id,
                                String.valueOf(getDouble(edt_money.getText().toString()))));
            }
        }
        mvpPresenter.sendBalance(setBalanceParamList);
    }

    private void onSetParam() {
        DepartmentHeadParam param = new DepartmentHeadParam(UserPreferences.get().id);
        mvpPresenter.getStaff(param);
    }

    @Override
    public void onSetUpRecyclerView(List<DepartmentStaff> departmentStaffList) {
        this.departmentStaffList = departmentStaffList;
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        adapter = new PayStaffAdapter(departmentStaffList, PayStaffActivity.this);
        recyclerView.setAdapter(adapter);
        adapter.setCallback((DepartmentStaff departmentStaff) -> {
            for (DepartmentStaff selectedDepartmentStaff : departmentStaffList) {
                if (departmentStaff.equals((selectedDepartmentStaff))) {
                    selectedDepartmentStaff.isSelected = !selectedDepartmentStaff.isSelected;
                }
            }
            adapter.selectItem(departmentStaffList);
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
    public void onSuccessBalance() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onError(String s) {
        Snackbar.make(relativeL, s, Snackbar.LENGTH_LONG).show();
    }
}
