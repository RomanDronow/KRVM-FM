package com.grechka.krvmfm.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.grechka.krvmfm.R
import com.grechka.krvmfm.objects.Spin
import com.grechka.krvmfm.util.grabber.PlaylistGrabber
import kotlinx.android.synthetic.main.fragment_queue.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QueueFragment : Fragment() {

    private var listForView: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fillPlaylist()
        val recyclerView: RecyclerView = spins_list
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = SpinsListAdapter(listForView)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_queue, container, false)
    }

    private fun fillPlaylist() {
        GlobalScope.launch{
            listForView = PlaylistGrabber().getPlaylist()
            withContext(Dispatchers.Main) {

            }
        }
    }

    class SpinsListAdapter(private val items: List<String>) :
        RecyclerView.Adapter<SpinsListAdapter.ViewHolder>() {

        private val gson = Gson()

        override fun getItemCount() = items.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.queue_item, parent, false)
            return ViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ViewHolder, i: Int) {
            val spin = gson.fromJson(items[i],Spin::class.java)
            holder.textViewArtist?.text = spin.a
            holder.textViewSong?.text = spin.s
            holder.textViewAlbum?.text = spin.r
            holder.textViewTime?.text =  spin.t
            holder.textViewImg?.text = spin.img
        }

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
        }
    }
}