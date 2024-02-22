package com.okstore.increasewatchtimeto4000hr

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Adapter
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView

class WebAdapter(private val context: Context, private val list : List<WebData>) :
    RecyclerView.Adapter<WebAdapter.WebViewHolder>() {

        class WebViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val webView = itemView.findViewById<WebView>(R.id.webView)

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebViewHolder {
        val inflater = LayoutInflater.from(context)
            val view= inflater.inflate(R.layout.web_item, parent, false)
        return WebViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: WebViewHolder, position: Int) {


        val embedUrl = list[position].embedUrl

        // Initialize WebView settings and load the URL here
        val webView = holder.webView

        webView?.settings?.javaScriptEnabled = true
        webView?.settings?.mediaPlaybackRequiresUserGesture = false // Allow autoplay
        webView?.webChromeClient = WebChromeClient()
        webView?.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                // Inject JavaScript code to trigger video playback
                view?.loadUrl("javascript:document.getElementsByTagName('video')[0].play();")
            }
        }

        val video = "<iframe width=\"100%\" height=\"100%\" src=\"$embedUrl\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"

        webView?.loadData(video, "text/html", "utf-8")

    }
}