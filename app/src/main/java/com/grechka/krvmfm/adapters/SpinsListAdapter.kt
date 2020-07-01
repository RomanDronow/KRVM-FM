package com.grechka.krvmfm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.grechka.krvmfm.R
import com.grechka.krvmfm.objects.Spin
import kotlin.reflect.KProperty0

class SpinsListAdapter(private val items: KProperty0<ArrayList<String>>) :
    RecyclerView.Adapter<SpinsListAdapter.ViewHolder>() {

    private val gson = Gson()

    override fun getItemCount() = items.get().size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.queue_item, parent, false)
    )


    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        val spin = gson.fromJson(items.get()[i], Spin::class.java)
        holder.bind(spin)
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var textViewArtist: TextView? = null
        var textViewSong: TextView? = null
        var textViewAlbum: TextView? = null
        var textViewTime: TextView? = null
        var textViewImg: TextView? = null

        init {
            textViewArtist = itemView?.findViewById(R.id.item_artist)
            textViewSong = itemView?.findViewById(R.id.item_song)
            textViewTime = itemView?.findViewById(R.id.item_time)
        }

        fun bind(spin: Spin) {
            textViewArtist?.text = spin.artist
            textViewSong?.text = spin.song
            textViewAlbum?.text = spin.release
            textViewTime?.text = spin.time
            textViewImg?.text = spin.img
        }
    }
}