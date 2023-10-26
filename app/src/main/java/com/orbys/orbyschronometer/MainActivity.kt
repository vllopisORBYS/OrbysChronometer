package com.orbys.orbyschronometer

import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.util.Log
import android.widget.Chronometer
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.orbys.orbyschronometer.fragment.FragmentCount
import com.orbys.orbyschronometer.fragment.FragmentStopwatch


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment1 = FragmentStopwatch()
        var btnClose = findViewById<LinearLayout>(R.id.btnClose)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.flFragment2, fragment1)
            commit()
        }

        // Para el botón "Count"
        findViewById<LinearLayout>(R.id.btnCount).setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.flFragment2, FragmentCount())
                .commit()
            // Remove fragment2
            val existingFragment = supportFragmentManager.findFragmentById(R.id.flFragment2)
            if (existingFragment != null) {
                supportFragmentManager.beginTransaction().remove(existingFragment).commit()
            }else{
                Log.d("Prueba FragmenesNoexist Stopwach","FragmenesNoexist Stopwach")
            }
        }

        // Para el botón "StopWatch"
        findViewById<LinearLayout>(R.id.btnStopWatch).setOnClickListener {

            supportFragmentManager.beginTransaction().replace(R.id.flFragment2,  FragmentStopwatch())
                .commit()

            // Remove fragment2
            val existingFragment = supportFragmentManager.findFragmentById(R.id.flFragment2)
            if (existingFragment != null) {
                supportFragmentManager.beginTransaction().remove(existingFragment).commit()
            }else{
                Log.d("Prueba FragmenesNoexist count","FragmenesNoexist Count")
            }
        }

        btnClose.setOnClickListener {
            finish()
        }

    }



}