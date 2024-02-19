package com.okstore.increasewatchtimeto4000hr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.okstore.increasewatchtimeto4000hr.dashboard.DashboardActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val handler = Handler(Looper.getMainLooper())

        handler.postDelayed(Runnable {
             startActivity(Intent(this, DashboardActivity::class.java))
        }, 200 )

    }
}