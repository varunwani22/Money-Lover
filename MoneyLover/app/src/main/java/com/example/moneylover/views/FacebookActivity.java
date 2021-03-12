package com.example.moneylover.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.example.moneylover.R;

public class FacebookActivity extends AppCompatActivity {
 private WebView mWebview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);
      mWebview = findViewById(R.id.WebView);
      mWebview.loadUrl("https://m.facebook.com/moneylover.me");
       mWebview.loadUrl("https://mobile.twitter.com/moneyloverapp");
       mWebview.loadUrl("https://play.google.com/apps/testing/com.bookmark.money");
    }
}