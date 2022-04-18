package com.example.myobserver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    MyObserver observer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri uri = Uri.parse("content://com.example.myapplication/person");
        ContentResolver resolver = getContentResolver();
        observer = new MyObserver(new Handler(), this);
        resolver.registerContentObserver(uri,true, observer);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (observer != null) {
            getContentResolver().unregisterContentObserver(observer);
        }
    }
}















