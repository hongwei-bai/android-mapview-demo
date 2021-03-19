package com.hongwei.android_lab.map

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.hongwei.android_lab.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.mapView.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        viewBinding.mapView.onResume()
        viewBinding.mapView.getMapAsync { googleMap ->
            with(googleMap) {
                val location = LatLng(-33.8566686, 151.214653)
                moveCamera(CameraUpdateFactory.newLatLng(location))
                animateCamera(CameraUpdateFactory.newLatLngZoom(location, 16f))
                addMarker(MarkerOptions().position(location))
            }
        }

        val html = "<b>Address: </b> Bennelong Point, Sydney NSW 2000<br/>" +
                "<b>Link:</b> https://www.sydneyoperahouse.com/<br/>" +
                "<b>Tel:</b> +61292507111"
        viewBinding.description.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY)
        viewBinding.buttonExit.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        viewBinding.mapView.onDestroy()
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()
        viewBinding.mapView.onStart()
    }

    override fun onPause() {
        viewBinding.mapView.onPause()
        super.onPause()
    }

    override fun onStop() {
        viewBinding.mapView.onStop()
        super.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        viewBinding.mapView.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewBinding.mapView.onSaveInstanceState(outState)
    }
}