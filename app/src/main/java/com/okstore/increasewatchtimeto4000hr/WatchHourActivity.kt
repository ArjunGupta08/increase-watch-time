package com.okstore.increasewatchtimeto4000hr

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.okstore.increasewatchtimeto4000hr.databinding.ActivityWatchHourBinding
import com.okstore.increasewatchtimeto4000hr.windowFrags.WindowFragment2
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class WatchHourActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWatchHourBinding

    private val webView : WebView
        get() = binding.webView

    var videoId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatchHourBinding.inflate(layoutInflater)
        setContentView(binding.root)

        videoId = intent.getStringExtra("videoID").toString()

//        val youTubePlayerView = binding.youtubePlayerView
//        lifecycle.addObserver(youTubePlayerView)

//        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
//            override fun onReady(youTubePlayer: YouTubePlayer) {
////                val videoId = "S0Q4gqBUs7c"
//                youTubePlayer.loadVideo(videoId, 0f)
//            }
//        })

            if (videoId != null) {

                // Initialize WebView settings
                webView.settings.javaScriptEnabled = true
                webView.settings.mediaPlaybackRequiresUserGesture = false // Allow autoplay
                webView.webChromeClient = WebChromeClient()
                webView.webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)

                        // Inject JavaScript code to trigger video playback
                        view?.loadUrl("javascript:document.getElementsByTagName('video')[0].play();")
                    }
                }

                val embedUrl = "https://www.youtube.com/embed/$videoId?autoplay=1"

                Glide.with(this).load("https://img.youtube.com/vi/$videoId/hqdefault.jpg").fitCenter().placeholder(R.drawable.animated_three_dot).into(binding.thumbnail)

                val video = "<iframe width=\"100%\" height=\"100%\" src=\"$embedUrl\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"

                webView.loadData(video, "text/html", "utf-8")
            } else {
                println("Invalid YouTube URL")
            }

            replaceFragment(WindowFragment2(videoId))

    }

    // AIzaSyDL2bQwxzvGCdCzEhPks4wk8CtrJYmkTvE
    // BuildConfig.YOUTUBE_API_KEY
    private fun replaceFragment(fragment: Fragment) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame2,fragment)
            transaction.commit()
    }


}