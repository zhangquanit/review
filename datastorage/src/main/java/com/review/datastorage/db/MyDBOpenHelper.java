package com.review.datastorage.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张全
 */

public class MyDBOpenHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "test.db";
    private static final int VERSION = 1;
    //user表字段
    private static final String TABLE_USER = "user";
    private static final String C_ID = "id";
    private static final String C_NAME = "name";
    private static final String C_AGE = "age";
    private static final String C_SALARY = "salery";

    public MyDBOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        System.out.println("MyDBOpenHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // onCreate只会调用一次
        System.out.println("MyDBOpenHelper  onCreate");

        StringBuffer sql = new StringBuffer();
        sql.append("create table " + TABLE_USER + "(")
                .append(C_ID + " integer primary key autoincrement,")
                .append(C_NAME + " varchar(10),")
                .append(C_AGE + " integer,")
                .append(C_SALARY + " number")
                .append(")");
        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("MyDBOpenHelper  onUpgrade,oldVersion="+oldVersion+",newVersion="+newVersion);
        if (oldVersion < 2) {
            String updateSql = "alter table " + TABLE_USER + " drop " + C_SALARY;
            db.execSQL(updateSql);
        }
    }

    public static SQLiteDatabase getDB(Context context) {
        return new MyDBOpenHelper(context).getWritableDatabase();// 如果数据库不存在，则会创建数据库
    }

    public static void insert(Context context, List<User> users) {
        SQLiteDatabase db = null;
        try {
            db = getDB(context);
            //事务提交  提高插入效率
            db.beginTransaction();
            for (User user : users) {
                /**
                 * String table,
                 * String nullColumnHack, 空列的默认值
                 * ContentValues values
                 */
                db.insert(TABLE_USER, null, toValues(user));
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            close(db, null);
        }
    }

    public static void update(Context context, User user) {
        SQLiteDatabase db = null;
        try {
            db = getDB(context);
            db.update(TABLE_USER, toValues(user), "id=?", new String[]{String.valueOf(user.getId())});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(db, null);
        }
    }

    public static void delete(Context context, User user) {
        SQLiteDatabase db = null;
        try {
            db = getDB(context);
            db.delete(TABLE_USER, "id=?", new String[]{String.valueOf(user.getId())});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(db, null);
        }
    }

    public static List<User> query(Context context) {
        List<User> users = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor c = null;
        try {
            db = getDB(context);
            c = db.rawQuery("select * from " + TABLE_USER, null);
            /**
             Cursor query (
             String table, //查询的表名
             String[] columns, //查询的字段、null表示查询所有的字段
             String selection, //查询条件，即where后的条件
             String[] selectionArgs, //占位符，替换查询条件中的？
             String groupBy, //按什么分组
             String having, //分组查询条件
             String orderBy //按什么排序
             )
             */
//            c = db.query(TABLE_USER, null, null, null, null, null, null);

            while (c.moveToNext()) {
                users.add(toUser(c));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(db, c);
        }
        return users;
    }

    public static List<User> queryByUser(Context context, User user) {
        List<User> users = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor c = null;
        try {
            db = getDB(context);
//            db.rawQuery("select * from " + TABLE_USER + " where " + C_ID + "=? and " + C_NAME + "=?", new String[]{String.valueOf(user.getId()), user.getName()});
            /**
             Cursor query (
             String table, //查询的表名
             String[] columns, //查询的字段、null表示查询所有的字段
             String selection, //查询条件，即where后的条件
             String[] selectionArgs, //占位符，替换查询条件中的？
             String groupBy, //按什么分组
             String having, //分组查询条件
             String orderBy //按什么排序
             )
             */
            c = db.query(TABLE_USER,
                    null,
                    C_ID+"=? and "+C_NAME+"=?",
                    new String[]{String.valueOf(user.getId()), user.getName()},
                    null,
                    null,
                    null);
            System.out.println("count="+c.getCount());
            while (c.moveToNext()) {
                users.add(toUser(c));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(db, c);
        }
        return users;
    }

    private static ContentValues toValues(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(C_NAME, user.getName());
        contentValues.put(C_AGE, user.getAge());
        contentValues.put(C_SALARY, user.getSalery());
        return contentValues;
    }

    private static User toUser(Cursor c) {
        User user = new User();
        user.setId(c.getInt(c.getColumnIndex(C_ID)));
        user.setName(c.getString(c.getColumnIndex(C_NAME)));
        user.setAge(c.getInt(c.getColumnIndex(C_AGE)));
        user.setSalery(c.getFloat(c.getColumnIndex(C_SALARY)));
        return user;
    }

    private static void close(SQLiteDatabase db, Cursor c) {
        try {
            if (null != db) db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (null != c) c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
