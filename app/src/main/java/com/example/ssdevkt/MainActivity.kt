package com.example.ssdevkt

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
import kotlin.math.floor

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = ".Main"
    }

    lateinit var mLatEditTxt: EditText
    lateinit var mLonEditTxt: EditText
    lateinit var mLatTxtView: TextView
    lateinit var mLonTxtView: TextView
    lateinit var mShowMapBtn: Button
    lateinit var latTxt: String
    lateinit var lonTxt: String


    fun getCoord(coord: Double): String {
        val coordDeg: Int = coord.toInt()
        val coordMins: Double = (coord - floor(coord)) * 60
        val coordSecs: Double = (coordMins - floor(coordMins)) * 60
        return String.format("%d°%d'%.2f''", coordDeg, coordMins.toInt(), coordSecs)
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

    fun handleConvertClick() {
        latTxt = mLatEditTxt.text.toString()
        lonTxt = mLonEditTxt.text.toString()

        if (latTxt?.isEmpty() == true || lonTxt?.isEmpty() == true) {
            val toast = Toast(applicationContext)
            toast.setText("A field is empty!")
            toast.duration = Toast.LENGTH_SHORT
            toast.show()
            return
        }

        mLatTxtView.setText("lat: " + getCoord(latTxt.toDouble()))
        mLonTxtView.setText("lon: " + getCoord(lonTxt.toDouble()))
        hideSofeKbd()
        mShowMapBtn.isEnabled = true
    }

    fun handleShowClick() {
        val intent = Intent()
        intent.setClass(applicationContext, MapActivity::class.java)
        intent.putExtra("lat", latTxt.toDouble())
        intent.putExtra("lon", lonTxt.toDouble())
        startActivity(intent)
    }

    fun hideSofeKbd() {
        val view: View = this.currentFocus ?: return
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }
}