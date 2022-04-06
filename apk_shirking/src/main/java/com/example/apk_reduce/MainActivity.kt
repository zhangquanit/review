package com.example.apk_reduce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawable = resources.getIdentifier("icon_wx", "drawable", packageName)
        imageview.setImageResource(drawable)

        method1()
    }

    fun method1() {

    }

    fun unusedMethod() {

    }

}
