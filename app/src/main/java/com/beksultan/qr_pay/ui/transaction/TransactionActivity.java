package com.beksultan.qr_pay.ui.transaction;

import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.adapter.viewpager.ViewPagerAdapter;
import com.beksultan.qr_pay.ui.base.BaseCategoryActivity;
import com.beksultan.qr_pay.ui.transaction.fragments.all.AllFragment;
import com.beksultan.qr_pay.ui.transaction.fragments.user.UserFragment;
import com.beksultan.qr_pay.ui.transaction.fragments.company.CompanyFragment;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import static com.beksultan.qr_pay.Constant.END_DATE;
import static com.beksultan.qr_pay.Constant.START_DATE;

public class TransactionActivity extends BaseCategoryActivity {

    public ViewPagerAdapter viewPagerAdapter;

    private Bundle bundle;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.img_filter)
    ImageView img_filter;

    @BindView(R.id.ll_filter)
    LinearLayout ll_filter;

    @BindView(R.id.edt_end)
    EditText edt_end;

    @BindView(R.id.edt_start)
    EditText edt_start;

    @BindView(R.id.btn_cancel)
    Button btn_cancel;

    @BindView(R.id.btn_apply)
    Button btn_apply;

    AllFragment allFragment;
    UserFragment userFragment;
    CompanyFragment companyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        // Add fragments in viewpager //

        allFragment = new AllFragment();
        userFragment = new UserFragment();
        companyFragment = new CompanyFragment();

        viewPagerAdapter.AddFragment(allFragment, "Все");
        viewPagerAdapter.AddFragment(userFragment, "Физ");
        viewPagerAdapter.AddFragment(companyFragment, "Юрид");

        onCheckToNullParam();

        //---------------------------//

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_transaction_history;
    }

    @Override
    protected void setCurrentNavigationButton() {
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_history);
        MenuItem menuItem = btn_navigation.getMenu().findItem(R.id.nav_history);
        menuItem.setIcon(drawable);
        menuItem.setEnabled(false);
    }

    @OnClick(R.id.img_filter)
    public void onShowFilter() {

        if (ll_filter.getVisibility() == View.GONE) {
            ll_filter.setVisibility(View.VISIBLE);

        } else {
            ll_filter.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.btn_cancel)
    public void onCancel() {
        ll_filter.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_apply)
    public void onApply() {
        if (ll_filter.getVisibility() == View.GONE) {
            ll_filter.setVisibility(View.VISIBLE);
        } else {
            bundle = new Bundle();
            bundle.putString(START_DATE, edt_start.getText().toString().trim());
            bundle.putString(END_DATE, edt_end.getText().toString().trim());

            allFragment.setArguments(bundle);
            userFragment.setArguments(bundle);
            companyFragment.setArguments(bundle);
            int currentPosition = viewPager.getCurrentItem();
            viewPager.setAdapter(viewPagerAdapter);
            viewPager.setCurrentItem(currentPosition);
            ll_filter.setVisibility(View.GONE);
        }
    }

    public void onCheckToNullParam() {
        String end = edt_end.getText().toString().trim();
        String start = edt_start.getText().toString().trim();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -29);

        if (TextUtils.isEmpty(end) && TextUtils.isEmpty(start)) {
            end = format.format(date);
            start = format.format(calendar.getTime());
            edt_end.setText(end);
            edt_start.setText(start);

            bundle = new Bundle();
            bundle.putString(START_DATE, start);
            bundle.putString(END_DATE, end);

            allFragment.setArguments(bundle);
            userFragment.setArguments(bundle);
            companyFragment.setArguments(bundle);
        }

    }

}
