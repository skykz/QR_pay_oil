package com.beksultan.qr_pay.ui.qrScanner;

import android.annotation.SuppressLint;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.db.UserPreferences;
import com.beksultan.qr_pay.model.qr.SetQrData;

import com.beksultan.qr_pay.ui.payment.fragments.CorporateFragment;
import com.google.zxing.Result;


import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static android.Manifest.permission.CAMERA;
import static com.beksultan.qr_pay.App.getInstance;

public class QrScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private Camera camera;
    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;
    private static int camId = Camera.CameraInfo.CAMERA_FACING_BACK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scanner);
        scannerView = (ZXingScannerView) findViewById(R.id.zxscan);

        int currentApiVersion = Build.VERSION.SDK_INT;
        if(currentApiVersion >=  Build.VERSION_CODES.M)
        {
            if(checkPermission())
            {
                Toast.makeText(getApplicationContext(), "Сканируйте QR код", Toast.LENGTH_LONG).show();
            }
            else
            {
                requestPermission();
            }
        }
    }

    private boolean checkPermission()
    {
        return (ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission()
    {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAMERA);
    }

    @Override
    public void onResume() {
        super.onResume();

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission()) {
                if(scannerView == null) {
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            } else {
                requestPermission();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        scannerView.stopCamera();
//        if (progressDialog != null)
//            progressDialog.cancel();

    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {
            case REQUEST_CAMERA:
                if (grantResults.length > 0) {

                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted){
                        Toast.makeText(getApplicationContext(), "Permission Granted, Now you can access camera", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "Permission Denied, You cannot access and camera", Toast.LENGTH_LONG).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(CAMERA)) {
                                showMessageOKCancel("You need to allow access to both the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{CAMERA},
                                                            REQUEST_CAMERA);
                                                }
                                            }
                                        });
                                return;
                            }
                        }
                    }
                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {

        new android.support.v7.app.AlertDialog.Builder(QrScannerActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }


// main handle function qr data from camera
    @SuppressLint("ResourceType")
    @Override
    public void handleResult(Result result) {
        final String myResult = result.getText();
        String balance = "Coperate";
        String login = UserPreferences.get().login;
        String personID = UserPreferences.get().id;
        int departId = 74;
        SetQrData setQrData = new SetQrData(balance, login, personID, departId);

        Log.d("QRCodeScanner request ", myResult);

        readQrSendServer(setQrData);

        CorporateFragment myFragment = new CorporateFragment();


//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Результать сканирования");
//        builder.setPositiveButton("Потвердить", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                Intent intent = new Intent(QrScannerActivity.this, MainActivity.class);
//                intent.putExtra("url",myResult);
//                startActivity(intent);
//
////                Intent i = new Intent(Intent.ACTION_VIEW);
////                i.setData(Uri.parse(myResult));
////                startActivity(i);
//            }
//        });
//
//        builder.setNeutralButton("Отмена", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getApplicationContext(), "Повтор...", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        builder.setOnDismissListener(new DialogInterface.OnDismissListener(){
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                scannerView.resumeCameraPreview(QrScannerActivity.this);
//                Toast.makeText(getApplicationContext(), "Повтор...", Toast.LENGTH_SHORT).show();
//            }
//        });



//        CorporateFragment fragment = new CorporateFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("url", myResult);
////        fragment.setArguments(bundle);
//            if (bundle != null) {
//                fragment.getArgs(bundle);
//            }

        finish();
        Fragment fragment = new CorporateFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.qr_scanner, fragment).commit();

    }

    void readQrSendServer(SetQrData setQrData) {

//
        Call<String> call = getInstance().getApiService().setQrData(setQrData);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    // запрос выполнился успешно, сервер вернул Status 200
                    Log.d("DONE !!!!! POST ", "YEEEEEEES");
                } else {
                    // сервер вернул ошибку
                    Log.d("DONE !!!!! POST ", "ERRRRRRRRRRROR");

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // ошибка во время выполнения запроса
                Log.d("DONE !!!!! POST ", "PZDCCCCCCCCCCCCCC");
            }
        });
    }

}


