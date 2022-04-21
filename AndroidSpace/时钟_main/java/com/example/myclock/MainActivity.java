package com.example.myclock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Timer timer;
    TimerTask timerTask;
    Handler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myHandler = new Handler(getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                long time = System.currentTimeMillis();
                MyClock clock = findViewById(R.id.view);
                clock.setTime(time);
            }
        };

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                Message msg = myHandler.obtainMessage();
                myHandler.sendMessage(msg);
            }
        };
        timer.schedule(timerTask, 5, 500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer != null){
            timer.cancel();
        }
    }
}