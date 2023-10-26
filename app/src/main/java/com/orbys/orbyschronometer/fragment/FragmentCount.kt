package com.orbys.orbyschronometer.fragment

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.SystemClock
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.TimePicker
import com.orbys.orbyschronometer.R
import studio.clapp.wheelpicker.WheelPicker

class FragmentCount : Fragment() {
    var time: Long = 0
    var bool = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("PruebaEntraFragment", "Entra fragmentt")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_count, container, false)

        val wheel1 = view?.rootView?.findViewById<WheelPicker>(R.id.wheel_picker)
        val wheel2 = view?.rootView?.findViewById<WheelPicker>(R.id.wheel_picker2)
        val wheel3 = view?.rootView?.findViewById<WheelPicker>(R.id.wheel_picker3)
        val btnTakeValues = view?.rootView?.findViewById<ImageView>(R.id.takaValues)
        val linearLayout = view.findViewById<LinearLayout>(R.id.linearLayout)
        val chronometer = view.findViewById<Chronometer>(R.id.countdown_chronometer)

        wheel1?.setMinValue(0)
        wheel1?.setMaxValue(23)
        wheel2?.setMinValue(0)
        wheel2?.setMaxValue(60)
        wheel3?.setMinValue(0)
        wheel3?.setMaxValue(60)

        chronometer.visibility = View.GONE
        linearLayout.visibility = View.VISIBLE


        btnTakeValues?.setOnClickListener {

            val txtH = wheel1?.getCurrentItem().toString()
            val txtM = wheel2?.getCurrentItem().toString()
            val txtS = wheel3?.getCurrentItem().toString()

            if(txtH.toInt() !=0||txtM.toInt() !=0||txtS.toInt() !=0) {
                chronometer.visibility = View.VISIBLE
                linearLayout.visibility = View.GONE
                val h = (txtH.toInt() * 60 * 60 * 1000)
                val m = (txtM.toInt() * 60 * 1000)
                val s = (txtS.toInt() * 1000)

                // Set the countdown duration in milliseconds (e.g., 5 minutes)
                val countdownDurationMs = m + h + s

                // Calculate the base time (current time + countdown duration)
                val baseTime = SystemClock.elapsedRealtime() + countdownDurationMs
                chronometer.base = baseTime


                chronometer.isCountDown = true

                // Start the countdown
                chronometer.start()
            }
        }

        return view
    }

}