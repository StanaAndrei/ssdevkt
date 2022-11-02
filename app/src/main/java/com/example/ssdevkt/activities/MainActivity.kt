package com.example.ssdevkt.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ssdevkt.R
import kotlin.math.floor

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = ".Main"
    }

    private lateinit var mLatEditTxt: EditText
    private lateinit var mLonEditTxt: EditText
    private lateinit var mLatTxtView: TextView
    private lateinit var mLonTxtView: TextView
    private lateinit var mShowMapBtn: Button
    private lateinit var latTxt: String
    private lateinit var lonTxt: String

    private fun getCoord(coord: Double): String {
        val coordDeg: Int = coord.toInt()
        val coordMins: Double = (coord - floor(coord)) * 60
        val coordSecs: Double = (coordMins - floor(coordMins)) * 60
        return String.format("%d°%d'%.2f''", coordDeg, coordMins.toInt(), coordSecs)
    }

    private fun exportToCsv() {
        //TODO("")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val convertBtn = findViewById<Button>(R.id.convert_btn)
        convertBtn.setOnClickListener{ handleConvertClick() }

        mShowMapBtn = findViewById(R.id.show_map_btn)
        mShowMapBtn.setOnClickListener{ handleShowClick() }

        mLatEditTxt = findViewById(R.id.lat_edit_text)
        mLonEditTxt = findViewById(R.id.lon_edit_text)
        mLatTxtView = findViewById(R.id.lat_txt)
        mLonTxtView = findViewById(R.id.lon_txt)
    }

    private fun handleConvertClick() {
        latTxt = mLatEditTxt.text.toString()
        lonTxt = mLonEditTxt.text.toString()

        if (latTxt.isEmpty() || lonTxt.isEmpty()) {
            val toast = Toast(applicationContext)
            toast.setText("A field is empty!")
            toast.duration = Toast.LENGTH_SHORT
            toast.show()
            return
        }

        mLatTxtView.text = String.format("lat: %s", getCoord(latTxt.toDouble()))
        mLonTxtView.text = String.format("lon: %s", getCoord(lonTxt.toDouble()))
        hideSoftKbd()
        mShowMapBtn.isEnabled = true
    }

    private fun handleShowClick() {
        val startMapsIntent = Intent()
        startMapsIntent.setClass(applicationContext, MapActivity::class.java)
        startMapsIntent.putExtra("lat", latTxt.toDouble())
        startMapsIntent.putExtra("lon", lonTxt.toDouble())
        startActivity(startMapsIntent)
    }

    private fun hideSoftKbd() {
        val view: View = this.currentFocus ?: return
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}