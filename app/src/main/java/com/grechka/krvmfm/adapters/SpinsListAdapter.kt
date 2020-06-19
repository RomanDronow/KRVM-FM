package com.grechka.krvmfm.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.grechka.krvmfm.R
import com.grechka.krvmfm.ViewHolder
import com.grechka.krvmfm.objects.Spin
import kotlin.reflect.KMutableProperty0

class SpinsListAdapter(private val items: KMutableProperty0<ArrayList<String>>) :
    RecyclerView.Adapter<ViewHolder>() {

    private val gson = Gson()

    override fun getItemCount() = items.get().size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.queue_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        val spin = gson.fromJson(items.get()[i], Spin::class.java)
        holder.bind(spin)
        Log.d("onBind",spin.a)
    }
}