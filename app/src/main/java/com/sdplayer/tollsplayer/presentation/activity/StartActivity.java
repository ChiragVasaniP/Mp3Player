package com.sdplayer.tollsplayer.presentation.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.sdplayer.tollsplayer.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        findViewById(R.id.txt_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this, MainActivity.class));

            }
        });

      findViewById(R.id.txt_praicy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this, PrivacyActivity.class));

            }
        });

        findViewById(R.id.txt_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textToShare = "Hello, I'm sharing this from my Android app!";
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
                Intent chooser = Intent.createChooser(shareIntent, "Share Via");
                startActivity(chooser);
            }
        });

        findViewById(R.id.txt_rate_us).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("market://details?id=" + getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    Uri webUri = Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName());
                    Intent goToWebMarket = new Intent(Intent.ACTION_VIEW, webUri);
                    startActivity(goToWebMarket);
                }
            }
        });

    }
}