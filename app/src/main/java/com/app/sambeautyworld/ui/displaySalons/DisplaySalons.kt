package com.app.sambeautyworld.ui.displaySalons

import android.os.Bundle
import android.support.annotation.Nullable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.cluster.MyItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterManager
import kotlinx.android.synthetic.main.display_salons.*


class DisplaySalons : BaseFragment(), OnMapReadyCallback {
    private var googleMaps: GoogleMap? = null

    override fun onMapReady(p0: GoogleMap?) {
        googleMaps = p0
        setUpClusterer()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        if (activity != null) {
            val mapFragment = childFragmentManager
                    .findFragmentById(R.id.map_display) as SupportMapFragment
            mapFragment.getMapAsync(this)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListeners()
    }

    private fun clickListeners() {
        tvGoBackSalonLocator.setOnClickListener {
            activity!!.onBackPressed()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.display_salons, container, false)
    }


    private var mClusterManager: ClusterManager<MyItem>? = null

    private fun setUpClusterer() {
        // Position the map.
        googleMaps?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(51.503186, -0.126446), 10f))

        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        mClusterManager = ClusterManager(activity, googleMaps)

        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
        googleMaps?.setOnCameraIdleListener(mClusterManager)
        googleMaps?.setOnMarkerClickListener(mClusterManager)

        // Add cluster items (markers) to the cluster manager.
        addItems()
    }

    private fun addItems() {
        // Set some lat/lng coordinates to start with.
        var lat = 51.5145160
        var lng = -0.1270060

        // Add ten cluster items in close proximity, for purposes of this example.
        for (i in 0..9) {
            val offset = i / 60.0
            lat += offset
            lng += offset
            val offsetItem = MyItem(lat, lng)
            mClusterManager!!.addItem(offsetItem)
        }
    }
}