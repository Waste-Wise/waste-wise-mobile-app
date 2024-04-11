package com.example.wastewise

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class NonDisposableActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private val locations = listOf(
        LatLng(37.7749, -122.4194), // San Francisco
        LatLng(37.7974, -122.4058), // Golden Gate Bridge
        LatLng(37.8080, -122.4177), // Alcatraz Island
        LatLng(37.7749, -122.4308), // Palace of Fine Arts
        LatLng(37.8087, -122.4098)  // Fisherman's Wharf
    )
    private val markers = mutableListOf<Marker>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_non_disposable)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment2) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Button to generate route link
        findViewById<Button>(R.id.generateRouteButton).setOnClickListener {
            generateRouteLink()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        addMarkers()
        moveCameraToLocation(locations.first())
    }

    private fun addMarkers() {
        // Ensure googleMap is not null
        if (::googleMap.isInitialized) {
            for (location in locations) {
                val marker = googleMap.addMarker(MarkerOptions().position(location))
                marker?.let {
                    markers.add(it)
                }
            }
            moveCameraToLocation(locations.first())
        }
    }


    private fun moveCameraToLocation(location: LatLng) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12f))
    }

    private fun generateRouteLink() {
        val routeBuilder = StringBuilder()
        for (location in locations) {
            routeBuilder.append("${location.latitude},${location.longitude}|")
        }
        val routeLink = "https://www.google.com/maps/dir/?api=1&destination=${locations.last().latitude},${locations.last().longitude}&waypoints=${routeBuilder.toString().removeSuffix("|")}"

        // Open the generated route link in Google Maps
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(routeLink))
        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)
    }
}
