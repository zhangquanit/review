package com.example.targetversion

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper

/**
 *
 * @author zhangquan
 */
class MyService() : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Handler(Looper.getMainLooper()).postDelayed({
            println("handle message")
            var intent = Intent(this, TwoActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }, 20 * 1000)
        return Service.START_STICKY
    }
}