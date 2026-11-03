package com.korokoroyok.karaoke

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import android.widget.Toast

/**
 * Koro Koro Yok - Playback Mode A
 * Flow Audio: B866F (YouTube) -> HDMI -> TV -> Kabel AUX 3.5mm -> F999Max LINE IN -> Speaker + Mic
 * Efek Echo diatur manual di knob F999Max (0 delay)
 */
class PlaybackActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val query = intent.getStringExtra("QUERY") ?: "karaoke"
        val title = intent.getStringExtra("TITLE") ?: "Koro Koro Yok"

        if (query == "VOICE_SEARCH") {
            // Voice Search - pakai Google Assistant yang ada di B866F
            val voiceIntent = Intent(Intent.ACTION_SEARCH)
            voiceIntent.putExtra("query", "karaoke")
            Toast.makeText(this, "Tekan mic di remote B866F dan bilang judul lagu", Toast.LENGTH_LONG).show()
            startActivity(voiceIntent)
            finish()
            return
        }

        // Mode A: Lempar ke YouTube App (paling stabil di B866F)
        try {
            // Coba buka di YouTube app resmi
            val youtubeIntent = Intent(Intent.ACTION_SEARCH)
            youtubeIntent.setPackage("com.google.android.youtube.tv")
            youtubeIntent.putExtra("query", "$query karaoke no vocal")
            startActivity(youtubeIntent)
        } catch (e: Exception) {
            // Fallback ke browser / YouTube web
            val webUrl = "https://www.youtube.com/results?search_query=${Uri.encode("$query karaoke")}"
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(webUrl))
            startActivity(browserIntent)
        }

        Toast.makeText(this, "Mode A: Musik via TV -> F999Max LINE IN | Atur Echo di F999Max", Toast.LENGTH_LONG).show()
        finish()
    }
}
