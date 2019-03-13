package com.app.sambeautyworld.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.annotation.Nullable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.ui.newaddress.SetNewAddressFragment
import com.app.sambeautyworld.utils.LocationService
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.select_address_map.*


class SelectUsersLocationFragment : BaseFragment(), OnMapReadyCallback {
    private var lat_long: String? = null
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    private var latitude_initial: Double = 0.0
    private var longitude_initial: Double = 0.0


    override fun onMapReady(googleMap: GoogleMap) {
        googleMaps = googleMap
//        val latlong = lat_long?.split(",".toRegex())?.dropLastWhile { it.isEmpty() }?.toTypedArray()
//        val latitude = java.lang.Double.parseDouble(latlong!![0])
//        val longitude = java.lang.Double.parseDouble(latlong[1])
//        val sydney = LatLng(latitude, longitude)
//        googleMap.addMarker(MarkerOptions().position(sydney)
//                .title("Marker in Sydney"))
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        LocationService.getLocation(activity!!, { location ->
            latitude_initial = location.latitude
            longitude_initial = location.longitude

            Handler(Looper.getMainLooper()).post {
                val sydney = LatLng(latitude_initial, longitude_initial)
                googleMaps!!.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 19.0f))

            }

        }, {
            showSnackBar("Cannot fetch the locaton, please select the salons manually")
        })


        googleMaps!!.setOnCameraIdleListener {
            latitude = googleMaps!!.cameraPosition.target.latitude
            longitude = googleMaps!!.cameraPosition.target.longitude
        }


    }

    private fun check() {

    }

    private var googleMaps: GoogleMap? = null


    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        if (activity != null) {
            val mapFragment = childFragmentManager
                    .findFragmentById(R.id.map_select_address) as SupportMapFragment
            mapFragment.getMapAsync(this)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ivLocationIcons.bringToFront()
        tvConfirmLocationToUpdate.bringToFront()
        clickListeners()
    }

    private fun clickListeners() {
        tvConfirmLocationToUpdate.setOnClickListener {
            var setNewAddressFragment = SetNewAddressFragment()
            var args = Bundle()
            args.putDouble("lat", latitude)
            args.putDouble("lon", longitude)
            setNewAddressFragment.arguments = args
            replaceFragment(setNewAddressFragment, false, R.id.container_fullscreen)
        }

        ivRecenter.setOnClickListener {
            val sydney = LatLng(latitude_initial, longitude_initial)
            googleMaps!!.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 19.0f))

        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.app.sambeautyworld.R.layout.select_address_map, container, false)
    }
}