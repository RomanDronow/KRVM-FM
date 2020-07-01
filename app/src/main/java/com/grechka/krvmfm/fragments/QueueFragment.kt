package com.grechka.krvmfm.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

    private val listForView: ArrayList<String> = ArrayList()
    private val adapter = SpinsListAdapter(::listForView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fillPlaylist()
        Log.d("array size: ", listForView.size.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_queue, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spins_list.layoutManager = LinearLayoutManager(requireContext())
        spins_list.adapter = adapter
    }

    private fun fillPlaylist() {
        GlobalScope.launch {
            listForView.clear()
            listForView.addAll(PlaylistGrabber().getPlaylist())
            withContext(Dispatchers.Main) {
                adapter.notifyDataSetChanged()
            }
        }
    }
}