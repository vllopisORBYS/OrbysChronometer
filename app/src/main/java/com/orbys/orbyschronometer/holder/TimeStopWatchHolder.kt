package com.orbys.orbyschronometer.holder

import android.view.TextureView
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.orbys.orbyschronometer.R


class TimeStopWatchHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    var txt :TextView
    var idx :TextView
    init {

        txt = itemView.findViewById(R.id.tvNameEntry)
        idx = itemView.findViewById(R.id.tvIndex)

    }

    fun render(list: MutableList<String>){
        txt.text = list[layoutPosition]
        idx.text = String.format("$layoutPosition.")
    }

}

