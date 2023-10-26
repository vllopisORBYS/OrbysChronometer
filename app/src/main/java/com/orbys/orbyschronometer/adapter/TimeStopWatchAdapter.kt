package com.orbys.orbyschronometer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.orbys.orbyschronometer.R
import com.orbys.orbyschronometer.holder.TimeStopWatchHolder

class TimeStopWatchAdapter (
    var listTimers : MutableList<String>
) : RecyclerView.Adapter<TimeStopWatchHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeStopWatchHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_stop_watch, parent, false)
        return TimeStopWatchHolder(itemView)
    }


    override fun onBindViewHolder(holder: TimeStopWatchHolder, position: Int) {

        holder.render(listTimers)

    }

    fun updateList(list: List<String>) {
        listTimers = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount()= listTimers.size

}