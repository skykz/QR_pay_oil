package com.beksultan.qr_pay.ui.business.director.edit;

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
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.adapter.business.director.AddDepartmentAdapter;
import com.beksultan.qr_pay.db.CompanyPreferences;
import com.beksultan.qr_pay.model.business.director.addDepartment.Head;
import com.beksultan.qr_pay.model.business.director.param.HeadParam;
import com.beksultan.qr_pay.model.business.director.param.UpdateParam;
import com.beksultan.qr_pay.ui.base.BaseCategoryActivity;
import com.beksultan.qr_pay.ui.business.director.DirectorActivity;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

public class EditActivity extends BaseCategoryActivity implements EditView {

    @InjectPresenter
    EditPresenter mvpPresenter;

    AddDepartmentAdapter adapter;

    @BindView(R.id.relativeL)
    RelativeLayout relativeL;

    @BindView(R.id.edt_departmentName)
    EditText edt_departmentName;

    @BindView(R.id.edt_email_head)
    EditText edt_email_head;

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
        return R.layout.activity_edit;
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
        edt_departmentName.setText(getIntent().getExtras().getString("departmentName"));
    }

    @OnClick(R.id.btn_cancel)
    public void onClickCancel() {
        finish();
    }

    @OnClick(R.id.btn_search)
    public void onClickSearch() {
        onSetParam();
    }

    private void onSetParam() {
        HeadParam param = new HeadParam(CompanyPreferences.get().companyId, edt_email_head.getText().toString().trim());
        mvpPresenter.getHead(param);
    }

    @Override
    public void onSetUpRecyclerView(List<Head> headList) {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        adapter = new AddDepartmentAdapter(headList);
        recyclerView.setAdapter(adapter);
        adapter.setCallback((Head head) ->
                new AlertDialog.Builder(EditActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Действительно хотите выбрать этого рабочего?")
                        .setPositiveButton("Да", (dialogInterface, i) ->
                        {
                            UpdateParam param = new UpdateParam(head.id, getIntent().getExtras().getString("departmentId"), edt_departmentName.getText().toString());
                            mvpPresenter.updateDepartment(param);
                        })
                        .setNegativeButton("Нет", (dialogInterface, i) -> {
                        })
                        .show());
    }

    @Override
    public void onUpdateDepartment(String string) {
       if (string.equals("true")){
           startActivity(new Intent(EditActivity.this, DirectorActivity.class));
           finish();
       }
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
