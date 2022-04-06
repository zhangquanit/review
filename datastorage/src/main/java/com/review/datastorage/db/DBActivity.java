package com.review.datastorage.db;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.review.datastorage.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 张全
 */

public class DBActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db);
        findViewById(R.id.btn_openDB).setOnClickListener(this);
        findViewById(R.id.btn_insert).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_openDB:
                openOrCreateDBFile();
                break;
            case R.id.btn_insert:
                ArrayList<User> users = new ArrayList<>();
                for (int i = 1; i <=5; i++) {
                    User user = new User();
                    user.setName("张三" + i);
                    user.setAge(20 + i);
                    user.setSalery((float) (100 + i * 0.1));
                    users.add(user);
                }
                MyDBOpenHelper.insert(this, users);
                break;
            case R.id.btn_update:
                User user = new User();
                user.setId(1);
                user.setName("张三11");
                user.setAge(30);
                user.setSalery(10000.1f);
                MyDBOpenHelper.update(this, user);
                break;
            case R.id.btn_delete:
                user = new User();
                user.setId(1);
                user.setAge(30);
                user.setSalery(10000.1f);
                MyDBOpenHelper.delete(this, user);
                break;
            case R.id.btn_query:
                List<User> userList = MyDBOpenHelper.query(this);
                System.out.println(userList);

                user=new User();
                user.setId(1);
                user.setName("张三1");
                userList = MyDBOpenHelper.queryByUser(this,user);
                System.out.println("queryByUser="+userList);
                break;
        }
    }

    /**
     * 打开已有的数据库
     * 开发中有时需要将预先创建好的数据库db文件随apk一起发布，然后应用中 SQLiteDatabase.openOrCreateDatabase(dbPath, null);
     */
    private void openOrCreateDBFile() {

        //获取db文件，由openOrCreateDatabase(String, int, SQLiteDatabase.CursorFactory)创建的
        File dataDB = getDatabasePath(MyDBOpenHelper.DB_NAME);
        System.out.println("getDatabasePath,databasePath="+dataDB);
        //   /data/user/0/com.review.datastorage/databases/test.db
        System.out.println(dataDB.exists());

        //拷贝到sd卡
         dataDB=new File(Environment.getExternalStorageDirectory(),MyDBOpenHelper.DB_NAME);
        try {
            InputStream inputStream = getAssets().open("test.db");
            FileOutputStream fileOutputStream = new FileOutputStream(dataDB);
            byte[] buffer=new byte[1024];
            int len=-1;
            while((len=inputStream.read(buffer))!=-1){
                fileOutputStream.write(buffer,0,len);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            inputStream.close();
            System.out.println(dataDB.length());

        } catch (IOException e) {
            e.printStackTrace();
        }


        //删除数据库相关信息
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
//            boolean successful = SQLiteDatabase.deleteDatabase(dbFile);
        }
        /**
         * SQLiteDatabase.OPEN_READONLY   以只读方式打开数据库
         * SQLiteDatabase.OPEN_READWRITE  以读写方式打开数据库
         * SQLiteDatabase.CREATE_IF_NECESSARY 如果应用的数据库不存在，则创建数据库
         */

        //打开外部数据库
        SQLiteDatabase sqLiteDatabase = null;
        sqLiteDatabase = SQLiteDatabase.openDatabase(dataDB.getAbsolutePath(), null, SQLiteDatabase.OPEN_READWRITE);
        System.out.println("openDatabase,sqLiteDatabase="+sqLiteDatabase);

        //打开或创建(不存在的话)外部数据库
        sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dataDB, null);
        //等价于SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        System.out.println("openOrCreateDatabase,sqLiteDatabase="+sqLiteDatabase);
    }


}
