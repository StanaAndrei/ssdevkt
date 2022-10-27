package com.example.ssdevkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlin.math.floor

class MainActivity : AppCompatActivity() {


    fun getCoord(coord: Double): String {
        val coordDeg: Int = coord.toInt()
        val coordMins: Double = (coord - floor(coord)) * 60
        val coordSecs: Double = (coordMins - floor(coordMins)) * 60
        return String.format("%d%d'%.2f''", coordDeg, coordMins.toInt(), coordSecs)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}