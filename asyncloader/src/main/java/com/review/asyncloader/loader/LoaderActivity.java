package com.review.asyncloader.loader;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import com.review.asyncloader.R;

import static com.review.asyncloader.R.layout.loader;

/**
 * @author 张全
 */

public class LoaderActivity extends Activity implements View.OnClickListener {
    Uri uri = Uri.parse("content://com.review.provider/person");
    String _id = "_id";
    String name = "name";
    private final int id_loader1 = 1;
    private final int id_loader2 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(loader);
        findViewById(R.id.btn_initloader).setOnClickListener(this);
        findViewById(R.id.btn_restartLoader).setOnClickListener(this);
        findViewById(R.id.btn_destroyLoader).setOnClickListener(this);
        findViewById(R.id.btn_asyncloader).setOnClickListener(this);
        findViewById(R.id.btn_asyncloader_change).setOnClickListener(this);
        findViewById(R.id.btn_asyncloader_destory).setOnClickListener(this);

        /**
         * 当手机状态发生改变比如旋转时，Activity会重新启动。
         * 但是LoaderManager只有一个但实例，Loader的生命周期是是由LoaderManager控制的，不会重新创建
         */
        LoaderManager loaderManager = getLoaderManager();
        Loader<Object> loader1 = loaderManager.getLoader(id_loader1);
        Loader<Object> loader2 = loaderManager.getLoader(id_loader2);
        System.out.println("onCreate ");
        System.out.println("loaderManager=" + loaderManager);
        System.out.println("id_loader1=" + loader1);
        System.out.println("id_loader2=" + loader2);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("onRestoreInstanceState");

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        System.out.println("onSaveInstanceState");

        LoaderManager loaderManager = getLoaderManager();
        Loader<Object> loader1 = loaderManager.getLoader(id_loader1);
        Loader<Object> loader2 = loaderManager.getLoader(id_loader2);
        System.out.println("onCreate ");
        System.out.println("loaderManager=" + loaderManager);
        System.out.println("id_loader1=" + loader1);
        System.out.println("id_loader2=" + loader2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_initloader:
                Bundle bundle = new Bundle();
                bundle.putString("initKey", "initValue");
                Loader loader = getLoaderManager().initLoader(id_loader1, bundle, loaderCallbacks);
                /**
                 * 1、如果该id对应的loader未被加载过，则会回调loaderCallbacks的onCreateLoader方法创建loader，loader开始加载数据，
                 * 数据加载完成后，回调onLoadFinished方法，通过cursor获取数据。
                 * 2、如果该id对应的loader已经加载过，只会回调loaderCallbacks.onLoadFinished，并且cursor不会重新加载数据
                 */
                break;
            case R.id.btn_restartLoader:
                bundle = new Bundle();
                bundle.putString("restartKey", "restartValue");
                getLoaderManager().restartLoader(id_loader1, bundle, loaderCallbacks);
                /**
                 * 每次调用都会回调onCreateLoader和onLoadFinished，并且cusor中有数据
                 */
                break;
            case R.id.btn_destroyLoader:
                getLoaderManager().destroyLoader(id_loader1);
                /**
                 * 1、如果该id对应的loader已经加载过，则会回调loaderCallbacks.onLoaderReset
                 * 退出Activity时，LoaderManager会自动销毁该loader，回调onLoaderReset
                 * 2、如果该id对应的loader未被加载过，则不会进行任何回调
                 */
                break;
            case R.id.btn_asyncloader:
                bundle = new Bundle();
                bundle.putString("key", "http://www.baidu.com");
                getLoaderManager().restartLoader(id_loader2, bundle, asyncTaskCallBack);
                break;
            case R.id.btn_asyncloader_destory:
                getLoaderManager().destroyLoader(id_loader2);
                break;
            case R.id.btn_asyncloader_change:
                sendBroadcast(new Intent(MyAsyncTaskLoader.ACTION));
                break;

        }
    }

    /**
     * 方法回调都是运行在主线程中的
     */
    LoaderManager.LoaderCallbacks loaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            System.out.println("onCreateLoader,id=" + id + ",args=" + args + ",thread=" + Thread.currentThread().getName());

            MyCursorLoader cursorLoader = new MyCursorLoader(LoaderActivity.this, uri, null, null, null, null);
            return cursorLoader;
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            System.out.println("onLoadFinished,thread=" + Thread.currentThread().getName());
            while (data.moveToNext()) {
                String id = data.getString(data.getColumnIndex(_id));
                String nameValue = data.getString(data.getColumnIndex(name));
                System.out.println("id=" + id + ",name=" + nameValue);
            }
//            data.close(); //不要手动关闭Cursor
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
            System.out.println("onLoaderReset,loader=" + loader + ",id=" + loader.getId() + ",thread=" + Thread.currentThread().getName());
        }
    };

    LoaderManager.LoaderCallbacks asyncTaskCallBack = new LoaderManager.LoaderCallbacks<Person>() {

        @Override
        public Loader<Person> onCreateLoader(int id, Bundle args) {
            System.out.println("AsyncTaskLoader  onCreateLoader,id=" + id + ",args=" + args);
            String value = args.getString("key");
            MyAsyncTaskLoader myAsyncTaskLoader = new MyAsyncTaskLoader(LoaderActivity.this, value);
            return myAsyncTaskLoader;
        }

        @Override
        public void onLoadFinished(Loader<Person> loader, Person data) {
            System.out.println("AsyncTaskLoader  onLoadFinished");
            System.out.println(data);
        }

        @Override
        public void onLoaderReset(Loader<Person> loader) {
            System.out.println("AsyncTaskLoader  onLoaderReset");
        }
    };

}
