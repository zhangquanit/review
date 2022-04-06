package com.example.apk_reduce

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * 只要在manifest.xml中定义了
 * 不管是否使用 代码都会保存
 * @author zhangquan
 */
class TwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = resources.getIdentifier("reflect_layout", "layout", packageName)
        setContentView(layout)
        method1()
    }

    fun method1() {

    }

    fun unusedMethod() {

    }
}