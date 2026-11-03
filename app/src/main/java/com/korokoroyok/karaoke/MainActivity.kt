package com.korokoroyok.karaoke

import android.os.Bundle
import androidx.fragment.app.FragmentActivity

/**
 * Koro Koro Yok - Launcher
 * B866F + F999Max Mode A (Hardware Effect)
 * Musik: B866F -> TV -> AUX -> F999Max LINE IN
 * Mic: Wireless -> F999Max MIC IN -> Speaker
 */
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_browse_fragment, BrowseKaraokeFragment())
                .commit()
        }
    }
}
