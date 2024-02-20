package com.okstore.increasewatchtimeto4000hr.dashboard

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.okstore.increasewatchtimeto4000hr.R
import com.okstore.increasewatchtimeto4000hr.WatchHourActivity
import com.okstore.increasewatchtimeto4000hr.utils.showProgressDialog
import java.util.regex.Pattern

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val search = findViewById<EditText>(R.id.searchET)
        val c5 = findViewById<CardView>(R.id.card5)

        c5.setOnClickListener {
            val youtubeUrl = search.text.toString()
            val videoId = extractYouTubeVideoId(youtubeUrl)

            val dialog = showProgressDialog(this)

            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed(Runnable {
                dialog.dismiss()
                val intent = Intent(this, WatchHourActivity::class.java)
                intent.putExtra("videoID", videoId)
                startActivity(intent)
            }, 500)
        }
    }

    private fun extractYouTubeVideoId(url: String): String? {
        val pattern = "(?<=youtu\\.be/|watch\\?v=|/videos/|embed\\/|youtu\\.be\\/|v=|e\\/|u\\/\\w{0,11}\\/|\\W|^)([\\w-]+)(?=\\?|&|\$)"
        val compiledPattern = Pattern.compile(pattern)
        val matcher = compiledPattern.matcher(url)

        if (matcher.find()) {
            return matcher.group(1)
        }

        return null
    }

}