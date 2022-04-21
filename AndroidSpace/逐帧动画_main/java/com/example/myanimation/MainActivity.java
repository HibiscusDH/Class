package com.example.myanimation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Drawable[] images;

    Timer timer;
    TimerTask timerTask;
    Handler myHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        myHandler = new Handler(getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                ImageView imageView = findViewById(R.id.imageView);
                imageView.setBackground(images[msg.what]);
            }
        };

        timer = new Timer();
        timerTask = new TimerTask() {
            int index = 0;
            @Override
            public void run() {
                Message msg = myHandler.obtainMessage();
                msg.what = index;
                myHandler.sendMessage(msg);
                index = index + 1;
                index = index % images.length;
            }
        };
        timer.schedule(timerTask, 5, 100);
    }

    private void init() {
        int len = 0;
        try {
            len = getAssets().list("imgs").length;
            if(len == 0) return;
            images = new Drawable[len];
            for(int i = 0; i < len; i++){
                InputStream is = getAssets().open("imgs/rr_" + (i + 1) + ".png");
                images[i] = Drawable.createFromStream(is, null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer != null){
            timer.cancel();
        }
    }
}