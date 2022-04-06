package com.example.service_aidl_client

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.review.aidl.server.MyAIDL
import com.review.aidl.server.bean.Person
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_bindService.setOnClickListener(this)
        btn_callService.setOnClickListener(this)
        btn_callService2.setOnClickListener(this)
        btn_unbindService.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_bindService -> {
                val intent = Intent()
                intent.setClassName("com.review.aidl.server", "com.review.aidl.server.MyAIDLService")
                bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
            }
            R.id.btn_callService -> {
                val person = Person()
                person.setId(2)
                person.setName("李四")
                person.setGender("女")
                try {
                    myAIDL?.greet(person)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            R.id.btn_callService2 -> {
                try {
                    val person = myAIDL?.person
                    Log.d("MyService", "返回的 persons=$person")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            R.id.btn_unbindService -> {
                myAIDL = null
                unbindService(serviceConnection)
            }
        }
    }

    var myAIDL: MyAIDL? = null
    var serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            Log.d("MyService", "onServiceConnected,service=$service")
            //service=android.os.BinderProxy
            myAIDL = MyAIDL.Stub.asInterface(service)
            Log.d("MyService", "onServiceConnected,myAIDL=$myAIDL")
            try {
                var info = myAIDL?.getInfor("hello world")
                Log.d("MyService", "onServiceConnected,info=$info")
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }

        override fun onServiceDisconnected(name: ComponentName) {
            Log.d("MyService", "onServiceDisconnected")
        }
    }
}
