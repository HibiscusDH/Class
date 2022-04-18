package com.example.myapplication;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.widget.Toast;

public class MyContentProvider extends ContentProvider {
    private PersonDBOpenHelper helper;

    static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    static final int PERSON = 1;
    static final int PERSON_ID = 3;


    static {
        matcher.addURI("com.example.myapplication", "/person", PERSON);
        matcher.addURI("com.example.myapplication", "/person/#", PERSON_ID);
    }

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int code = matcher.match(uri);
        SQLiteDatabase db = null;
        switch (code){
            case PERSON:
                db = helper.getWritableDatabase();
                int num = db.delete("info", selection, selectionArgs);
                db.close();
                getContext().getContentResolver().notifyChange(uri, null);
                return num;
            case PERSON_ID:
                db = helper.getWritableDatabase();
                long id = ContentUris.parseId(uri);
                int num1 = db.delete("info", "id=?", new String[]{id + ""});
                db.close();
                getContext().getContentResolver().notifyChange(uri, null);
                return num1;
            default:
                Toast.makeText(getContext(), "路径错！！！", Toast.LENGTH_LONG).show();
                break;
        }
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int code = matcher.match(uri);

        switch (code){
            case PERSON:
                SQLiteDatabase db = helper.getWritableDatabase();
                long id = db.insert("info", null, values);
                db.close();
                getContext().getContentResolver().notifyChange(uri, null);
                Uri u = ContentUris.withAppendedId(uri, id);
                return u;
            default:
                Toast.makeText(getContext(), "路径错！！！", Toast.LENGTH_LONG).show();
                break;
        }

        return null;
    }

    @Override
    public boolean onCreate() {
        helper = new PersonDBOpenHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        int code = matcher.match(uri);

        switch (code){
            case PERSON:
                SQLiteDatabase db = helper.getReadableDatabase();
                Cursor cursor = db.query("info", new String[]{"id", "name"},
                        null,
                        null,
                        null,
                        null,
                        null);

                return cursor;
            default:
                Toast.makeText(getContext(), "路径错！！！", Toast.LENGTH_LONG).show();
                break;
        }

        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int code = matcher.match(uri);
        SQLiteDatabase db = null;
        switch (code){
            case PERSON:
                db = helper.getWritableDatabase();
                int num = db.update("info", values, selection, selectionArgs);
                db.close();
                getContext().getContentResolver().notifyChange(uri, null);
                return num;
            case PERSON_ID:
                db = helper.getWritableDatabase();
                long id = ContentUris.parseId(uri);
                int num1 = db.update("info", values, "id=?", new String[]{id + ""});
                db.close();
                getContext().getContentResolver().notifyChange(uri, null);
                return num1;
            default:
                Toast.makeText(getContext(), "路径错！！！", Toast.LENGTH_LONG).show();
                break;
        }
        return 0;
    }
}