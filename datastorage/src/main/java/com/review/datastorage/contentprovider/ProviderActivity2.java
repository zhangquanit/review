package com.review.datastorage.contentprovider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.review.datastorage.R;

/**
 * 本应用内容使用ContetnProvider
 * @author 张全
 */

public class ProviderActivity2 extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provider);
        findViewById(R.id.btn_registresolver).setOnClickListener(this);
        findViewById(R.id.btn_insert).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Uri uri = TestContract.TestEntry.CONTENT_URI;
        String _id = TestContract.TestEntry._ID;
        String name = TestContract.TestEntry.COLUMN_NAME;
        switch (v.getId()) {
            case R.id.btn_registresolver:
                getContentResolver().registerContentObserver(
                        uri,
                        true,
                        contentObserver);
                break;
            case R.id.btn_insert:
                ContentValues contentValues = new ContentValues();
                contentValues.put(_id, System.currentTimeMillis());
                contentValues.put(name, "zhangsan");
                Uri insert = getContentResolver().insert(uri, contentValues);
                System.out.println("insert=" + insert);
                break;
            case R.id.btn_update:
                contentValues = new ContentValues();
                contentValues.put(name, "lisi");
                int update = getContentResolver().update(uri, contentValues, name + "=?", new String[]{"zhangsan"});
                System.out.println("update=" + update);
                break;
            case R.id.btn_delete:
                int delete = getContentResolver().delete(uri, name + "=?", new String[]{"lisi"});
                System.out.println("delete=" + delete);
                break;
            case R.id.btn_query:
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                while (cursor.moveToNext()) {
                    String id = cursor.getString(cursor.getColumnIndex(_id));
                    String nameValue = cursor.getString(cursor.getColumnIndex(name));
                    System.out.println("id=" + id + ",name=" + nameValue);
                }
                cursor.close();


                break;

        }
    }

    ContentObserver contentObserver = new ContentObserver(new Handler(Looper.getMainLooper())) {
        @Override
        public boolean deliverSelfNotifications() {
            System.out.println("ProviderActivity2 deliverSelfNotifications");
            return super.deliverSelfNotifications();
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            System.out.println("ProviderActivity2 onChange, selfChange=" + selfChange);
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange, uri);
            System.out.println("ProviderActivity2 onChange,selfChange=" + selfChange + ",uri=" + uri);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(contentObserver);
    }
}
