package com.review.asyncloader.loader;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;

/**
 * @author 张全
 */

public class MyCursorLoader extends CursorLoader {
    /**
      -----------initLoader
     02-07 04:49:31.931: I/System.out(8301): onCreateLoader,id=1,args=Bundle[{initKey=initValue}],thread=main
     02-07 04:49:31.931: I/System.out(8301): CursorLoader cancelLoad
     02-07 04:49:31.931: I/System.out(8301): CursorLoader onCancelLoad
     02-07 04:49:31.931: I/System.out(8301): CursorLoader onForceLoad
     02-07 04:49:31.931: I/System.out(8301): CursorLoader onStartLoading
     02-07 04:49:31.931: I/System.out(8301): CursorLoader cancelLoad
     02-07 04:49:31.931: I/System.out(8301): CursorLoader onCancelLoad
     02-07 04:49:31.931: I/System.out(8301): CursorLoader cancelLoadInBackground
     02-07 04:49:31.931: I/System.out(8301): CursorLoader onForceLoad
     02-07 04:49:31.935: I/System.out(8301): CursorLoader onCanceled
     02-07 04:49:31.936: I/System.out(8301): CursorLoader  onLoadInBackground ,thread=AsyncTask #1
     02-07 04:49:31.938: I/System.out(8301): CursorLoader  loadInBackground ,thread=AsyncTask #1
     02-07 04:49:31.948: I/System.out(8301): onLoadFinished,thread=main
     02-07 04:49:31.948: I/System.out(8301): id=1486437766240,name=zhangsan
     02-07 04:49:31.951: I/System.out(8301): CursorLoader deliverResult

     ------------------第二次initLoader
     02-07 04:50:07.939: I/System.out(8301): onLoadFinished,thread=main

     ----------------restartLoader
     如果该loader是第一次开启，则和init loader流程一样，如果该loader已经开启过，则先调用onAbandon，然后走initLoader流程
     02-07 04:51:44.003: I/System.out(8548): CursorLoader onAbandon
     02-07 04:51:44.003: I/System.out(8548): onCreateLoader,id=1,args=Bundle[{restartKey=restartValue}],thread=main
     02-07 04:51:44.003: I/System.out(8548): CursorLoader cancelLoad
     02-07 04:51:44.003: I/System.out(8548): CursorLoader onCancelLoad
     02-07 04:51:44.003: I/System.out(8548): CursorLoader onForceLoad
     02-07 04:51:44.003: I/System.out(8548): CursorLoader onStartLoading
     02-07 04:51:44.003: I/System.out(8548): CursorLoader cancelLoad
     02-07 04:51:44.003: I/System.out(8548): CursorLoader onCancelLoad
     02-07 04:51:44.003: I/System.out(8548): CursorLoader cancelLoadInBackground
     02-07 04:51:44.003: I/System.out(8548): CursorLoader onForceLoad
     02-07 04:51:44.008: I/System.out(8548): CursorLoader onCanceled
     02-07 04:51:44.011: I/System.out(8548): CursorLoader  onLoadInBackground ,thread=AsyncTask #2
     02-07 04:51:44.014: I/System.out(8548): CursorLoader  loadInBackground ,thread=AsyncTask #2
     02-07 04:51:44.022: I/System.out(8548): onLoadFinished,thread=main
     02-07 04:51:44.022: I/System.out(8548): id=1486437766240,name=zhangsan
     02-07 04:51:44.022: I/System.out(8548): CursorLoader cancelLoad
     02-07 04:51:44.022: I/System.out(8548): CursorLoader onCancelLoad
     02-07 04:51:44.022: I/System.out(8548): CursorLoader onStopLoading
     02-07 04:51:44.025: I/System.out(8548): CursorLoader onReset
     02-07 04:51:44.026: I/System.out(8548): CursorLoader deliverResult

     --------------------------destroyLoader
     02-07 04:48:53.427: I/System.out(8301): onLoaderReset,loader=MyCursorLoader{77d1b3a id=1},id=1,thread=main
     02-07 04:48:53.428: I/System.out(8301): CursorLoader cancelLoad
     02-07 04:48:53.428: I/System.out(8301): CursorLoader onCancelLoad
     02-07 04:48:53.428: I/System.out(8301): CursorLoader onStopLoading
     02-07 04:48:53.428: I/System.out(8301): CursorLoader onReset


     */
    public MyCursorLoader(Context context) {
        super(context);
    }

    public MyCursorLoader(Context context, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        super(context, uri, projection, selection, selectionArgs, sortOrder);
    }

    @Override
    protected void onAbandon() {
        super.onAbandon();
        System.out.println("CursorLoader onAbandon");
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        System.out.println("CursorLoader onForceLoad");
    }

    @Override
    public void onCanceled(Cursor data) {
        super.onCanceled(data);
        System.out.println("CursorLoader onCanceled");
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        System.out.println("CursorLoader onContentChanged");
    }

    @Override
    protected Cursor onLoadInBackground() {
        System.out.println("CursorLoader  onLoadInBackground ,thread="+Thread.currentThread().getName());
        return super.onLoadInBackground();
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        System.out.println("CursorLoader onStartLoading");

        forceLoad();//强制加载数据
    }
    @Override
    public Cursor loadInBackground() {
        System.out.println("CursorLoader  loadInBackground ,thread="+Thread.currentThread().getName());
        return super.loadInBackground();
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        System.out.println("CursorLoader onStopLoading");
    }

    @Override
    protected void onReset() {
        super.onReset();
        System.out.println("CursorLoader onReset");
    }

    @Override
    protected boolean onCancelLoad() {
        System.out.println("CursorLoader onCancelLoad");
        return super.onCancelLoad();
    }

    @Override
    public void deliverResult(Cursor data) {
        super.deliverResult(data);
        System.out.println("CursorLoader deliverResult");
    }

    @Override
    public boolean cancelLoad() {
        System.out.println("CursorLoader cancelLoad");
        return super.cancelLoad();
    }

    @Override
    public void cancelLoadInBackground() {
        System.out.println("CursorLoader cancelLoadInBackground");
        super.cancelLoadInBackground();
    }
}
