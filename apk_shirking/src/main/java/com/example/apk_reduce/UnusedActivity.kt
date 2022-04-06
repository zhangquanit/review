package com.example.apk_reduce

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * 未在manifest.xml中注册
 * 不会打进apk包中
 * @author zhangquan
 */
class UnusedActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}