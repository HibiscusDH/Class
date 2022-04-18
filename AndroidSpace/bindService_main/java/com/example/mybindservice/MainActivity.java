package com.example.mybindservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MyService.MyBinder binder = null;
    MyConnection coon = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    void init() {
        Button btn_bind = findViewById(R.id.btn_bind);
        btn_bind.setOnClickListener(this);

        Button btn_call = findViewById(R.id.btn_call);
        btn_call.setOnClickListener(this);

        Button btn_unbind = findViewById(R.id.btn_unbind);
        btn_unbind.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bind:
                Intent intent = new Intent(MainActivity.this, MyService.class);
                if (coon == null) {
                    coon = new MyConnection();
                }
                bindService(intent, coon, BIND_AUTO_CREATE);
                break;
            case R.id.btn_call:
                if (binder != null) {
                    binder.play();
                }
                break;
            case R.id.btn_unbind:
                if (coon != null) {
                    unbindService(coon);
                    coon = null;
                }
                break;
        }
    }

    class MyConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MyService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

}
























