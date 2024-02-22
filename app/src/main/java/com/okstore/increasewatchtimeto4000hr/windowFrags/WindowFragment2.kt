package com.okstore.increasewatchtimeto4000hr.windowFrags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.bumptech.glide.Glide
import com.okstore.increasewatchtimeto4000hr.R

class WindowFragment2(private val videoId : String) : Fragment() {

    private lateinit var webView : WebView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_window2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        webView = view.findViewById(R.id.webView)

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

            val video = "<iframe width=\"100%\" height=\"100%\" src=\"$embedUrl\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"

            webView.settings.javaScriptEnabled = true
            webView.loadData(video, "text/html", "utf-8")
        } else {
            println("Invalid YouTube URL")
        }

    }
}