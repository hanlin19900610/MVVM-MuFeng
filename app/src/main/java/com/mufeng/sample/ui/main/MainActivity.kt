package com.mufeng.sample.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mufeng.sample.R
import com.mufeng.sample.bindingDemo.BindingDemoActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun gotoBind(view: View) {

        startActivity(Intent(this, BindingDemoActivity::class.java))

    }
}
