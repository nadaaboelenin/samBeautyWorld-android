package com.app.sambeautyworld.ui.enableLocation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.sambeautyworld.R
import com.app.sambeautyworld.base_classes.BaseFragment
import com.app.sambeautyworld.ui.home.HomeActivity
import kotlinx.android.synthetic.main.fragment_enable_location_services.*

/**
 * Created by ${Shubham} on 12/27/2018.
 */
class EnableLocationServices  : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpData()
        clickListeners()
    }

    private fun clickListeners() {
        btLater.setOnClickListener {
            startActivity(Intent(activity,HomeActivity::class.java))
        }
    }

    private fun setUpData() {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_enable_location_services,container,false)
    }
}