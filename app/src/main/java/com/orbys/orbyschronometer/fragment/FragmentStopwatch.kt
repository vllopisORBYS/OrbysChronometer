package com.orbys.orbyschronometer.fragment

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.orbys.orbyschronometer.R
import com.orbys.orbyschronometer.adapter.TimeStopWatchAdapter
import studio.clapp.wheelpicker.WheelPicker
import studio.clapp.wheelpicker.dialog.TimePickerDialog
import java.time.LocalDateTime

class FragmentStopwatch : Fragment() {

    val listTimer = mutableListOf<String>()
    lateinit var adapter : TimeStopWatchAdapter
    var lastPause :Long =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_stopwatch, container, false)
        var isOn = false

        val chro = view.findViewById<Chronometer>(R.id.crono)
        val btnGetTime = view.findViewById<LinearLayout>(R.id.btnGetTime)
        val btnStart = view.findViewById<ImageView>(R.id.btnChronoStart)
        val btnReset = view.findViewById<ImageView>(R.id.btnChronoReStart)

        adapter = TimeStopWatchAdapter(listTimer)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.rvStopWatch)
        recyclerView?.adapter = adapter
        val layoutManager = LinearLayoutManager(context)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        recyclerView?.layoutManager = layoutManager

        //Cuando se añada un elemento nuevo que vaya arriba del recycledView
        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                recyclerView?.scrollToPosition(listTimer.size - 1)
            }
        })
        chro.format = "00:%s"
        chro?.base = SystemClock.elapsedRealtime()

        btnStart.setOnClickListener {
            if(!isOn) {

                chro?.start()
                if(lastPause.toInt() != 0) {
                    chro?.base = chro?.base?.plus(SystemClock.elapsedRealtime() - lastPause)!!
                }
            }else{
                lastPause  = SystemClock.elapsedRealtime()
                chro?.stop()
            }
            isOn = !isOn
        }

        btnReset.setOnClickListener{
            chro?.base = SystemClock.elapsedRealtime()
            listTimer.clear()
            adapter.updateList(listTimer)
        }

        btnGetTime.setOnClickListener {
            Log.d("Prueba Texo crono ", chro.text.toString())
            listTimer.add(chro.text.toString())
            adapter.updateList(listTimer)
        }

        // Inflate the layout for this fragment
        return view
    }
}