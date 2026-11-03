package com.korokoroyok.karaoke

import android.graphics.Color
import android.view.ViewGroup
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter

class CardPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val cardView = ImageCardView(parent.context)
        cardView.isFocusable = true
        cardView.isFocusableInTouchMode = true
        cardView.setBackgroundColor(Color.DKGRAY)
        cardView.setMainImageDimensions(300, 170)
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        val video = item as KaraokeVideo
        val cardView = viewHolder.view as ImageCardView
        cardView.titleText = video.title
        cardView.contentText = "Koro Koro Yok - F999Max Mode"
        // Placeholder color, no internet image needed
        cardView.setMainImage(null) 
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {}
}
