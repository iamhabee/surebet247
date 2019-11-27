package com.arke.sdk;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView zXingScannerView;
    private TextView txtResult;
    public SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        zXingScannerView =  findViewById(R.id.zxscan);
        txtResult =  findViewById(R.id.txt_result);
        preferences = getSharedPreferences("username", MODE_PRIVATE);

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        zXingScannerView.setResultHandler(ScanActivity.this);
                        zXingScannerView.startCamera();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(ScanActivity.this, "You must accept this permission", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                })
                .check();
    }

    @Override
    protected void onDestroy() {
        zXingScannerView.stopCamera();
        super.onDestroy();
    }

    @Override
    public void handleResult(Result rawResult) {
        Intent intent = new Intent();
        intent.putExtra("result", rawResult.getText());
        setResult(Activity.RESULT_OK, intent);
        finish();
    }


    private void processRawRequest(String text) {
        if (text.startsWith("http")){
            Toast.makeText(ScanActivity.this, text, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ScanActivity.this, HomeActivity.class);
//            intent.putExtra("webUrl", text);
            startActivity(intent);
        }else {
            Toast.makeText(ScanActivity.this, "Invalid data", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(ScanActivity.this, c);
//            intent.putExtra("webUrl", text);
//            startActivity(intent);
        }
    }

    private void processRawRequestOnActivityResult(String text, Class c){
        if (text.startsWith("http")){
            Toast.makeText(ScanActivity.this, "This is an address", Toast.LENGTH_SHORT).show();
            preferences.edit().putString("user", text).apply();
            Intent intent = new Intent(ScanActivity.this, c);
            intent.putExtra("webUrl", text);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(ScanActivity.this, "Not URL data", Toast.LENGTH_SHORT).show();
            preferences.edit().putString("user", text).apply();
            Intent intent = new Intent(ScanActivity.this, c);
            intent.putExtra("webUrl", text);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
