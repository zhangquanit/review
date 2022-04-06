package com.example.targetversion

import android.Manifest
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import java.io.InputStreamReader
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //http请求 targetSdkVersion>=28使用https  使用http需要设置
        doNetwork()

        //运行时权限  targetSdkVersion>=23
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)


        //android10 后台启动activity
        startService(Intent(this, MyService::class.java))

        //8.0的通知测试 targetSdkVersion>=26必须设置NotifcationChannel   targetSdkVersion<26就无所谓
        val pendingIntent = PendingIntent.getService(this, 0,
                Intent(this, TwoActivity::class.java), PendingIntent.FLAG_UPDATE_CURRENT)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(this)
        val notification = builder.setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("title")
                .setContentText("content")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()
        notificationManager.notify(11, notification)
    }

    fun doNetwork() {
        Thread {
            var url = URL("http://www.baidu.com");
            var urlConnection = url.openConnection();
            try {
                val inputStream = urlConnection.getInputStream()
                val inputStreamReader = InputStreamReader(inputStream)
                val readLines = inputStreamReader.readLines()
                println("data=$readLines")
                inputStream.close()

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }.start()
    }
}
