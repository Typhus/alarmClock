package ru.taptap.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.TextView

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import dagger.android.support.DaggerAppCompatActivity
import java.text.DecimalFormat
import javax.inject.Inject

class MapsActivity : DaggerAppCompatActivity(), OnMapReadyCallback {

    private val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[LocationViewModel::class.java]
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var map: GoogleMap
    private var marker1: Marker? = null
    private var marker2: Marker? = null

    private val texView by bindView<TextView>(R.id.text)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        map.setOnMapClickListener { position ->
            addMarker(position)
        }

        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)0
//        map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        map.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        checkPermission()
    }

    private fun addMarker(position: LatLng) {
        when {
            marker1 == null -> marker1 = map.addMarker(MarkerOptions()
                    .draggable(true)
                    .position(position)
                    .title("new Market1"))
            marker2 == null -> marker2 = map.addMarker(MarkerOptions()
                    .draggable(true)
                    .position(position)
                    .title("new Market2"))
            else -> {
//                marker1 = marker2
//                marker2?.remove()
//                marker2 = null
//                addMarker(position)
            }
        }

        if (marker1 != null && marker2 != null) {
            val length = viewModel.calcLengthInKm(marker1!!.position, marker2!!.position)
            texView.text = "расстояние = $length"
        }
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            map.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION)
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            if (permissions.size == 1 &&
                    permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                map.isMyLocationEnabled = true
            } else {
                AlertDialog.Builder(this).setTitle("Ну и иди в жопу!").show()
            }
        }
    }
}

/**
 * 1. Сначала разберись с ViewModel
 * 2. Потом все остальное
 */


/**
 * 1. Получить 2 точки
 * 2. Посчитать между ними расстояние
 */
