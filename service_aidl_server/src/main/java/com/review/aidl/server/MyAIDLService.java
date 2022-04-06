package com.review.aidl.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.review.aidl.server.bean.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张全
 */

public class MyAIDLService extends Service {
    public final static String TAG = "MyService";

    /**
     * MyAIDL.Stub
     */
    private MyAIDL.Stub binder = new MyAIDL.Stub() {
        Person inputPerson;

        @Override
        public String getInfor(String s) throws RemoteException {
            Log.d(TAG, "MyAIDLService getInfor()->s=" + s + ",this=" + this);
            return "我是 aidl 接口返回的字符串";
        }

        @Override
        public String greet(Person person) throws RemoteException {
            this.inputPerson = person;
            Log.d(TAG, "MyAIDLService greet()->person=" + person + ",this=" + this);
            return "greet接口调用成功";
        }

        @Override
        public List<Person> getPerson() throws RemoteException {
            Log.d(TAG, "MyAIDLService etPerson()");
            List<Person> persons = new ArrayList<>();
            Person person = new Person();
            person.setId(3);
            person.setName("王五");
            person.setGender("人妖");
            persons.add(person);
            persons.add(inputPerson);
            return persons;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "MyAIDLService onBind(),binder=" + binder);
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "MyAIDLService onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "MyAIDLService onStartCommand()");
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MyAIDLService onDestroy()");
    }
}
