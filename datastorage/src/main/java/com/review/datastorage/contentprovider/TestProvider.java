package com.review.datastorage.contentprovider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.Nullable;

/**
 *
 */
public class TestProvider extends ContentProvider {
    private final static int TEST = 100;
    static UriMatcher uriMatcher;
    private ContentResolver contentResolver;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(TestContract.CONTENT_AUTHORITY, TestContract.TestEntry.TABLE_NAME, TEST);
    }

    private TestDBHelper mOpenHelper;

    @Override
    public boolean onCreate() {
        System.out.println("TestProvider..onCreate");
        mOpenHelper = new TestDBHelper(getContext());
        contentResolver = getContext().getContentResolver();
        return true;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();

        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case TEST:
                cursor = db.query(TestContract.TestEntry.TABLE_NAME, projection, selection, selectionArgs, sortOrder, null, null);
//                cursor.setNotificationUri(contentResolver,uri);
                break;
        }

        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Uri returnUri;
        long _id;
        switch (uriMatcher.match(uri)) {
            case TEST:
                _id = db.insert(TestContract.TestEntry.TABLE_NAME, null, values);
                if (_id > 0) {
                    returnUri = TestContract.TestEntry.buildUri(_id);
                    contentResolver.notifyChange(returnUri,null); //通知有数据插入了
                } else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            default:
                throw new android.database.SQLException("Unknown uri: " + uri);
        }
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case TEST:
                count = db.delete(TestContract.TestEntry.TABLE_NAME, selection, selectionArgs);
                contentResolver.notifyChange(uri,null); //通知有数据删除了
                break;
        }
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case TEST:
                count = db.update(TestContract.TestEntry.TABLE_NAME, values, selection, selectionArgs);
                contentResolver.notifyChange(uri,null); //通知有数据更新了
                break;
        }
        return count;
    }

}
