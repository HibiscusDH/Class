package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{
    Button btn_insert, btn_update, btn_delete, btn_query;
    TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_insert = findViewById(R.id.btn_insert);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);
        btn_query = findViewById(R.id.btn_query);
        tv_result = findViewById(R.id.tv_result);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver resolver = getContentResolver();
                Uri uri = Uri.parse("content://com.example.myapplication/person");
                ContentValues values = new ContentValues();
                values.put("name", "张三");
                resolver.insert(uri, values);
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver resolver = getContentResolver();
                Uri uri = Uri.parse("content://com.example.myapplication/person");
                ContentValues values = new ContentValues();
                values.put("name", "李四");
                resolver.update(uri, values, "id=?", new String[]{"3"});

                uri = Uri.parse("content://com.example.myapplication/person/4");
                resolver.update(uri, values, null, null);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver resolver = getContentResolver();
                Uri uri = Uri.parse("content://com.example.myapplication/person");

                resolver.delete(uri, "id=?", new String[]{"1"});

                uri = Uri.parse("content://com.example.myapplication/person/2");
                resolver.delete(uri, null, null);
            }
        });

        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver resolver = getContentResolver();
                Uri uri = Uri.parse("content://com.example.myapplication/person");
                Cursor cursor = resolver.query(uri, null,null,null,null);
                String s = "";
                while (cursor.moveToNext()){
                    @SuppressLint("Range") long id = cursor.getInt(cursor.getColumnIndex("id"));
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                    s = s + id + ", " + name + "\n";
                }
                cursor.close();
                tv_result.setText(s);
            }
        });

    }

}