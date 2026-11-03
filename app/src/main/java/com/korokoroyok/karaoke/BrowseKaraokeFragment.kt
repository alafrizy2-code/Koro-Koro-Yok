package com.korokoroyok.karaoke

import android.content.Intent
import android.os.Bundle
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*

class BrowseKaraokeFragment : BrowseSupportFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        title = "Koro Koro Yok"
        badgeDrawable = null
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true

        loadRows()
        setupListeners()
    }

    private fun loadRows() {
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        
        // Row 1: Dangdut Medan - Paling Laku
        val dangdutHeader = HeaderItem(0, "Dangdut Medan")
        val dangdutPresenter = CardPresenter()
        val dangdutAdapter = ArrayObjectAdapter(dangdutPresenter)
        dangdutAdapter.add(KaraokeVideo("Via Vallen - Sayang", "Sayang Via Vallen karaoke"))
        dangdutAdapter.add(KaraokeVideo("Nella Kharisma - Jaran Goyang", "Jaran Goyang karaoke"))
        dangdutAdapter.add(KaraokeVideo("Rhoma Irama - Begadang", "Begadang karaoke"))
        rowsAdapter.add(ListRow(dangdutHeader, dangdutAdapter))

        // Row 2: Pop Indonesia
        val popHeader = HeaderItem(1, "Pop Indonesia")
        val popAdapter = ArrayObjectAdapter(dangdutPresenter)
        popAdapter.add(KaraokeVideo("Judika - Bukan Dia Tapi Aku", "Judika karaoke"))
        popAdapter.add(KaraokeVideo("Rizky Febian - Kesempurnaan Cinta", "Rizky Febian karaoke"))
        rowsAdapter.add(ListRow(popHeader, popAdapter))

        // Row 3: Cari Lagu (Voice Search)
        val searchHeader = HeaderItem(2, "Cari Lagu")
        val searchAdapter = ArrayObjectAdapter(dangdutPresenter)
        searchAdapter.add(KaraokeVideo("🔍 Tekan OK untuk Cari Suara", "VOICE_SEARCH"))
        rowsAdapter.add(ListRow(searchHeader, searchAdapter))

        adapter = rowsAdapter
    }

    private fun setupListeners() {
        onItemViewClickedListener = ItemViewClickedListener()
    }

    inner class ItemViewClickedListener : OnItemViewClickedListener {
        override fun onItemClicked(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any?,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row?
        ) {
            if (item is KaraokeVideo) {
                val intent = Intent(activity, PlaybackActivity::class.java)
                intent.putExtra("QUERY", item.query)
                intent.putExtra("TITLE", item.title)
                activity?.startActivity(intent)
            }
        }
    }
}

data class KaraokeVideo(val title: String, val query: String)
