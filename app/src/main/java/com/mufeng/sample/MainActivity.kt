package com.mufeng.sample

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mufeng.sample.bindingDemo.BindingDemoActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @ExperimentalCoroutinesApi
    fun gotoBind(view: View) {

        startActivity(Intent(this, BindingDemoActivity::class.java))

    }
}
