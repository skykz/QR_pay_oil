package com.beksultan.qr_pay.ui.payment.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.beksultan.qr_pay.Constant;
import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.db.BalancePreferences;
import com.beksultan.qr_pay.db.UserPreferences;
import com.beksultan.qr_pay.model.balance.Balance;
import com.beksultan.qr_pay.model.balance.BalanceParam;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PrivateFragment extends MvpAppCompatFragment implements BalanceView {

    @InjectPresenter
    PaymentPresenter mvpPresenter;

    @BindView(R.id.ll_main)
    LinearLayout ll_main;

    @BindView(R.id.ll_content)
    LinearLayout ll_content;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.img_qr)
    ImageView img_qr;

    @BindView(R.id.tv_price)
    TextView tv_price;

    String url = null;
    String price = null;
    String user_id = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_private, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        onSetParam();
        setupQRCode();
    }

    private void onSetParam() {
        BalanceParam param = new BalanceParam(UserPreferences.get().id);
        mvpPresenter.onShowBalance(param);
    }

    private void setupQRCode() {
        price = BalancePreferences.get().personalBalance;
        user_id = UserPreferences.get().id;

        url = Constant.BASE_URL + "cashier?id=" + user_id + "&accountName=" + "Personal";

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(url, BarcodeFormat.QR_CODE, 500, 500);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            img_qr.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onSetBalance(Balance balance) {
        tv_price.setText(String.valueOf(balance.personalBalance + " KZT"));
    }

    @Override
    public void onShowProgressBar(boolean isShow) {
        if (isShow) {
            progressBar.setVisibility(View.VISIBLE);
            ll_content.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            ll_content.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onError(String s) {
        Snackbar.make(ll_main, s, Snackbar.LENGTH_LONG).show();
    }

}
