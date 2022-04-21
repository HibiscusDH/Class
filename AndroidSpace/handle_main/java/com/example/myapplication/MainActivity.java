package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    MyThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (thread != null) return;
                thread = new MyThread();
                thread.start();
            }
        });
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            Handler handler = new Handler(getMainLooper()){
                @Override
                public void handleMessage(@NonNull Message msg) {
                    TextView tv_time = findViewById(R.id.tv_time);
                    tv_time.setText(msg.what+"");
                }
            };
            for (int i=0;i<100;i++){
                Message msg = handler.obtainMessage();
                msg.what = i;
                handler.sendMessage(msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}