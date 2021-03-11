package com.example.moneylover.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.moneylover.R;

public class AboutActivity extends AppCompatActivity {
    private TextView mtvFacebook, mtvTwitter,mtvGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        mtvGoogle=findViewById(R.id.tvGoogle);
        mtvGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutActivity.this, FacebookActivity.class);
                startActivity(intent);
            }
        });
        mtvTwitter=findViewById(R.id.tvTwitter);
        mtvTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutActivity.this, FacebookActivity.class);
                startActivity(intent);
            }
        });
        mtvFacebook =findViewById(R.id.tvFacebook);
        mtvFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutActivity.this, FacebookActivity.class);
                startActivity(intent);
            }
        });
    }
}