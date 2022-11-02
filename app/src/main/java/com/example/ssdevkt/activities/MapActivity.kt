package com.example.ssdevkt.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ssdevkt.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlin.properties.Delegates


class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        private const val TAG = ".Map"
    }

    private var mLat by Delegates.notNull<Double>()
    private var mLon by Delegates.notNull<Double>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        mLat = intent.getDoubleExtra("lat", .0)
        mLon = intent.getDoubleExtra("lon", .0)
        val mapFrag = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFrag.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val latLng = LatLng(mLat, mLon)

        val markerOption = MarkerOptions()
        markerOption.position(latLng)
        val markerStr = getString(R.string.marker_title)
        markerOption.title(markerStr)

        googleMap.addMarker(markerOption)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
    }
}


