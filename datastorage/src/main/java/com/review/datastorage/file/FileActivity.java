package com.review.datastorage.file;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentActivity;

import com.review.datastorage.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.functions.Consumer;


/**
 * @author 张全
 */

public class FileActivity extends FragmentActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file);
        findViewById(R.id.btn_appfile).setOnClickListener(this);
        findViewById(R.id.btn_internalFile).setOnClickListener(this);
        findViewById(R.id.btn_externalFile).setOnClickListener(this);
        findViewById(R.id.btn_fileprovider).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_appfile:
                getAPPFile();
                break;
            case R.id.btn_internalFile:
                getIntervalFile();
                break;
            case R.id.btn_externalFile:
                getExternalFile();
                break;
            case R.id.btn_fileprovider:
                startDownload();
                break;
        }
    }

    private void getAPPFile() {
        String packageCodePath = getPackageCodePath();//  /data/app/com.review.datastorage-1/base.apk
        System.out.println("getPackageCodePath=" + packageCodePath);

        //获取该程序的安装包路径
        String packageResourcePath = getPackageResourcePath();// /data/app/com.review.datastorage-1/base.apk
        System.out.println("getPackageResourcePath=" + packageResourcePath);

        String callingPackage = getCallingPackage();// com.review.datastorage
        System.out.println("getCallingPackage=" + callingPackage);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            File codeCacheDir = getCodeCacheDir();
            //   /data/data/com.review.datastorage/code_cache
            System.out.println("getCodeCacheDir=" + codeCacheDir);
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            File dataDir = getDataDir();
            System.out.println("getDataDir=" + dataDir);
        }
        File obbDir = getObbDir();
        // /storage/sdcard/Android/obb/com.review.datastorage
        System.out.println("getObbDir=" + obbDir);
/**
 --------------------小米3
 getPackageCodePath=/data/app/com.review.datastorage-1/base.apk
 getPackageResourcePath=/data/app/com.review.datastorage-1/base.apk
 getCallingPackage=com.review.datastorage
 getCodeCacheDir=/data/user/0/com.review.datastorage/code_cache
 getObbDir=/storage/emulated/0/Android/obb/com.review.datastorage
 */
    }

    private void getIntervalFile() {

        File filesDir = getFilesDir();
        File file = new File(filesDir, "one.txt");
        writeToFile(file, "你好");
        System.out.println("getFilesDir=" + file);

        //创建子目录
        filesDir = new File(filesDir, "zhangquanit");
        if (!filesDir.exists()) filesDir.mkdirs();
        file = new File(filesDir, "one.txt");
        writeToFile(file, "你好");
        System.out.println("getFilesDir[zhangquanit]=" + file);

        File cacheDir = getCacheDir();
        file = new File(cacheDir, "two.txt");
        writeToFile(file, "你好");
        System.out.println("getCacheDir=" + file);

        File externalCacheDir = getExternalCacheDir();
        file = new File(externalCacheDir, "one.txt");
        writeToFile(file, "你好");
        System.out.println(file.length());
        System.out.println("getExternalCacheDir=" + file);

        File externalFilesDir = getExternalFilesDir(null);
        file = new File(externalFilesDir, "one.txt");
        writeToFile(file, "你好");
        System.out.println("getExternalFilesDir=" + file);

        externalFilesDir = getExternalFilesDir("zhagnquanit");
        file = new File(externalFilesDir, "one.txt");
        writeToFile(file, "你好");
        System.out.println("getExternalFilesDir(\"zhagnquanit\")=" + file);

/**
 getFilesDir=/data/data/com.review.datastorage/files/one.txt
 getFilesDir[zhangquanit]=/data/data/com.review.datastorage/files/zhangquanit/one.txt
 getCacheDir=/data/data/com.review.datastorage/cache/two.txt
 getExternalCacheDir=/storage/sdcard/Android/data/com.review.datastorage/cache/one.txt
 getExternalFilesDir=/storage/sdcard/Android/data/com.review.datastorage/files/one.txt
 getExternalFilesDir("zhagnquanit")=/storage/sdcard/Android/data/com.review.datastorage/files/zhagnquanit/one.txt
 */

    }

    private void getExternalFile() {
        boolean hasSDCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        System.out.println("hasSDCard=" + hasSDCard);

        boolean externalStorageEmulated = Environment.isExternalStorageEmulated();
        System.out.println("isExternalStorageEmulated=" + externalStorageEmulated);

        boolean externalStorageRemovable = Environment.isExternalStorageRemovable();
        System.out.println("isExternalStorageRemovable=" + externalStorageRemovable);

        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        System.out.println("getExternalStorageDirectory=" + externalStorageDirectory);

        File dataDirectory = Environment.getDataDirectory();
        System.out.println("getDataDirectory=" + dataDirectory);

        File downloadCacheDirectory = Environment.getDownloadCacheDirectory();
        System.out.println("getDownloadCacheDirectory=" + downloadCacheDirectory);

        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        System.out.println("getExternalStoragePublicDirectory=" + externalStoragePublicDirectory);

        File rootDirectory = Environment.getRootDirectory();
        System.out.println("getRootDirectory=" + rootDirectory);
/**
 * ------------------模拟器
 hasSDCard=true
 isExternalStorageEmulated=false
 isExternalStorageRemovable=false
 getExternalStorageDirectory=/storage/sdcard
 getDataDirectory=/data
 getDownloadCacheDirectory=/cache
 getExternalStoragePublicDirectory=/storage/sdcard/Movies
 getRootDirectory=/system

 */

/**
 * ------------------小米3
 hasSDCard=true
 isExternalStorageEmulated=true
 isExternalStorageRemovable=false
 getExternalStorageDirectory=/storage/emulated/0
 getDataDirectory=/data
 getDownloadCacheDirectory=/cache
 getExternalStoragePublicDirectory=/storage/emulated/0/Movies
 getRootDirectory=/system

 */

    }

    private void writeToFile(File file, String content) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(content.getBytes("utf-8"));
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //------------------------file provider----------------------------------
    private static final String FILE="update.apk";
    private void startDownload(){
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        System.out.println("granted=" + granted);
                        doDownload();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
    private void doDownload(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getFile().delete();
                System.out.println("开始下载");
                download();
                System.out.println("下载完毕,size="+getFile().length());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        installAPK();
                    }
                });
            }
        }).start();
    }
    private  File getFile(){
//        File imagePath =  new File(Environment.getExternalStorageDirectory()+File.separator+"apks");
        File imagePath = new File(getFilesDir(), "files");
        if(!imagePath.exists())imagePath.mkdirs();
        File newFile = new File(imagePath,FILE);

        return  newFile;
    }
    private void download(){
        InputStream inputStream = null;
        DataOutputStream outputStream = null;
        try {
            URL url = new URL("https://www.wolaibao.com/apk/wolaibao_anzhi_sign.apk");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(10*1000);
            conn.setReadTimeout(10*1000);
            conn.setDoInput(true);
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK
                    || responseCode == HttpURLConnection.HTTP_PARTIAL) {

                inputStream = conn.getInputStream();
                FileOutputStream fos=new FileOutputStream(getFile());
                outputStream = new DataOutputStream(fos);
                byte[] buffer = new byte[1024 * 10];
                int readSize = 0;
                while ((readSize = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, readSize);
                }
                outputStream.flush();
            }
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (null != outputStream) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void installAPK(){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        Uri uri=Uri.fromFile(getFile());
        //7.0上需要文件共享
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(this, getString(R.string.file_authority), getFile());
            System.out.println(uri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }


        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        startActivity(intent);
    }

}
