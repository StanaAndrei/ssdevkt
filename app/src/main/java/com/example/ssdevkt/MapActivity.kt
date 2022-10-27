package com.example.ssdevkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlin.properties.Delegates


class MapActivity : AppCompatActivity() {

    private var mLat by Delegates.notNull<Double>()
    private var mLon by Delegates.notNull<Double>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_activity)
        mLat = intent.getDoubleExtra("lat", .0)
        mLon = intent.getDoubleExtra("lon", .0)

    }
}


