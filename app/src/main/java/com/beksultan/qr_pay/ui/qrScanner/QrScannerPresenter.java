package com.beksultan.qr_pay.ui.qrScanner;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;


@InjectViewState
public class QrScannerPresenter extends MvpPresenter<QrView> {

//        App.getInstance().getApiService().setQrData(setQrData)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
////                .doOnSubscribe((v)-> getViewState().onShowProgressBar())
////                .doAfterTerminate(() -> getViewState().onHideProgressBar())
//                .subscribe(new SingleObserver<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(String s) {
//                        getViewState().onSuccessQrData();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d("QRCodeScanner POST ","EEEEEEEEEEROR");
//
//                        getViewState().onError(e.getMessage());
//                    }
//                });
//}

}
