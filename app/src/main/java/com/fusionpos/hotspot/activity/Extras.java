package com.fusionpos.hotspot.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.fusionpos.hotspot.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import im.delight.android.webview.AdvancedWebView;

public class Extras extends AppCompatActivity {


    AdvancedWebView webView;
    Bundle bundle;
    String extraurl;
    BottomNavigationView navigation;

    ProgressDialog loader;
    RelativeLayout loadimgrel;
    ImageView loadimg;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_extras);

        //hind actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        loadimgrel = findViewById(R.id.loadimgrel);
        loadimg = findViewById(R.id.loadimg);
        loadimg.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate));

        // loader = ProgressDialog.show(Extras.this, "", "Loading...", true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadimgrel.setVisibility(View.GONE);
                loadimg.setVisibility(View.GONE);
                // loader.dismiss();
            }
        }, 5000);

        Bundle bundle = getIntent().getExtras();
        extraurl = bundle.getString("extraurl");
        //getting bottom navigation view and attaching the listener
        navigation = findViewById(R.id.bottom_navigation);

        webView = findViewById(R.id.webview);


        webView.clearCache(true);
        webView.clearHistory();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Android 4.1.1; Galaxy Nexus Build/JRO03C) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");
        webView.getSettings().setUserAgentString("Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.116 Safari/537.36 Mozilla/5.0 (iPad; U; CPU OS 3_2 like Mac OS X; en-us) AppleWebKit/531.21.10 (KHTML, like Gecko) Version/4.0.4 Mobile/7B334b Safari/531.21.10");

        webView.loadUrl(extraurl);
        Log.e("WebViewstr", "g.." + extraurl);


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.d("WebView", "your current url when webpage loading.." + url);
                if (url.equalsIgnoreCase(getString(R.string.reviewurl))) {
                    navigation.getMenu().findItem(R.id.page_review).setChecked(true);
                } else if (url.equalsIgnoreCase(getString(R.string.orderurl))) {
                    navigation.getMenu().findItem(R.id.page_order).setChecked(true);
                } else if (url.equalsIgnoreCase(getString(R.string.baseurl))) {
                    navigation.getMenu().findItem(R.id.page_home).setChecked(true);
                } else {
                    navigation.getMenu().findItem(R.id.page_more).setChecked(true);
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.d("WebView", "your current url when webpage loading.. finish" + url);
                super.onPageFinished(view, url);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onLoadResource(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("WebView", "when you click on any interlink on webview that time you got url :" + url);
                System.out.println("when you click on any interlink on webview that time you got url :-" + url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });


        /*String webUrl = webView.getUrl();

        Toast.makeText(getApplicationContext(), webUrl, Toast.LENGTH_SHORT).show();*/


        // navigation.setOnNavigationItemSelectedListener(this);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.page_home:
                        Intent intenthome = new Intent(getApplicationContext(), Home.class);
                        startActivity(intenthome);
                        //Toast.makeText(getApplicationContext(), "Recents", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.page_order:
                        Intent intentorder = new Intent(getApplicationContext(), Order.class);
                        startActivity(intentorder);
                        //Toast.makeText(getApplicationContext(), "Favorites", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.page_review:
                        Intent intentreview = new Intent(getApplicationContext(), Review.class);
                        startActivity(intentreview);
                        //Toast.makeText(getApplicationContext(), "Nearby", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.page_more:
                        Intent intentmore = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intentmore);
                        //Toast.makeText(getApplicationContext(), "Nearby", Toast.LENGTH_SHORT).show();
                        break;

                }
                return true;
            }
        });

    }


}
