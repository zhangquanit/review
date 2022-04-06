package com.review.datastorage.contentprovider;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * TestContract
 * Created by 90Chris on 2016/5/1.
 */
public class TestContract {

    protected static final String CONTENT_AUTHORITY = "com.review.provider";
    protected static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final class TestEntry implements BaseColumns {
        public static final String TABLE_NAME = "person";
        public static final String COLUMN_NAME = "name";
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        protected static Uri buildUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }


    }
}
