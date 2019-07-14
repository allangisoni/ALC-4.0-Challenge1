package com.example.android.alcapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ALCAbout extends AppCompatActivity {
    @BindView(R.id.alc_webview)WebView alc_webview;
  //  ProgressDialog progDailog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alcabout);

        ButterKnife.bind(this);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //String url = "https://andela.com/alc";
        //Intent i = new Intent(Intent.ACTION_VIEW);
        //i.setData(Uri.parse(url));
        //startActivity(i);

       final ProgressDialog progDailog = new ProgressDialog(this);
       progDailog.setTitle("Loading");
       progDailog.setMessage("Please wait ......");
       progDailog.setCancelable(false);
       progDailog.show();


        alc_webview.getSettings().setJavaScriptEnabled(true);
        alc_webview.getSettings().setLoadsImagesAutomatically(true);
         alc_webview.setWebViewClient(new MyBrowser(){
             @Override
             public void onPageFinished(WebView view, String url) {
                 super.onPageFinished(view, url);
                 progDailog.dismiss();
             }
         });
         alc_webview.loadUrl("https://andela.com/alc");



    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



    private class MyBrowser extends WebViewClient {




        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
       //     progDailog.show();
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            // ignore ssl error
            if (handler != null){
                handler.proceed();
            } else {
                super.onReceivedSslError(view, null, error);
            }
        }


        @Override
        public void onPageFinished(WebView view, String url) {
          //  super.onPageFinished(view, url);
           // progDailog.dismiss();
        }
    }
}
