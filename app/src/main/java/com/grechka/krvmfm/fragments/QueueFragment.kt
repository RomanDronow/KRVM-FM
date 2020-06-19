package com.grechka.krvmfm.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.grechka.krvmfm.R
import com.grechka.krvmfm.adapters.SpinsListAdapter
import com.grechka.krvmfm.util.grabber.PlaylistGrabber
import kotlinx.android.synthetic.main.fragment_queue.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QueueFragment : Fragment() {

    private var listForView: ArrayList<String> = ArrayList()
    private val adapter = SpinsListAdapter(::listForView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fillPlaylist()
        val linearLayoutManager = LinearLayoutManager(this.context)
        spins_list.layoutManager = linearLayoutManager
        spins_list.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_queue, container, false)
    }

    private fun fillPlaylist() {
        GlobalScope.launch {
            listForView = PlaylistGrabber().getPlaylist()
            withContext(Dispatchers.Main) {
                adapter.notifyDataSetChanged()
            }
        }
    }
}