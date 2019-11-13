package com.arke.sdk;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.concurrent.CountDownLatch;

import timber.log.Timber;


public class HomeActivity extends AppCompatActivity {


    private static String innerHTML;
    private static AlertDialog dialog;
    private WebView webview;
    private boolean loadingFinished = true;
    private CountDownLatch loadingFinishedLatch = new CountDownLatch(1);
    private String targetUrl = "";
//    private ProgressBar progressBar;
    private TextView progressBar;
    private Button refreshBtn;
    ConstraintLayout rukkaLogo;



    /**
     * Toast.
     */
    private Toast toast;
    private String screenshotDir;
    private String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Init toast
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);

        // Init alert dialog
        dialog = new AlertDialog.Builder(this)
                .setNegativeButton(getString(R.string.cancel), null)
                .setCancelable(false)
                .create();
        // create a folder to store logo
        createFolder("/RukkabetAgent/assets/screenshots");
//        initialize web view
        webview = (WebView) findViewById(R.id.homeWebView);
//        initialize progress bar
        progressBar = (TextView) findViewById(R.id.progressBar);
        refreshBtn = (Button) findViewById(R.id.refreshWebNavBtn);
        rukkaLogo = (ConstraintLayout) findViewById(R.id.loaderConstraint);
//        set target url
        setTargetUrl("https://mobile.rukkabet.com");
//        load url
        loadUrlToWebview(this.targetUrl);
    }

    private void createFolder(String fname) {
        String myfolder = Environment.getExternalStorageDirectory() + "/" + fname;
        File f = new File(myfolder);
        if (!f.exists()) {
            if (!f.mkdirs()) {
                Log.d("Storage path created", myfolder);
            }
        }
    }

    private void loadUrlToWebview(String url) {
        setLoadingFinished(false);

        webview.setWebViewClient(new HomeActivity.MyWebViewClient());
        webview.setInitialScale(100);
        webview.clearCache(true);
        webview.clearHistory();
        webview.setVerticalScrollBarEnabled(false);
        webview.setHorizontalScrollBarEnabled(false);
        webview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webview.getSettings().setDefaultTextEncodingName("utf-8");
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.postDelayed(new Runnable() {

            @Override
            public void run() {
                webview.loadUrl(url);
            }
        }, 500);
//        webview.loadUrl(url);

        // TODO process finish
        webview.setWebViewClient(new HomeActivity.MyWebViewClient());

        webview.setWebChromeClient(new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage cm) {
                Timber.d(cm.message() + " -- From line "
                        + cm.lineNumber() + " of "
                        + cm.sourceId());
                return true;
            }
        });
    }

    //    set url
    private void setTargetUrl(String url){
        this.targetUrl = url;
    }

    public void startScan(View view) {
        Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
    }


    //    custom WebViewClient class
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            String newUrl = request.getUrl().toString();
            Log.d("WebView", "Override Url Loading "+newUrl);
            setLoadingFinished(false);
            view.postDelayed(new Runnable() {

                @Override
                public void run() {
                    view.loadUrl(newUrl);
                }
            }, 500);
//            view.loadUrl(newUrl);
//            Toast.makeText(HomeActivity.this, "Override Url Loading", Toast.LENGTH_SHORT).show();
            return true;
        }

        @Override
        public void onPageStarted(
                WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
//            Toast.makeText(HomeActivity.this, "On Page Started", Toast.LENGTH_SHORT).show();
            Log.d("WebView", "On Page Started- "+url);
            setLoadingFinished(false);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            setLoadingFinished(true);
            setTargetUrl(url);
//            capture screen shot
//            captureScreenshot(view);
//            grab html code from webview
            view.evaluateJavascript(
                    "(function() { return ('<html>'+document.getElementById('content').outerHTML+'</html>'); })();",
                    new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String html) {
                            Log.d("HTML", html);
                            rukkaLogo.setVisibility(View.GONE);
                            HomeActivity.innerHTML = html;
                        }
                    });
//            Toast.makeText(HomeActivity.this, "On Page Finished", Toast.LENGTH_SHORT).show();
            Log.d("WebView", "On Page Finished- "+url);
        }
    }



    private void setLoadingFinished(boolean finished) {
        this.loadingFinished = finished;
        if (this.loadingFinished) {
            loadingFinishedLatch.countDown();
//            hide progress bar show refresh btn
            progressBar.setVisibility(View.GONE);
            refreshBtn.setVisibility(View.VISIBLE);
        }else{
//            show progress bar hide refresh btn
            progressBar.setVisibility(View.VISIBLE);
            refreshBtn.setVisibility(View.GONE);
        }
    }



    //    refresh page
    public void refreshPage(View view){
//        load url
        loadUrlToWebview(this.targetUrl);
    }



    //    nav back page
    public void webviewGoBack(View view){
        if(webview.canGoBack()){
            webview.goBack();
        }
    }

    //    nav back page
    public void webviewGoForward(View view){
        if(webview.canGoForward()){
            webview.goForward();
        }
    }

//    print page
    public void webviewPrint(View view){
//        open dialog and request for slip code
        LayoutInflater layoutInflater = LayoutInflater.from(HomeActivity.this);
        View promptView = layoutInflater.inflate(R.layout.request_slip_code_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HomeActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText slipCode = (EditText) promptView.findViewById(R.id.editTextTemp);

        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Validate", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String code = slipCode.getText().toString().trim();
                        SlipValidator slipValidator = new SlipValidator(HomeActivity.this);
                        slipValidator.validateSlipThenPrint(code);
//                        if(code.length() > 0) {
//                            SlipValidator slipValidator = new SlipValidator(HomeActivity.this);
//                            slipValidator.validateSlipThenPrint(code);
//                        }else{
//                            Toast.makeText(HomeActivity.this, "Invalid Slip Code", Toast.LENGTH_SHORT).show();
//                        }
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @Override
    public void onBackPressed(){
        if(webview.canGoBack()){
            webview.goBack();
        }else{
            super.onBackPressed();
        }
    }
}
