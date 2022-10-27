package com.example.ssdevkt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlin.properties.Delegates
import com.google.android.gms.maps.GoogleMap;


class MapActivity : AppCompatActivity() {

    private var mLat by Delegates.notNull<Double>()
    private var mLon by Delegates.notNull<Double>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_acticity)
        mLat = intent.getDoubleExtra("lat", .0)
        mLon = intent.getDoubleExtra("lon", .0)

    }
}


