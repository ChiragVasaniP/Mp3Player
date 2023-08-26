package com.sdplayer.tollsplayer.presentation.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.sdplayer.tollsplayer.R;

public class SplashActivity extends AppCompatActivity {

    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    String[] permission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            permission = new String[]{Manifest.permission.READ_MEDIA_AUDIO};
        }else{
            permission = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        }

        // Check if the WRITE_EXTERNAL_STORAGE permission is granted
        if (isWriteStoragePermissionGranted()) {
            // Permission is already granted, proceed to the main activity
            jumptoNextActivity();
        } else {
            // Permission is not granted, request it from the user
            requestWriteStoragePermission();
        }
    }

    // Check if the WRITE_EXTERNAL_STORAGE permission is granted
    private boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_AUDIO)
                    == PackageManager.PERMISSION_GRANTED;
        }else{
            return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED;
        }

    }

    // Request WRITE_EXTERNAL_STORAGE permission
    private void requestWriteStoragePermission() {
        ActivityCompat.requestPermissions(this,
                permission,
                REQUEST_WRITE_EXTERNAL_STORAGE);
    }

    // Handle permission request results
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed to the main activity
                jumptoNextActivity();
            } else {
                // Permission denied, show an alert dialog
                showPermissionAlertDialog();
            }
        }
    }

    // Show an alert dialog explaining the need for permission
    private void showPermissionAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permission Required")
                .setMessage("This app requires WRITE_EXTERNAL_STORAGE permission to function properly. Please grant the permission in the settings.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Request permission again
                        requestWriteStoragePermission();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle the user's cancellation here, you can exit the app or take other actions
                        finish();
                    }
                })
                .create()
                .show();
    }

    private void jumptoNextActivity(){
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity. this, StartActivity.class));
                finish();
            }
        },2000);
    }
}
