package com.grechka.krvmfm

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grechka.krvmfm.objects.Spin

class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    var textViewArtist: TextView? = null
    var textViewSong: TextView? = null
    var textViewAlbum: TextView? = null
    var textViewTime: TextView? = null
    var textViewImg: TextView? = null

    init {
        textViewArtist = itemView?.findViewById(R.id.item_artist)
        textViewSong = itemView?.findViewById(R.id.item_song)
        textViewAlbum = itemView?.findViewById(R.id.item_album)
        textViewTime = itemView?.findViewById(R.id.item_time)
    }

    fun bind(spin: Spin){
        textViewArtist?.text = spin.a
        textViewSong?.text = spin.s
        textViewAlbum?.text = spin.r
        textViewTime?.text =  spin.t
        textViewImg?.text = spin.img
    }
}