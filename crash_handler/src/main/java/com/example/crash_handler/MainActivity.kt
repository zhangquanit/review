package com.example.crash_handler

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start()
        textview.setOnClickListener {
            Toast.makeText(MainActivity@ this, "toast", Toast.LENGTH_SHORT).show()
        }
    }

    private fun start() {

//        AppCrashHandler.install { t, e ->
//            if(Looper.getMainLooper().thread==t){ //主线程异常
//                println("主线程异常")
//            }else{ //子线程异常
//                println("子线程异常")
//            }
//            e.printStackTrace()
//        }
        val defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            println("thread=" + t.name)
            e.printStackTrace()
            defaultUncaughtExceptionHandler.uncaughtException(t, e)
        }
    }

    override fun onResume() {
        super.onResume()
        Handler(Looper.getMainLooper()).postDelayed({
            "a".toInt()  //主线程异常
        }, 3 * 1000)

        Handler(Looper.getMainLooper()).postDelayed({
            Thread {
                "b".toInt()  //子线程异常
            }.start()
        }, 5 * 1000)
    }
}
