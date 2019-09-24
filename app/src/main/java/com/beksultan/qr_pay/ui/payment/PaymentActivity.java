package com.beksultan.qr_pay.ui.payment;

import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;
import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.adapter.viewpager.ViewPagerAdapter;
import com.beksultan.qr_pay.ui.base.BaseCategoryActivity;
import com.beksultan.qr_pay.ui.payment.fragments.CorporateFragment;
import com.beksultan.qr_pay.ui.payment.fragments.PrivateFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentActivity extends BaseCategoryActivity {

    public ViewPagerAdapter viewPagerAdapter;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        // Add fragments in viewpager //

        viewPagerAdapter.AddFragment(new PrivateFragment(), "Личный");
        viewPagerAdapter.AddFragment(new CorporateFragment(), "Корпоративный");

        //---------------------------//

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected int getLayoutResourceId() { return R.layout.activity_payment;}

    @Override
    protected void setCurrentNavigationButton() {
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_payment);
        MenuItem menuItem = btn_navigation.getMenu().findItem(R.id.nav_payment);
        menuItem.setIcon(drawable);
        menuItem.setEnabled(false);
    }

}
