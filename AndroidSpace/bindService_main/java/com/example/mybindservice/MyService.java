package com.example.mybindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    class MyBinder extends Binder {
        public void play() {
            MyService.this.play();
        }
    }

    private void play() {
        Log.v("MyTest", "Play music");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v("MyTest", "onBind");
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("MyTest", "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("MyTest", "onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v("MyTest", "onUnbind");
        return super.onUnbind(intent);
    }
}